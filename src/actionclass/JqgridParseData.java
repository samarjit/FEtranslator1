package actionclass;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.ycs.fe.dao.DBConnector;
import com.ycs.fe.dto.PrepstmtDTOArray;

public class JqgridParseData extends ActionSupport{
	
	
//@Action(value="jqgrid", results={@Result(name="success")})
	public String execute(){
		DBConnector db = new DBConnector();
		CachedRowSet crs = null;
		String sqlquery = "select ";
		PrepstmtDTOArray prepar = null;
		
		try {
			crs = db.executePreparedQuery(sqlquery, prepar);
			while (crs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}