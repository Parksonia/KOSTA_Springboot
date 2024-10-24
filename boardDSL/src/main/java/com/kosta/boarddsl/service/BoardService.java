package com.kosta.boarddsl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.boarddsl.dto.BoardDto;
import com.kosta.boarddsl.utils.PageInfo;


public interface BoardService {
	Integer boardWrite(BoardDto board, MultipartFile file, MultipartFile dfile) throws Exception;
	BoardDto boardDetail(Integer num) throws Exception;
	Integer checkHeart(String memberId, Integer boardNum) throws Exception;
	Integer boardModify(BoardDto board, MultipartFile file, MultipartFile dfile) throws Exception;
	List<BoardDto> boardList(PageInfo page,String type,String word) throws Exception;
	boolean toggleHeart(String id, Integer boardNum) throws Exception;
	void deleteBoard(Integer boardNum) throws Exception;	
	//void imageView(HttpServletRequest request, OutputStream out, String file) throws Exception;
}
