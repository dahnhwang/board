package service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IFileDao;
import model.File;

@Service
public class FileService implements IFileService {

	@Autowired
	public IFileDao fileDao;

	@Override
	public int saveFile(File file) {
		fileDao.insertFile(file);
		// 여기서 파일을 어느 경로에다가 어떻게 저장할 것인지 작업이 들어가야 할 듯
		int savedFileId = file.getfId();
		return savedFileId;
	}

	@Override
	public HashMap<String, Object> getFileName(int fId) {
		File file = fileDao.selectFile(fId);
		HashMap<String, Object> fileMap = new HashMap<>();
		fileMap.put("fName", file.getfName());
		fileMap.put("fSize", file.getfSize());
		fileMap.put("fUri", file.getfUri());
		return fileMap;
	}

}
