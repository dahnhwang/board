package dao;

import model.File;

public interface IFileDao {

	//파일을 저장한다음 fid값을 리턴한다.
	public int insertFile(File file);

	public File selectFile(int fId);

}
