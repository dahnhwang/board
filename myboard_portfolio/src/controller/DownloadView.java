package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	private String filename;
	private String uri;

	public DownloadView(String filename, String uri) {
		this.filename = filename;
		this.uri = uri;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// TODO Auto-generated method stub
		resp.setContentType("application/download; utf-8");

		File file = new File(uri);
		resp.setContentLength((int) (file.length())); // 응답할 파일의 길이
		String filename = new String(this.filename.getBytes("UTF-8"), "ISO-8859-1");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = resp.getOutputStream();
		FileInputStream fis = new FileInputStream(file);

		FileCopyUtils.copy(fis, out);
		out.flush();

	}

}
