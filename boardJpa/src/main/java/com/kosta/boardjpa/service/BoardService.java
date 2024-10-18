package com.kosta.boardjpa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.boardjpa.dto.BoardDto;
import com.kosta.boardjpa.utils.PageInfo;


public interface BoardService {
	Integer boardWrite(BoardDto board, MultipartFile file, MultipartFile dfile) throws Exception;
	BoardDto boardDetail(Integer num) throws Exception;
	Integer checkHeart(String memberId, Integer boardNum) throws Exception;
	Integer boardModify(BoardDto board, MultipartFile file, MultipartFile dfile) throws Exception;
	List<BoardDto> boardList(PageInfo page) throws Exception;
	boolean toggleHeart(String id, Integer boardNum) throws Exception;
	void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception;	
	//void imageView(HttpServletRequest request, OutputStream out, String file) throws Exception;
}
