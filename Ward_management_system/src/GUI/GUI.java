package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import DBConnection.Delete_Patient;
import DBConnection.Login_Patient;
import DBConnection.Query_Department;
import DBConnection.Query_Docnumtopatient;
import DBConnection.Query_Doctor;
import DBConnection.Query_Doctor_name;
import DBConnection.Query_Doctor_num;
import DBConnection.Query_Patient;
import DBConnection.Query_Patient_num;
import DBConnection.Query_dep_name;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.ImageIcon;


public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextArea textArea;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	//判断是否为数字
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
     }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./ico/1.png"));
		frame.setTitle("病房管理系统");
		frame.setBounds(100, 100, 1043, 957);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("查询相关科室：");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(39, 40, 150, 18);
		frame.getContentPane().add(label);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"内科", "外科", "儿科", "传染病科", "妇产科", "男科", "精神心理科", "骨科"}));
		comboBox.setBounds(172, 37, 139, 24);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query_Department a= new Query_Department();
				String temp[]=a.Query_director(comboBox.getSelectedItem().toString());
				textField_2.setText(temp[0]);
				textField_3.setText(temp[1]);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(325, 36, 113, 27);
		frame.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("查询相关医师：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(39, 90, 156, 38);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField.setText("请输入医生姓名或编号");
		textField.setBounds(187, 93, 244, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("查询");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");      //主治病人文本栏
				Query_Doctor a= new Query_Doctor();	
				Query_Doctor_num b = new Query_Doctor_num();
				Query_Doctor_name c = new Query_Doctor_name();
				Query_Docnumtopatient d = new Query_Docnumtopatient();
				if(isNumeric(textField.getText()))      //输入的为编号
				{
					String temp_name=c.Query_name(Integer.parseInt(textField.getText()));
				    String temp[]=a.Query_doc(Integer.parseInt(textField.getText()));
				    textField_4.setText(temp[1]);
				    textField_5.setText(temp[0]);
				    textField_6.setText(temp_name);
				    ResultSet rs=d.docnum_patient(Integer.parseInt(textField.getText()));
				    int i=0;
				    String patient_data="";
				    try {
						while (rs.next()) {
							patient_data=patient_data+"姓名："+rs.getString(1)+"-----"+"病历号："+rs.getInt(2)+"\r\n";
						    i++;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    textArea.append("主治病人："+"\r\n");
				    textArea.append(patient_data);
				    textField_8.setText(i+"");
				}
				else     //当输入的为姓名
				{
					int doc_num=b.Query_doc(textField.getText());
					String temp1[]=a.Query_doc(doc_num);
					textField_4.setText(temp1[1]);
					textField_5.setText(temp1[0]);
					textField_6.setText(doc_num+"");
					 ResultSet rs=d.docnum_patient(doc_num);
					    int i=0;
					    String patient_data="";
					    try {
							while (rs.next()) {
								patient_data=patient_data+"姓名："+rs.getString(1)+"-----"+"病历号："+rs.getInt(2)+"\r\n";
							    i++;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    textArea.append("主治病人："+"\r\n");
					    textArea.append(patient_data);
					    textField_8.setText(i+"");
				}
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(452, 96, 113, 27);
		frame.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("住院病人查询：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(39, 369, 150, 46);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setText("请输入病人姓名或病历号");
		textField_1.setBounds(187, 376, 252, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_2 = new JButton("查询");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query_Patient a=new Query_Patient();
				Query_dep_name b = new Query_dep_name();
				Query_Doctor_name c = new Query_Doctor_name();
				Query_Patient_num d = new Query_Patient_num();
				if(isNumeric(textField_1.getText()))         //数字
				{
					String [] temp_patient=a.Query_pat(Integer.parseInt(textField_1.getText()));
					int dep_num=Integer.parseInt(temp_patient[0]);
					int doc_num=Integer.parseInt(temp_patient[1]);
					String dep_name=b.Query_name(dep_num);
					textField_7.setText(dep_name);
					String doc_name=c.Query_name(doc_num);
					textField_9.setText(doc_name);
					textField_11.setText(temp_patient[2]);
					textField_12.setText(temp_patient[4]);
					textField_13.setText(temp_patient[3]);
					textField_10.setText(temp_patient[5]);
				}
				else 
				{
					int pat_num=d.Query_patient_num(textField_1.getText());
					String [] temp_patient=a.Query_pat(pat_num);
					int dep_num=Integer.parseInt(temp_patient[0]);
					int doc_num=Integer.parseInt(temp_patient[1]);
					String dep_name=b.Query_name(dep_num);
					textField_7.setText(dep_name);
					String doc_name=c.Query_name(doc_num);
					textField_9.setText(doc_name);
					textField_11.setText(temp_patient[2]);
					textField_12.setText(temp_patient[4]);
					textField_13.setText(temp_patient[3]);
					textField_10.setText(pat_num+"");
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(452, 377, 113, 30);
		frame.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("科室主任：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(486, 30, 113, 38);
		frame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_2.setBounds(593, 31, 139, 35);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_4 = new JLabel("是否剩余病房：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(762, 32, 155, 35);
		frame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_3.setBounds(915, 29, 73, 38);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_5 = new JLabel("所属科室：");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(39, 144, 113, 24);
		frame.getContentPane().add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_4.setBounds(141, 141, 125, 30);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_6 = new JLabel("技术职称：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(293, 141, 113, 30);
		frame.getContentPane().add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_5.setBounds(392, 141, 139, 30);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_7 = new JLabel("姓名或编号：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		label_7.setBounds(567, 140, 139, 33);
		frame.getContentPane().add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_6.setBounds(688, 140, 139, 32);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_8 = new JLabel("执行结果:");
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		label_8.setBounds(39, 191, 100, 30);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("共有");
		label_9.setFont(new Font("宋体", Font.PLAIN, 20));
		label_9.setBounds(841, 233, 40, 24);
		frame.getContentPane().add(label_9);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("方正姚体", Font.PLAIN, 20));
		textField_8.setBounds(889, 229, 64, 38);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JLabel label_10 = new JLabel("人");
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));
		label_10.setBounds(968, 231, 43, 30);
		frame.getContentPane().add(label_10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(141, 191, 686, 165);
		frame.getContentPane().add(textArea);
		
		JLabel label_11 = new JLabel("就诊科室：");
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		label_11.setBounds(39, 439, 100, 27);
		frame.getContentPane().add(label_11);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_7.setBounds(141, 439, 126, 31);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label_12 = new JLabel("主治医师：");
		label_12.setFont(new Font("宋体", Font.PLAIN, 20));
		label_12.setBounds(293, 439, 100, 27);
		frame.getContentPane().add(label_12);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_9.setBounds(392, 439, 139, 31);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("姓名或编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(567, 436, 139, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_10.setBounds(688, 437, 139, 35);
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
		JLabel label_13 = new JLabel("诊断：");
		label_13.setFont(new Font("宋体", Font.PLAIN, 20));
		label_13.setBounds(39, 506, 78, 24);
		frame.getContentPane().add(label_13);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_11.setBounds(103, 506, 163, 33);
		frame.getContentPane().add(textField_11);
		textField_11.setColumns(10);
		
		JLabel label_14 = new JLabel("病房号：");
		label_14.setFont(new Font("宋体", Font.PLAIN, 20));
		label_14.setBounds(293, 502, 85, 33);
		frame.getContentPane().add(label_14);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_12.setBounds(392, 506, 139, 33);
		frame.getContentPane().add(textField_12);
		textField_12.setColumns(10);
		
		JLabel label_15 = new JLabel("床号：");
		label_15.setFont(new Font("宋体", Font.PLAIN, 20));
		label_15.setBounds(567, 502, 64, 33);
		frame.getContentPane().add(label_15);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_13.setBounds(632, 506, 195, 33);
		frame.getContentPane().add(textField_13);
		textField_13.setColumns(10);
		
		JLabel label_16 = new JLabel("病人入院登记：");
		label_16.setForeground(Color.RED);
		label_16.setFont(new Font("宋体", Font.PLAIN, 22));
		label_16.setBounds(39, 561, 177, 38);
		frame.getContentPane().add(label_16);
		
		JLabel label_17 = new JLabel("病人姓名：");
		label_17.setFont(new Font("宋体", Font.PLAIN, 20));
		label_17.setBounds(39, 619, 100, 27);
		frame.getContentPane().add(label_17);
		
		textField_14 = new JTextField();
		textField_14.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_14.setBounds(141, 612, 125, 34);
		frame.getContentPane().add(textField_14);
		textField_14.setColumns(10);
		
		JLabel label_18 = new JLabel("病人性别：");
		label_18.setFont(new Font("宋体", Font.PLAIN, 20));
		label_18.setBounds(293, 620, 100, 24);
		frame.getContentPane().add(label_18);
		
		textField_15 = new JTextField();
		textField_15.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_15.setBounds(392, 617, 139, 34);
		frame.getContentPane().add(textField_15);
		textField_15.setColumns(10);
		
		JLabel label_19 = new JLabel("主治医师：");
		label_19.setFont(new Font("宋体", Font.PLAIN, 20));
		label_19.setBounds(39, 684, 100, 24);
		frame.getContentPane().add(label_19);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_16.setBounds(141, 681, 125, 34);
		frame.getContentPane().add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("病人年龄：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(567, 620, 100, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_17.setBounds(670, 612, 139, 37);
		frame.getContentPane().add(textField_17);
		textField_17.setColumns(10);
		
		JLabel label_20 = new JLabel("诊断：");
		label_20.setFont(new Font("宋体", Font.PLAIN, 20));
		label_20.setBounds(293, 685, 64, 23);
		frame.getContentPane().add(label_20);
		
		textField_18 = new JTextField();
		textField_18.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_18.setBounds(359, 684, 172, 31);
		frame.getContentPane().add(textField_18);
		textField_18.setColumns(10);
		
		JLabel label_21 = new JLabel("所属科室：");
		label_21.setFont(new Font("宋体", Font.PLAIN, 20));
		label_21.setBounds(568, 688, 100, 26);
		frame.getContentPane().add(label_21);
		
		JLabel lblNewLabel_2 = new JLabel("所在病房号：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(39, 750, 125, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_20 = new JTextField();
		textField_20.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_20.setBounds(172, 738, 150, 38);
		frame.getContentPane().add(textField_20);
		textField_20.setColumns(10);
		
		JLabel label_22 = new JLabel("所在病床号：");
		label_22.setFont(new Font("宋体", Font.PLAIN, 20));
		label_22.setBounds(359, 749, 125, 24);
		frame.getContentPane().add(label_22);
		
		textField_21 = new JTextField();
		textField_21.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_21.setBounds(498, 738, 169, 38);
		frame.getContentPane().add(textField_21);
		textField_21.setColumns(10);
		
		JLabel label_23 = new JLabel("病人出院登记：");
		label_23.setForeground(Color.RED);
		label_23.setFont(new Font("宋体", Font.PLAIN, 22));
		label_23.setBounds(39, 802, 177, 43);
		frame.getContentPane().add(label_23);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"内科", "外科", "儿科", "传染病科", "妇产科", "男科", "精神心理科", "骨科"}));
		comboBox_1.setBounds(670, 683, 125, 31);
		frame.getContentPane().add(comboBox_1);
		
		JButton button_3 = new JButton("登记");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Login_Patient a=new Login_Patient();
			     String L_name=textField_14.getText();
			     String L_sex=textField_15.getText();
			     int L_age=Integer.parseInt(textField_17.getText());
			     String L_doctor=textField_16.getText();
			     String L_bing=textField_18.getText();
			     String L_department=comboBox_1.getSelectedItem().toString();
			     int L_roomnum=Integer.parseInt(textField_20.getText());
			     int L_bednum=Integer.parseInt(textField_21.getText());
			     a.Login(L_name, L_sex, L_age, L_doctor, L_bing, L_department, L_roomnum, L_bednum);
			     textArea.setText("");
			     textArea.append("病人入院登记成功！");
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(703, 738, 106, 38);
		frame.getContentPane().add(button_3);
		
		JLabel label_24 = new JLabel("请输入病人编号：");
		label_24.setFont(new Font("宋体", Font.PLAIN, 20));
		label_24.setBounds(39, 858, 172, 38);
		frame.getContentPane().add(label_24);
		
		textField_22 = new JTextField();
		textField_22.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_22.setBounds(211, 860, 139, 38);
		frame.getContentPane().add(textField_22);
		textField_22.setColumns(10);
		
		JButton button_4 = new JButton("确认");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Delete_Patient a= new Delete_Patient();
				int p_num=Integer.parseInt(textField_22.getText());
				a.Delete_P(p_num);
				textArea.setText("");
			    textArea.append("病人出院成功！");
			}
		});
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(384, 866, 113, 27);
		frame.getContentPane().add(button_4);
		
	}
}
