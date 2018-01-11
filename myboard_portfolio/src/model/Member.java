package model;

public class Member {

	private int mId;
	private String mName;
	private String mPwd;
	private String mEmail;
	private int isWithdraw;
	
	public int getIsWithdraw() {
		return isWithdraw;
	}
	public void setIsWithdraw(int isWithdraw) {
		this.isWithdraw = isWithdraw;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPwd() {
		return mPwd;
	}
	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	@Override
	public String toString() {
		return "Member [mId=" + mId + ", mName=" + mName + ", mPwd=" + mPwd + ", mEmail=" + mEmail + ", isWithdraw="
				+ isWithdraw + "]";
	}

	
	
	
	
	
}
