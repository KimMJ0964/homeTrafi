package com.skt.board.model.vo;

public class BoardFile {
	private int fileNo;
	private int refBNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String uploadDate;
	
	public BoardFile() {
		super();
	}

	public BoardFile(int fileNo, int refBNo, String originName, String changeName, String filePath, String uploadDate) {
		super();
		this.fileNo = fileNo;
		this.refBNo = refBNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getRefBNo() {
		return refBNo;
	}

	public void setRefBNo(int refBNo) {
		this.refBNo = refBNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", refBNo=" + refBNo + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + "]";
	}
	
}
