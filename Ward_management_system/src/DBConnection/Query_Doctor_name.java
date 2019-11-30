package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Doctor_name {
	public  String Query_name(int doc_num) {
		Connection conn =null;
		CallableStatement st;
		String result="";
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL doctor_name_query(?,?)}");
			st.setInt(1, doc_num);
			st.registerOutParameter(2, OracleTypes.VARCHAR);
			st.execute();
			result=st.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
