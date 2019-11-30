package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Doctor_num {
	public  int Query_doc(String doc_name) {
		Connection conn =null;
		CallableStatement st;
		int result=0;
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL doctor_num_query(?,?)}");
			st.setString(1, doc_name);
			st.registerOutParameter(2, OracleTypes.VARCHAR);
			st.execute();
			result=st.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
