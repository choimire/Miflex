package net.mirechoi.miflex.dto;

public class FileDTO {
	private long id;
	private long bid;
	private String ofilename;
	private String nfilename;
	private String ext;
	private long filesize;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getOfilename() {
		return ofilename;
	}
	public void setOfilename(String ofilename) {
		this.ofilename = ofilename;
	}
	public String getNfilename() {
		return nfilename;
	}
	public void setNfilename(String nfilename) {
		this.nfilename = nfilename;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "FileDTO [id=" + id + ", bid=" + bid + ", ofilename=" + ofilename + ", nfilename=" + nfilename + ", ext="
				+ ext + ", filesize=" + filesize + "]";
	}
}
