package model;

import java.util.Date;

public class BoardHistory extends Board {

	private int hId;
	private int bId;
	private String title;
	private String bContent;
	private int mId;
	private String bPwd;
	private Date bDatetime;
	private int hit;
	private int groupId;
	private int groupSeq;
	private int groupLv;
	private int fId;
	private int isPrivate;
	private int isUpdated;
	private int isDeleted;
	private int mDeleted;
	private Date hDatetime;

	public BoardHistory(Board board, int mDeleted) {
		this.bId = board.getbId();
		this.title = board.getTitle();
		this.bContent = board.getbContent();
		this.mId = board.getmId();
		this.bPwd = board.getbPwd();
		this.bDatetime = board.getbDatetime();
		this.hit = board.getHit();
		this.groupId = board.getGroupId();
		this.groupSeq = board.getGroupSeq();
		this.groupLv = board.getGroupLv();
		this.fId = board.getfId();
		this.isPrivate = board.getIsPrivate();
		this.isUpdated = board.getIsUpdated();
		this.isDeleted = board.getIsDeleted();
		this.mDeleted = mDeleted;
	}

	public BoardHistory(Board board) {
		this.bId = board.getbId();
		this.title = board.getTitle();
		this.bContent = board.getbContent();
		this.mId = board.getmId();
		this.bPwd = board.getbPwd();
		this.bDatetime = board.getbDatetime();
		this.hit = board.getHit();
		this.groupId = board.getGroupId();
		this.groupSeq = board.getGroupSeq();
		this.groupLv = board.getGroupLv();
		this.fId = board.getfId();
		this.isPrivate = board.getIsPrivate();
		this.isUpdated = board.getIsUpdated();
		this.isDeleted = board.getIsDeleted();
	}

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getbPwd() {
		return bPwd;
	}

	public void setbPwd(String bPwd) {
		this.bPwd = bPwd;
	}

	public Date getbDatetime() {
		return bDatetime;
	}

	public void setbDatetime(Date bDatetime) {
		this.bDatetime = bDatetime;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
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

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(int isUpdated) {
		this.isUpdated = isUpdated;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getmDeleted() {
		return mDeleted;
	}

	public void setmDeleted(int mDeleted) {
		this.mDeleted = mDeleted;
	}

	public Date gethDatetime() {
		return hDatetime;
	}

	public void sethDatetime(Date hDatetime) {
		this.hDatetime = hDatetime;
	}

	@Override
	public String toString() {
		return "BoardHistory [hId=" + hId + ", bId=" + bId + ", title=" + title + ", bContent=" + bContent + ", mId="
				+ mId + ", bPwd=" + bPwd + ", bDateTime=" + bDatetime + ", hit=" + hit + ", groupId=" + groupId
				+ ", groupSeq=" + groupSeq + ", groupLv=" + groupLv + ", fId=" + fId + ", isPrivate=" + isPrivate
				+ ", isUpdated=" + isUpdated + ", isDeleted=" + isDeleted + ", mDeleted=" + mDeleted + ", hDatetime="
				+ hDatetime + "]";
	}

}
