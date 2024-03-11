package HongZe.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class RereadHttpServletRequest extends HttpServletRequestWrapper {
	private byte[] body;
	private boolean open = false;

	public RereadHttpServletRequest(HttpServletRequest request, byte[] body) {
		super(request);
		// TODO Auto-generated constructor stub
		this.body = body;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		if (open) {
			throw new IllegalStateException("Cannot re-open input stream!");
		}
		open = true;
		return new ServletInputStream() {
			private int offset = 0;

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				if (offset >= body.length) {
					return -1;
				}
				int n = body[offset] & 0xff;
				offset++;
				return n;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return offset >= body.length;
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		if (open) {
			throw new IllegalStateException("Cannot re-open reader!");
		}
		open = true;
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body), StandardCharsets.UTF_8));
	}

}
