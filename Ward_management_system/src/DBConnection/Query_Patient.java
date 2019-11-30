package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Patient {

	public  String[] Query_pat(int pat_num) {
		Connection conn =null;
		CallableStatement st;
		String[] result = new String[6];
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL PATIENT_QUERY(?,?,?,?,?,?,?)}");
			st.setInt(1,pat_num);
			st.registerOutParameter(2, OracleTypes.NUMBER);
			st.registerOutParameter(3, OracleTypes.NUMBER);
			st.registerOutParameter(4, OracleTypes.VARCHAR);
			st.registerOutParameter(5, OracleTypes.VARCHAR);
			st.registerOutParameter(6, OracleTypes.VARCHAR);
			st.registerOutParameter(7, OracleTypes.VARCHAR);
			st.execute();
			result[0]=String.valueOf(st.getInt(2));   //科室号
			result[1]=String.valueOf(st.getInt(3));    //医生号
			result[2]=st.getString(4);    //诊断
			result[3]=st.getString(5);    //床号
			result[4]=st.getString(6);    //房号
			result[5]=st.getString(7);    //名字
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
