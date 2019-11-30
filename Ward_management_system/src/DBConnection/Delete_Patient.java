package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Delete_Patient {
	
	public  void Delete_P(int p_num) {
		Connection conn =null;
		CallableStatement st;
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL DELETE_PATIENT(?)}");
			st.setInt(1,p_num);   //姓名
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Delete Fail!");
		}
	}
}
