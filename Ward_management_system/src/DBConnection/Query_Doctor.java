package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Doctor {

	public  String[] Query_doc(int doc_num) {
		Connection conn =null;
		CallableStatement st;
		String[] result = new String[] {"无","无"};
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL doctor_query(?,?,?)}");
			st.setInt(1,doc_num);
			st.registerOutParameter(2, OracleTypes.VARCHAR);
			st.registerOutParameter(3, OracleTypes.VARCHAR);
			st.execute();
			result[0]=st.getString(2);
			result[1]=st.getString(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
