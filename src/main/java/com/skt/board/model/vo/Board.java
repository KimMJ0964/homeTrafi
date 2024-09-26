package com.skt.board.model.vo;

public class Board {
	private int commNo;
	private String memId;
	private String title;
	private String content;
	private int viewCount;
	private int likeCount;
	private String createDate;
	private String updateDate;
	private String type;
	
	public Board() {
		super();
	}
	
	public Board(int commNo, String title, String memId, String createDate, int viewCount, int likeCount, String content, String type) {
		this.commNo = commNo;
		this.title = title;
		this.memId = memId;
		this.createDate = createDate;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.content = content;
		this.type = type;
	}
	
	public Board(int commNo, String memId, String title, String content, int viewCount, int likeCount,
			String createDate, String updateDate, String type) {
		super();
		this.commNo = commNo;
		this.memId = memId;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.type = type;
	}

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Board [commNo=" + commNo + ", memId=" + memId + ", title=" + title + ", content=" + content
				+ ", viewCount=" + viewCount + ", likeCount=" + likeCount + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", type=" + type + "]";
	}
}
