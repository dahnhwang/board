package model;

public class File {
	
	private int fId;
	private String fName;
	private long fSize;
	private String fUri;
	private String fHash;
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public long getfSize() {
		return fSize;
	}
	public void setfSize(Long fileSize) {
		this.fSize = fileSize;
	}
	public String getfUri() {
		return fUri;
	}
	public void setfUri(String fUri) {
		this.fUri = fUri;
	}
	public String getfHash() {
		return fHash;
	}
	public void setfHash(String fHash) {
		this.fHash = fHash;
	}
	@Override
	public String toString() {
		return "File [fId=" + fId + ", fName=" + fName + ", fSize=" + fSize + ", fUri=" + fUri + ", fHash=" + fHash
				+ "]";
	}
	
	

}
