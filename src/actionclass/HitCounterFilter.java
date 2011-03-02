package actionclass;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class HitCounterFilter
 */
public class HitCounterFilter implements Filter {
	private Logger logger = Logger.getLogger(this.getClass());
    /**
     * Default constructor. 
     */
    public HitCounterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		CharResponseWrapper wrapper = new CharResponseWrapper(
		   (HttpServletResponse)response);
		logger.debug("HitCounterFilter: started");
		chain.doFilter(request, wrapper);
		 
		logger.debug("HitCounterFilter: content type"+wrapper.getContentType());
		if(wrapper!= null && wrapper.getContentType()!=null && wrapper.getContentType().equals("text/html")) {
		   CharArrayWriter caw = new CharArrayWriter();
		   caw.write(wrapper.toString());
		
		caw.write("<p>\nYou are visitor number <font color='red'>" +12 + "</font>");
		   caw.write("\n</body></html>");
		   response.setContentLength(caw.toString().length());
		   out.write(caw.toString());
		} else 
		   out.write(wrapper.toString());
		out.flush();
		//out.close();
		logger.debug("HitCounterFilter: end");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
