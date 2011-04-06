package com.ycs.user.accesscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ycs.fe.dao.DBConnector;
import com.ycs.fe.dto.PrepstmtDTOArray;
import com.ycs.fe.dto.PrepstmtDTO.DataType;
import com.ycs.user.Role;

public class UserRoleHelper {
 
	
	  
	public List<Role> getRolesForUser(String userId){
		DBConnector db = new DBConnector();
		
		String qry = "select roleid from user_role_map where userid=?";
		
		PrepstmtDTOArray arPrepstmt = new PrepstmtDTOArray();
		arPrepstmt.add(DataType.STRING, userId);
		CachedRowSet crs = null;
		List<Role> roleList = new ArrayList<Role>();
		Role tmpRole = null;
		try {
			crs = db.executePreparedQuery(qry , arPrepstmt );
			while(crs.next()){
				tmpRole = new Role(); 
				tmpRole.setRoleId(crs.getString("roleid") );
				roleList.add(tmpRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(crs!=null){
					crs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roleList;
	}
	
	
	public static void main(String[] args) {

	}
	
}
