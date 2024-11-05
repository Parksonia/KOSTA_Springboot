package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.utils.PageInfo;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HttpSession session;
	
	@Value("${upload.path}")
	private String filePath;
	

	@GetMapping("/boardList")
	public ResponseEntity<Map<String,Object>> boardList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
								@RequestParam(name="type",required = false) String type,
								@RequestParam(name="keyword",required = false) String keyword) {

		try {
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			
			List<BoardDto> boardList = boardService.boardList(pageInfo, type, keyword);
			
			Map<String,Object> res = new HashMap<>();
			res.put("boardList", boardList);
			res.put("pageInfo", pageInfo);
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//**@RequestBody는 Json으로 가져올 때(객체를 보낼 때) 사용할 수 있지만, 현재는 FormData로 프론트에서 데이터를 가져오기 때문에 @RequestBody사용하면 안된다.
	@PostMapping("/boardWrite") 
	public ResponseEntity<String> boardWrite(BoardDto boardDto,
			@RequestParam(name= "file", required = false) MultipartFile[] files) {
		List<MultipartFile> fileList = null;
		try {
		
			
			Integer boardNum = boardService.boardWrite(boardDto,files== null? null:Arrays.asList(files));
			return new ResponseEntity<String>(String.valueOf(boardNum),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("게시글 작성 오류",HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/boardDetail/{num}") 
	public ResponseEntity<Map<String,Object>> boardDetail (@PathVariable Integer num) {
		try {
			Map<String,Object> res = new HashMap<>();
			BoardDto boardDto = boardService.boardDetail(num);
			boolean heart = boardService.checkHeart(boardDto.getWriter(), num) !=null;
			res.put("board", boardDto);
			res.put("heart", heart);
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/image/{num}") 
	public void image(@PathVariable String num,HttpServletResponse response) {
		try {
			InputStream ins = new FileInputStream(new File(filePath,num));
			FileCopyUtils.copy(ins, response.getOutputStream());
			ins.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@PostMapping("/boardModify")
	public ResponseEntity<Integer> boardModify(@RequestBody BoardDto boardDto,
			@RequestParam(name= "delFile", required = false) Integer [] delFileNum,
			@RequestParam(name= "file", required = false) MultipartFile[] files){
		try {
			boardService.boardModify(boardDto,delFileNum==null?null:Arrays.asList(delFileNum),
					files==null?null:Arrays.asList(files));
			
			return new ResponseEntity<Integer>(boardDto.getNum(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/boardDelete/{num}")
	public ResponseEntity<String> boardDelete(@PathVariable String num) {
		Integer boardNum = Integer.parseInt(num);
		try {
			boardService.deleteBoard(boardNum);
			
			return new ResponseEntity<String>(String.valueOf(true),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
		
	}
	@PostMapping("/boardLike") 
	public ResponseEntity<String> boardList(@RequestBody Map<String,String>param) {
		// Json형태로 받으면서 , 해당 Dto가 따로 없다면, Map으로 받음 
		try {
			boolean heart = boardService.toggleHeart(param.get("id"), Integer.parseInt(param.get("num")));
			return new ResponseEntity<String>(String.valueOf(heart),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
