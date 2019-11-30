package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_dep_name {
	public  String Query_name(int dep_num) {
		Connection conn =null;
		CallableStatement st;
		String result="";
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL QUERY_DEP_NAME(?,?)}");
			st.setInt(1, dep_num);
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
