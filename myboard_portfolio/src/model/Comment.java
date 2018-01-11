package model;

import java.util.Date;

public class Comment {
	
	private int cId;
	private String cContent;
	private int mId;
	private int bId;
	private Date cDatetime;
	private int groupId;
	private int groupSeq;
	private int groupLv;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public Date getcDatetime() {
		return cDatetime;
	}
	public void setcDatetime(Date cDatetime) {
		this.cDatetime = cDatetime;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public int getGroupLv() {
		return groupLv;
	}
	public void setGroupLv(int groupLv) {
		this.groupLv = groupLv;
	}
	@Override
	public String toString() {
		return "Comment [cId=" + cId + ", cContent=" + cContent + ", mId=" + mId + ", bId=" + bId + ", cDatetime="
				+ cDatetime + ", groupId=" + groupId + ", groupSeq=" + groupSeq + ", groupLv=" + groupLv + "]";
	}
	
	

}
