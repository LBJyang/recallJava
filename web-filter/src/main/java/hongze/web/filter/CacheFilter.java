package hongze.web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CacheFilter implements Filter {
	private Map<String, byte[]> cache = new ConcurrentHashMap<String, byte[]>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String url = req.getRequestURI();
		byte[] data = this.cache.get(url);
		resp.setHeader("X-cache-hit", data == null ? "no" : "yes");
		if (data == null) {
			CachedHttpServletResponse wrapper = new CachedHttpServletResponse(resp);
			chain.doFilter(request, wrapper);
			data = wrapper.getContent();
			cache.put(url, data);
		}
		ServletOutputStream output = resp.getOutputStream();
		output.write(data);
		output.flush();
	}

}
