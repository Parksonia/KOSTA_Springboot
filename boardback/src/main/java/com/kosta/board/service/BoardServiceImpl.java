package com.kosta.board.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.Heart;
import com.kosta.board.repository.BoardDSLRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.repository.HeartRepository;
import com.kosta.board.utils.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	
	private final BoardDSLRepository boardDslRepo;
	private final FileRepository fileRepo;
	private final HeartRepository heartRepo;
	private final BoardRepository boardRepo;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	
	
	@Override
	public Integer boardWrite(BoardDto boardDto, List<MultipartFile>fileList) throws Exception {
		Board boardEntity = boardDto.toEntity();
		boardRepo.save(boardEntity);  //보드를 먼저 저장해서 그 정보를 파일이 알 수 있게 해야 함 
		
		if(fileList!=null && fileList.size()>0) {
			for(MultipartFile file: fileList) {
				BFile bFile = new BFile();
				bFile.setDirectory(uploadPath);
				bFile.setName(file.getOriginalFilename());
				bFile.setSize(file.getSize());
				bFile.setContentType(file.getContentType());
				bFile.setBoard(boardEntity);
				
				fileRepo.save(bFile); // bfile테이블에 저장 
				File upFile = new File(uploadPath,bFile.getNum()+""); 
				file.transferTo(upFile); //실제 폴더에 업로드
			}
		
		}

			return boardEntity.toDto().getNum();

	}

	@Transactional // Dsl  이용하여 insert update 하는 경우 필수로 필요함
	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Board board = boardRepo.findById(num).orElseThrow(()->new Exception("글번호 오류"));
		boardDslRepo.updateBoardViewCount(num, board.getViewCount()+1); // dsl에서 다이렉트로 연산할 수 없음 
		
		return board.toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		
		return boardDslRepo.findHeart(memberId, boardNum);
	}

	@Override
	public Integer boardModify(BoardDto boardDto,List<Integer>delFileNum,List<MultipartFile>fileList) throws Exception {
		
		Board board = boardRepo.findById(boardDto.getNum()).orElseThrow(()->new Exception("글번호 오류"));
		board.setSubject(boardDto.getSubject());
		board.setContent(boardDto.getContent());
		boardRepo.save(board);

		if(delFileNum !=null) {
			for(Integer fn:delFileNum ) {
				File oldFile = new File(uploadPath,fn+"");
				if(oldFile !=null) oldFile.delete(); //폴더에서 삭제
				fileRepo.deleteById(fn); //테이블에서도 삭제 
				
			}
			
		}
		if(fileList!=null &&fileList.size()>0) {
			for(MultipartFile file:fileList) {
				BFile bfile = new BFile();
				bfile.setBoard(board);
				bfile.setName(file.getOriginalFilename());
				bfile.setDirectory(uploadPath);
				bfile.setContentType(file.getContentType());
				bfile.setSize(file.getSize());
				fileRepo.save(bfile);
				
				File nFile = new File(uploadPath,bfile.getNum()+"");
				file.transferTo(nFile);
			}
		}
		
		
		return board.toDto().getNum();
	}

	@Override
	public List<BoardDto> boardList(PageInfo page, String type, String word) throws Exception {
		PageRequest pageRequest = PageRequest.of(page.getCurPage()-1, 10);
		List<BoardDto> boardDtoList = null; 
		Long allCnt = 0L;
		
		if(word==null || word.trim().equals("")) { //전체
			boardDtoList = boardDslRepo.findBoardListByPaging(pageRequest).stream()
					.map(b->b.toDto()).collect(Collectors.toList());
					
			//전체 페이지 수 계산 
			allCnt = boardDslRepo.findBoardAllCount();
		
		} else { //검색조건 포함  
			boardDtoList = boardDslRepo.searchFindBoardListByPaging(pageRequest, type, word).stream()
					.map(b->b.toDto()).collect(Collectors.toList());
			allCnt = boardDslRepo.searchBoardAllCount(type,word);
			
		}
		Integer allPage = (int)(Math.ceil(allCnt.doubleValue()/pageRequest.getPageSize()));
		Integer startPage = (page.getCurPage()-1)/10*10+1;
		Integer endPage = Math.min(startPage+10-1,allPage);
		page.setAllPage(allPage);
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		
		System.out.println(boardDtoList);
		
		return boardDtoList;
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
	Integer heartNum = boardDslRepo.findHeart(id, boardNum);
		if(heartNum ==null) {
			heartRepo.save(Heart.builder().memId(id).boardNum(boardNum).build());
			return true;
		}else {
			heartRepo.deleteById(heartNum);
			return false;
		}
	}

	@Override
	@Transactional 
	public void deleteBoard(Integer boardNum) throws Exception {
		// 참조 되어 있는 heart도 삭제 되야함
	
		
		heartRepo.deleteByBoardNum(boardNum);
		
		boardRepo.deleteById(boardNum);
		
	}
}
