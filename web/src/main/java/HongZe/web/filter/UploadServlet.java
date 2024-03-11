package HongZe.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/upload/file")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream input = req.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		input.transferTo(output);
		String uploadText = output.toString(StandardCharsets.UTF_8);
		PrintWriter pw = resp.getWriter();
		pw.write("<h1>Uploaded:</h1>");
		pw.write("<pre><code>");
		pw.write(uploadText);
		pw.write("</code></pre>");
		pw.flush();
	}

}
