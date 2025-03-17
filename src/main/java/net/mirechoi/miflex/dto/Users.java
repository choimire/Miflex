package net.mirechoi.miflex.dto;

import java.sql.Timestamp;


public class Users {
	private int id;
	private String userid;
	private String userpass;
	private String username;
	private String useremail;
	private String usertel;
	private Integer zipcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private String userimg;
	private String userprofile;
	private Timestamp createAt;
	private Timestamp editAt;
	private String userip;
	private int grade;
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", userid=" + userid + ", userpass=" + userpass + ", username=" + username
				+ ", useremail=" + useremail + ", usertel=" + usertel + ", zipcode=" + zipcode + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", extraAddress=" + extraAddress + ", userimg=" + userimg
				+ ", userprofile=" + userprofile + ", createAt=" + createAt + ", editAt=" + editAt + ", userip="
				+ userip + ", grade=" + grade + "]";
	}
	public int getId() {
		return id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getExtraAddress() {
		return extraAddress;
	}
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public String getUserprofile() {
		return userprofile;
	}
	public void setUserprofile(String userprofile) {
		this.userprofile = userprofile;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public Timestamp getEditAt() {
		return editAt;
	}
	public void setEditAt(Timestamp editAt) {
		this.editAt = editAt;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public void setId(int id) {
		this.id = id;
	}
}