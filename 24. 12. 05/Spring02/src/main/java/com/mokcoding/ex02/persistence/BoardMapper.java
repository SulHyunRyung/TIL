package com.mokcoding.ex02.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mokcoding.ex02.domain.BoardVO;

@Mapper
public interface BoardMapper {
	// �޼ҵ� �̸��� BoardMApper.xml���� SQL ���� ���� �±��� ID ���� ����
	// �Ű������� BoardMappeer.xml���� #{������}�� ����(Ŭ���� Ÿ���� �� ���������� ��Ī)
    int insert(BoardVO boardVO); // �Խñ� ����
    List<BoardVO> selectList(); // ��ü �Խñ� ��ȸ
    BoardVO selectOne(int boardId); // Ư�� �Խñ� ��ȸ
    int update(BoardVO boardVO); // �Խñ� ����
    int delete(int boardId); // �Խñ� ����
}
