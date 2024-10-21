package com.kosta.boardjpa.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.boardjpa.dto.BoardDto;
import com.kosta.boardjpa.entity.BFile;
import com.kosta.boardjpa.entity.Board;
import com.kosta.boardjpa.entity.Heart;
import com.kosta.boardjpa.entity.Member;
import com.kosta.boardjpa.repository.BoardRepository;
import com.kosta.boardjpa.repository.FileRepository;
import com.kosta.boardjpa.repository.HeartRepository;
import com.kosta.boardjpa.utils.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepo;
	private final FileRepository fileRepo;
	private final HeartRepository heartRepo;
	
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@Override
	public Integer boardWrite(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception {
		// 이미지파일 업로드 (선택해서 업로드니까 if)
				if (file != null && !file.isEmpty()) {
					BFile bFile = new BFile();
					bFile.setDirectory(uploadPath);
					bFile.setName(file.getOriginalFilename());
					bFile.setSize(file.getSize());
					bFile.setContentType(file.getContentType());
					fileRepo.save(bFile);

					File upFile = new File(uploadPath, bFile.getNum() + "");
					file.transferTo(upFile);

					boardDto.setImgFileNum(bFile.getNum());
				}

				// 그 외 파일 업로드 (선택해서 업로드니까 if)
				if (dfile != null && !dfile.isEmpty()) {
			
					BFile bFile = new BFile();
					bFile.setDirectory(uploadPath);
					bFile.setName(dfile.getOriginalFilename());
					bFile.setSize(dfile.getSize());
					bFile.setContentType(dfile.getContentType());
					fileRepo.save(bFile);

					File upFile = new File(uploadPath, dfile.getOriginalFilename());
					dfile.transferTo(upFile);

					boardDto.setUploadFileNum(bFile.getNum());
					boardDto.setUploadFileName(bFile.getName());
				}

				Board boardEntity = boardDto.toEntity();
				 boardRepo.save(boardEntity);
				 return boardEntity.toDto().getNum();
	}

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Board board = boardRepo.findById(num).orElseThrow(()->new Exception("글번호 오류"));
		board.setViewCount(board.getViewCount()+1);	
		boardRepo.save(board);
		return board.toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		Optional<Heart> cHeart = heartRepo.findByMember_IdAndBoard_Num(memberId, boardNum);
		if(cHeart.isPresent()) {
			return cHeart.get().getNum();
		}		
		return null;
	}

	
	
	@Override
	public Integer boardModify(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception {
		
		//파일을 수정 한 경우와 안한 경우 고려해야함 
		//1.기존 파일이 있으면서 파일을 변경할 경우
		//2.기존 파일이 없으면서 파일을 추가하는 경우
		//3.기존 파일이 있는데 변경하지 않는 경우 
		
		Board preBoard = boardRepo.findById(boardDto.getNum()).get();
		preBoard.setSubject(boardDto.getSubject());
		preBoard.setContent(boardDto.getContent());
		
		if(file!=null && !file.isEmpty()) { // 이미지 파일 변경o : 엎어쓰기
			BFile imgFile = BFile.builder()
					.name(file.getOriginalFilename())
					.directory(uploadPath)
					.size(file.getSize())
					.contentType(file.getContentType())
					.build();
			fileRepo.save(imgFile);
			File upFile = new File(uploadPath, imgFile.getNum() + "");
			file.transferTo(upFile);
			preBoard.setImageFile(imgFile);
			
		}
		
		if(dfile!=null && !dfile.isEmpty()) { // 업로드 파일 변경o : 엎어쓰기
			BFile uploadFile = BFile.builder()
					.name(dfile.getOriginalFilename())
					.directory(uploadPath)
					.size(dfile.getSize())
					.contentType(dfile.getContentType())
					.build();
			fileRepo.save(uploadFile);
			File upFile = new File(uploadPath,dfile.getOriginalFilename());
			dfile.transferTo(upFile);
			preBoard.setUploadFile(uploadFile);
		}
		boardRepo.save(preBoard);
		return preBoard.getNum();
	}

	@Override
	public List<BoardDto> boardList(PageInfo page,String type,String word) throws Exception {
		PageRequest pageRequest = PageRequest.of(page.getCurPage()-1, 10,Sort.by(Sort.Direction.DESC,"num"));
		//Page<Board> pages = boardRepo.findAll(pageRequest); //전체 조회 시 페이지 인포를 보내면 jpa가 알아서 페이지에 대한 데이터만 가져옴, 페이지 인포를 가져오진 않음
		Page<Board> pages = null;
		
		//1.검색 조건x- 전체 조회
		if(word == null || word.trim().equals("")) {
			pages =boardRepo.findAll(pageRequest);
		
		//2.검색 조건o- 제목,내용,작성자 기준 검색
		}else {
			if(type.equals("subject")) {
				pages =  boardRepo.findBySubjectContains(word, pageRequest);
			}else if(type.equals("content")) {
				pages = boardRepo.findByContentContains(word, pageRequest);
			}else if(type.equals("writer")) {
				pages = boardRepo.findByMember_Nickname(word, pageRequest);
			}
		}
		
		page.setAllPage(pages.getTotalPages());
		Integer startPage = (page.getCurPage()-1)/10*10+1;
		Integer endPage = Math.min(startPage+10-1, page.getAllPage());
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		return pages.getContent().stream().map((b)->b.toDto()).collect(Collectors.toList());
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		Integer heartBoardNum = checkHeart(id,boardNum);
		if(heartBoardNum ==null) {
			heartRepo.save(Heart.builder()
					.member(Member.builder().id(id).build())
					.board(Board.builder().num(boardNum).build())
					.build());
			return true;
		}
		else {
			heartRepo.deleteById(heartBoardNum);
			return false;
		}
		
	}

}
