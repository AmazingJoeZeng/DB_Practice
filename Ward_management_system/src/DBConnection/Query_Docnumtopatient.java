package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_Docnumtopatient {

	public ResultSet docnum_patient(int doc_num) {
		Connection conn =null;
		CallableStatement st=null;
		ResultSet rs=null;
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL doc_patient(?,?)}");
			st.setInt(1, doc_num);
			st.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			st.execute();
			rs=(ResultSet)st.getObject(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
