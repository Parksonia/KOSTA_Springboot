package com.kosta.boarddsl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.boarddsl.dto.BoardDto;
import com.kosta.boarddsl.dto.MemberDto;
import com.kosta.boarddsl.service.BoardService;
import com.kosta.boarddsl.utils.PageInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@Autowired
	private HttpSession session;
	
	@Value("${upload.path}")
	private String downloadPath;
	

	@GetMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
								@RequestParam(value="type",required = false)String type,
								@RequestParam(value="word",required = false)String word) {
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<BoardDto> boardList = boardService.boardList(pageInfo,type,word);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("type", type);
			mav.addObject("word", word);
			mav.setViewName("boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}

	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			BoardDto board = boardService.boardDetail(num); // boardService.boardDetail(num) 리턴타입이 board라서
			mav.addObject("board", board);
			if (memberDto != null) {
				Integer hnum = boardService.checkHeart(memberDto.getId(), num);
				mav.addObject("heart", hnum);
			}
			mav.setViewName("boarddetail");

		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}

	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "writeform";
	}

	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDto board,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "dfile", required = false) MultipartFile dfile, Model model) {
		try {
			Integer boardNum = boardService.boardWrite(board, file, dfile);
			// 저장하고 나서 detail로 넘겨줄건데, detail 조회를 위한 글번호 num을 알아야 함 -> board.xml insert 수정해서
			// 사용
			return "redirect:boardDetail?num=" +boardNum; // getNum으로 사용할 수 있음
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}

	@GetMapping("/boardModify") // @GetMapping("/boardModify/{num}" 하고 @PathVariable Integer num) 해도 됨
	public ModelAndView boardModify(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			BoardDto board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyform");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}

	@PostMapping("/boardModify")
	public String boardModify(BoardDto boardDto, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart(value = "dfile", required = false) MultipartFile dfile, Model model) {
		try {
			Integer boardNum = boardService.boardModify(boardDto, file, dfile);
			 
			return "redirect:boardDetail?num=" + boardDto.getNum();
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}

	@PostMapping("/heart")
	@ResponseBody
	public ResponseEntity<String> heart(@RequestParam("num") Integer num) {
		try {
			boolean heart = boardService.toggleHeart(((MemberDto) session.getAttribute("member")).getId(), num);
			return new ResponseEntity<String>(String.valueOf(heart),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/fileDown")
	public void fileDown(@RequestParam("file") String filename, HttpServletResponse response) {
		
		try {
			
		FileInputStream fis = new FileInputStream(new File(downloadPath,filename));
		
		//파일 형식 얻어오기
		String mimeType ="application/octet-stream" ; // octet-stream 8비트로 된 일련의 데이터를 뜻함
		
		response.setContentType(mimeType);
	
		String encoding = new String (filename.getBytes("utf-8"),"8859_1");// 한글 파일명 깨짐 방지
		response.setHeader("Content-disposition", "attachment;filename=\"" +encoding);

			FileCopyUtils.copy(fis,response.getOutputStream());
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
