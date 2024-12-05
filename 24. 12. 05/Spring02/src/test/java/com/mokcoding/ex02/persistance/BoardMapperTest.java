package com.mokcoding.ex02.persistance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mokcoding.ex02.config.RootConfig;
import com.mokcoding.ex02.domain.BoardVO;
import com.mokcoding.ex02.persistence.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // Spring JUnit test 연결
@ContextConfiguration(classes = { RootConfig.class }) // 설정 파일 연결
@Log4j
public class BoardMapperTest {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void test() {
		// testBoardInsert();
		// testBoardList();
		 testBoardSelect();
		// testBoardUpdate();
		// testBoardDelete();
	}

	private void testBoardInsert() {
		BoardVO vo = new BoardVO(0, "test title", "test Content", "guest", null);
		int result = boardMapper.insert(vo);
		log.info(result + "행 삽입");

	}

	private void testBoardList() {
		for (BoardVO boardVO : boardMapper.selectList()) {
			log.info(boardVO);
		}
	}

	private void testBoardSelect() {
		int boardId = 6;
		BoardVO boardVO = boardMapper.selectOne(boardId);
		if (boardVO != null) {
			log.info(boardId +"번 게시글: " + boardVO);	
		} else {
			log.info(boardId +"번 게시글이 존재하지 않습니다.");	
		}
		
	}

	private void testBoardUpdate() {
		int boardId = 1;
		BoardVO boardVO = new BoardVO(boardId, "updated title", "updated Content", "guest", null);
		int result = boardMapper.update(boardVO);
		log.info(result + "행 업데이트: " + boardVO);
	}

	private void testBoardDelete() {
		int boardId = 6; 
		int result = boardMapper.delete(boardId);
		log.info(result + "행 삭제");
	}
}
