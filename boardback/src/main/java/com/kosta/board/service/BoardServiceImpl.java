package com.kosta.board.service;

import java.io.File;
import java.util.List;
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

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		
		return boardRepo.findById(num).orElseThrow(()->new Exception("글번호 오류")).toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		
		return boardDslRepo.findHeart(memberId, boardNum);
	}

//	@Override
//	public Integer boardModify(BoardDto boardDto, MultipartFile imgfile, MultipartFile uploadfile) throws Exception {
//		
//		Board board = boardRepo.findById(boardDto.getNum()).orElseThrow(()->new Exception("글번호 오류"));
//		board.setSubject(boardDto.getSubject());
//		board.setContent(boardDto.getContent());
//		
//
//		Integer bimgFileNum = null;
//		Integer bupfileFileNum =null;
//		
//		if(imgfile !=null && !imgfile.isEmpty()) {
//			
//			//기존의 파일이 있을때-파일번호 임시저장
//			if(board.getImageFile() != null) {
////				여기에 삭제를 하면 참조 걸려 있기 때문에 에러 발생 전체 글 선 업데이트 후 그 이후에 이전 파일을 삭제해야함
////				Integer fileNum = board.getImageFile().getNum();
////				new File(uploadPath,fileNum+"").delete(); //디렉토리 삭제
////				fileRepo.deleteById(fileNum); //테이블 삭제
//				bimgFileNum = board.getImageFile().getNum();
//				
//			}
//		
//			BFile cImgFile = new BFile();
//			cImgFile.setDirectory(uploadPath);
//			cImgFile.setName(imgfile.getOriginalFilename());
//			cImgFile.setSize(imgfile.getSize());
//			cImgFile.setContentType(imgfile.getContentType());
//			fileRepo.save(cImgFile);
//			
//			File newImgFile = new File(uploadPath,cImgFile.getNum()+"");
//			imgfile.transferTo(newImgFile);
//		
//			board.setImageFile(cImgFile);
//		}
//		
//		if(uploadfile !=null && !uploadfile.isEmpty()) {
//			if(board.getUploadFile()!=null) {
////				Integer fileNum = board.getUploadFile().getNum();
////				new File(uploadPath,fileNum+"").delete();
////				fileRepo.deleteById(fileNum);
//				bupfileFileNum = board.getUploadFile().getNum();
//			}
//			
//			BFile cUploadFile = new BFile();
//			cUploadFile.setDirectory(uploadPath);
//			cUploadFile.setName(uploadfile.getOriginalFilename());
//			cUploadFile.setSize(uploadfile.getSize());
//			cUploadFile.setContentType(uploadfile.getContentType());
//			fileRepo.save(cUploadFile); 
//			
//			File newUpFile = new File(uploadPath,cUploadFile.getNum()+"");
//			uploadfile.transferTo(newUpFile);
//			
//			
//			board.setUploadFile(cUploadFile);
//			
//
//		}
//	
		//AutoIncrement니까 save 해야 번호를 가져올 수 있음	,글수정 완료
//		boardRepo.save(board);
//		
//		//파일 수정이 update 됐으니 이제 기존파일을 삭제 할 수 있다.
//		if(bimgFileNum !=null ) {
//			fileRepo.deleteById(bimgFileNum); //테이블 삭제
//			new File(uploadPath,bimgFileNum+"").delete(); //디렉토리 삭제
//			
//		}
//		if(bupfileFileNum !=null) {
//			fileRepo.deleteById(bupfileFileNum); //테이블 삭제
//			new File(uploadPath,bupfileFileNum+"").delete(); //디렉토리 삭제
//			
//		}
//		
//		
//		return board.toDto().getNum();
//	}

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
