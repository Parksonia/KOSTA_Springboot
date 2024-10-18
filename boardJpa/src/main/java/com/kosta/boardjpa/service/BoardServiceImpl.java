package com.kosta.boardjpa.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.boardjpa.dto.BoardDto;
import com.kosta.boardjpa.entity.BFile;
import com.kosta.boardjpa.entity.Board;
import com.kosta.boardjpa.repository.BoardRepository;
import com.kosta.boardjpa.repository.FileRepository;
import com.kosta.boardjpa.utils.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepo;
	private final FileRepository fileRepo;
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
		return boardRepo.findById(num).orElseThrow(()->new Exception("글번호 오류")).toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer boardModify(BoardDto board, MultipartFile file, MultipartFile dfile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDto> boardList(PageInfo page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}
	


}
