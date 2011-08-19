package actionclass;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.ycs.fe.util.CharResponseWrapper;

/**
 * Servlet Filter implementation class XSLTFilter
 */
public class XSLTFilter implements Filter {
	private FilterConfig filterConfig;
    /**
     * Default constructor. 
     */
    public XSLTFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		   String contentType;
		   String styleSheet;
		   System.out.println("XSLTFilter: started..");
			String type = request.getParameter("type");
			if (type == null || type.equals("")) {
				contentType = "text/html";
				styleSheet = "/xml/html.xsl";
			} else {
				if (type.equals("xml")) {
					contentType = "text/plain";
					styleSheet = "/xml/xml.xsl";
				} else {
					contentType = "text/html";
					styleSheet = "/xml/html.xsl";
				}
			}
			response.setContentType(contentType);
			String stylepath = filterConfig.getServletContext().getRealPath(styleSheet);
			System.out.println("XSLTFilter: transforming with XSL:"+stylepath);
			Source styleSource = new StreamSource(stylepath);
	
			PrintWriter out = response.getWriter();
			CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
			chain.doFilter(request, wrapper);
			// Get response from servlet
			System.out.println("XMLFilter about to transform:"+wrapper.toString());
			StringReader sr = new StringReader(new String(wrapper.toString()));
			Source xmlSource = new StreamSource((Reader) sr);
	
			try {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer(styleSource);
				CharArrayWriter caw = new CharArrayWriter();
				StreamResult result = new StreamResult(caw);
				transformer.transform(xmlSource, result);
				response.setContentLength(caw.toString().length());
				out.write(caw.toString());
			} catch (Exception ex) {
				out.println(ex.toString());
				out.write(wrapper.toString());
			}
			out.close();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

}
