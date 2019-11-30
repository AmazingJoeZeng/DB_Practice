package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Login_Patient {
	
	public  void Login(String name,String sex,int age,String doctor,String bing,String department,int roomnum,int bednum) {
		Connection conn =null;
		CallableStatement st;
		try {
			conn=Conn.getconn();
			st = conn.prepareCall("{CALL LOGIN_PATIENT(?,?,?,?,?,?,?,?)}");
			st.setString(1,name);   //姓名
			st.setString(2, sex);    //性别
			st.setInt(3, age);      //年龄
			st.setString(4,doctor);  //医生
			st.setString(5,bing);    //诊断
			st.setString(6,department);   //科室
			st.setInt(7, roomnum);      //病房号
			st.setInt(8, bednum);       //病床号
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Login Fail!");
		}
	}
}
