package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class Query_Department {

	public  String[] Query_director(String department_name) {
		Connection conn =null;
		CallableStatement st;
		String[] result = new String[] {"无","无"};
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL department_query(?,?,?)}");
			st.setString(1, department_name);
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
	
//	public static void main(String[] args) {
//		Connection conn =null;
//		Statement st=null;
//		ResultSet rs=null;
//		try {
//			conn=Conn.getconn();
//			st=conn.createStatement();
//			String sql="select * from  \"Department\"";
//			rs=st.executeQuery(sql);
//			while(rs.next()) {
//				String department_name = rs.getString("Department_Name");
//				String dirctor=rs.getString("Director");
//				String address=rs.getString("Address");
//				int phone_num=rs.getInt("Phone_Num");
//				String doctor=rs.getString("Doctor");
//				int department_num=rs.getInt("Department_Num");
//				System.out.println("科室名："+department_name+"--"+"主任："+dirctor+"--"+"地址："+address+"--"+"电话号码："+phone_num+"--"+"医生："+doctor+"--"+"科室编号："+department_num);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//		String[] result=Query_director("内科");
//		System.out.println(result[0]);
//	}	
}
