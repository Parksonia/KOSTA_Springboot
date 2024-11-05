package com.kosta.board.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.QBoard;
import com.kosta.board.entity.QHeart;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardDSLRepository {
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;


	
	//전체 페이지 수 계산을 위한 쿼리
	public Long findBoardAllCount() throws Exception {
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board.count())
				.from(board).fetchOne();
	}
	
	
	//검색 조건이 없을 때의 전체 리스트 조회
	public List<Board> findBoardListByPaging(PageRequest pageRequest) throws Exception {
		
		QBoard board = QBoard.board;
		
		return jpaQueryFactory.selectFrom(board)
			.orderBy(board.num.desc())
			.offset(pageRequest.getOffset())
			.limit(pageRequest.getPageSize())
			.fetch();
	}
	// 검색 조건이 있을 때의 조건에 따른 전체 개수 조회
	//전체 페이지 수 계산을 위한 쿼리
	public Long searchBoardAllCount(String type,String word) throws Exception {
		QBoard board = QBoard.board;
		Long cnt =0L;
		
		if(type.equals("subject")) {
			cnt = jpaQueryFactory.select(board.count())
				.from(board)
				.where(board.subject.contains(word))
				.fetchOne();
		}else if(type.equals("content")) {
			cnt = jpaQueryFactory.select(board.count())
					.from(board)
					.where(board.content.contains(word))
					.fetchOne();
		}else if(type.equals("writer")) {
			cnt = jpaQueryFactory.select(board.count())
					.from(board)
					.where(board.member.nickname.contains(word))
					.fetchOne();
		}
		return cnt; 
	}
	
	
	// 검색 조건이 있을 때의 전체 리스트 조회
	public List<Board> searchFindBoardListByPaging(PageRequest pageRequest,String type,String word) throws Exception {
		
		QBoard board = QBoard.board;
		List<Board> boardList =null;
		// 선택하는 option 종류에(제목,내용,작성자) 따라 다르게 검색
		if(type.equals("subject")) {
			boardList = jpaQueryFactory.selectFrom(board)
					.where(board.subject.contains(word))
					.orderBy(board.num.desc())
					.offset(pageRequest.getOffset())
					.limit(pageRequest.getPageSize())
					.fetch();
		}else if(type.equals("content")) {
			boardList = jpaQueryFactory.selectFrom(board)
					.where(board.content.contains(word))
					.orderBy(board.num.desc())
					.offset(pageRequest.getOffset())
					.limit(pageRequest.getPageSize())
					.fetch();
		}else if(type.equals("writer")) {
			boardList = jpaQueryFactory.selectFrom(board)
					.where(board.member.nickname.contains(word))
					.orderBy(board.num.desc())
					.offset(pageRequest.getOffset())
					.limit(pageRequest.getPageSize())
					.fetch();
		}
		return boardList;
	}
	public Integer findHeart(String memId, Integer boardNum) throws Exception {
		QHeart heart = QHeart.heart;
		return jpaQueryFactory.select(heart.num)
				.from(heart)
				.where(heart.memId.eq(memId).and(heart.boardNum.eq(boardNum)))
				.fetchOne();
		
	}
	
	public void updateBoardViewCount(Integer boardNum,Integer viewCount) throws Exception {
		QBoard board = QBoard.board;
		
		jpaQueryFactory.update(board)
			.set(board.viewCount,viewCount)
			.where(board.num.eq(boardNum))
			.execute();
					
	}	
	
}
