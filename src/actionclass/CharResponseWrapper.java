package actionclass;

import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class CharResponseWrapper extends HttpServletResponseWrapper {
   
	static class ServletOutputStreamWrapper extends ServletOutputStream {
	    StringWriter writer;

	    ServletOutputStreamWrapper(StringWriter aWriter) {
	        writer = aWriter;
	    }

	    public void write(int aByte) {
	        writer.write(aByte);
	    }
    }
	
   private CharArrayWriter output;
   
   StringWriter strout;
   PrintWriter writer;
   ServletOutputStream sout;

private boolean getWriterCalled;

private boolean getOutputStreamCalled;
   
   public String toString() {
      return strout.toString();
   }
   public CharResponseWrapper(HttpServletResponse response){
      super(response);
      
     strout = new StringWriter();
//	 sout = new ServletOutputStreamWrapper(strout);
//     output = new CharArrayWriter();
//	 writer = new PrintWriter(strout);
   }
   
   public String getData() {
       writer.flush();
       return strout.toString();
   }
   
   public ServletOutputStream getOutputStream(){
	   if (getWriterCalled) {
		      throw new IllegalStateException("getWriter already called");
		    }

		    getOutputStreamCalled = true;
		    
	   return new ServletOutputStreamWrapper(strout);
   }
   
   public PrintWriter getWriter(){
	   if (writer != null) {
		      return writer;
		    }
		    if (getOutputStreamCalled) {
		      throw new IllegalStateException("getOutputStream already called");
		    }
		    getWriterCalled = true;
		    writer = new PrintWriter(strout);
		    
	   return writer;
   }
}