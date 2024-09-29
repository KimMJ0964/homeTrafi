package com.skt.board.model.vo;

public class BoardComment {
	private String memId;
	private int commentNo;
	private int commNo;
	private int parentCommentNo;
	private String commentContent;
	private String commentDate;
	private String status;
	
	BoardComment() {
		super();
	}

	public BoardComment(String memId, int commentNo, int commNo, int parentCommentNo, String commentContent,
			String commentDate, String status) {
		super();
		this.memId = memId;
		this.commentNo = commentNo;
		this.commNo = commNo;
		this.parentCommentNo = parentCommentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.status = status;
	}
	
	public BoardComment(int commNo, String memId, String commentContent) {
		super();
		this.commNo = commNo;
		this.memId = memId;
		this.commentContent = commentContent;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public int getParentCommentNo() {
		return parentCommentNo;
	}

	public void setParentCommentNo(int parentCommentNo) {
		this.parentCommentNo = parentCommentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BoardComment [memId=" + memId + ", commentNo=" + commentNo + ", commNo=" + commNo + ", parentCommentNo="
				+ parentCommentNo + ", commentContent=" + commentContent + ", commentDate=" + commentDate + ", status="
				+ status + "]";
	}
	

}
