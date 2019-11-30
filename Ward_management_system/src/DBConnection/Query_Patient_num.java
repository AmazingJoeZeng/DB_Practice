package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Patient_num {
	public  int Query_patient_num(String pat_name) {
		Connection conn =null;
		CallableStatement st;
		int result=0;
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL patient_num_query(?,?)}");
			st.setString(1, pat_name);
			st.registerOutParameter(2, OracleTypes.NUMBER);
			st.execute();
			result=st.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
