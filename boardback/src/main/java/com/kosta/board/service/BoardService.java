package com.kosta.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.utils.PageInfo;


public interface BoardService {
	Integer boardWrite(BoardDto board,List<MultipartFile>fileList) throws Exception;
	BoardDto boardDetail(Integer num) throws Exception;
	Integer checkHeart(String memberId, Integer boardNum) throws Exception;
	Integer boardModify(BoardDto board,List<Integer>delFileNum,List<MultipartFile>fileList) throws Exception;
	List<BoardDto> boardList(PageInfo page,String type,String word) throws Exception;
	boolean toggleHeart(String id, Integer boardNum) throws Exception;
	void deleteBoard(Integer boardNum) throws Exception;	
	//void imageView(HttpServletRequest request, OutputStream out, String file) throws Exception;
}
