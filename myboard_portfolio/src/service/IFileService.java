package service;

import java.util.HashMap;

import model.File;

public interface IFileService {

	// public int insertFile(File file);
	//
	// public File selectFile(int fId);

	public int saveFile(File file);

	public HashMap<String, Object> getFileName(int fId);

}
