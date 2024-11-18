package edu.web.homework;

import java.util.Date;

public class BoardVO {
	private int boardId;
	private String boardTitle;
	private String userid;
	private Date boardRegDate;
	
	public BoardVO() {}
	
	public BoardVO(int boardId, String boardTitle, String userid, Date boardRegDate) {
		super();
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.userid = userid;
		this.boardRegDate = boardRegDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(Date boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	@Override
	public String toString() {
		return "BoardVO [boardId=" + boardId + ", boardTitle=" + boardTitle + ", userid=" + userid + ", boardRegDate="
				+ boardRegDate + "]";
	}
}
