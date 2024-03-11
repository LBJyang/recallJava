package hongze.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CachedHttpServletResponse extends HttpServletResponseWrapper {
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	private boolean open = false;

	public CachedHttpServletResponse(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		if (open) {
			throw new IllegalStateException("Cannot re-open writer!");
		}
		open = true;
		return new PrintWriter(output, false, StandardCharsets.UTF_8);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		if (open) {
			throw new IllegalStateException("Cannot re-open output stream!");
		}
		open = true;
		return new ServletOutputStream() {

			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				output.write(b);
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

	public byte[] getContent() {
		return output.toByteArray();
		// TODO Auto-generated method stub

	}
}
