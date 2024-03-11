package hongze.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ValidateUploadFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String digest = req.getHeader("Signature-Method");
		String signature = req.getHeader("Signature");
		if (digest == null || digest.isEmpty() || signature == null || signature.isEmpty()) {
			sendErrorPage(resp, "Missing Signatue");
			return;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream input = new DigestInputStream(req.getInputStream(), md);
		byte[] buffer = new byte[1024];
		for (;;) {
			int len = input.read(buffer);
			if (len == -1) {
				break;
			}
		}
		String actural = new BigInteger(1, md.digest()).toString(16);
		if (!actural.endsWith(signature)) {
			sendErrorPage(resp, "Invalid signature.");
            return;
		}
		chain.doFilter(request, response);
	}

	private void sendErrorPage(HttpServletResponse resp, String errorMessage) throws IOException {
		// TODO Auto-generated method stub
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		PrintWriter pw = resp.getWriter();
		pw.write("<html><body><h1>");
		pw.write(errorMessage);
		pw.write("</h1></body></html>");
		pw.flush();
	}

}
