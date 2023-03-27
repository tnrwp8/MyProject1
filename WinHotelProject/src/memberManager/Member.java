package memberManager;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Member extends JPanel {
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfMobile;
	private JPasswordField passwordFild;
	private JPasswordField passwordField;
	private JButton btmMemberModify;
	private JButton btnMemberAdd;
	private JButton btnMemberRemove;
	private JLabel lblConfirm;
	private JButton btnDup;
	private int type;
	private JLabel lblImage;
	private String filepath;
	private JComboBox cbregionc;
	private JLabel lblPWCheak;
	public Member() {
		setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					if(type != 2) {//삭제가아니라면
						lblImage.setToolTipText("");
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter =new FileNameExtensionFilter("그림파일", "png","gif","jpg");
					chooser.setFileFilter(filter);
					chooser.showOpenDialog(null);
					
					int ret = chooser.showOpenDialog(null);
					if(ret==JFileChooser.APPROVE_OPTION) {
						filepath = chooser.getSelectedFile().getPath();
						ImageIcon icon =new ImageIcon(filepath);
						Image image = icon.getImage();
						image = image.getScaledInstance(150,200, Image.SCALE_SMOOTH);
						ImageIcon pic = new ImageIcon(image);
						lblImage.setIcon(pic);
						}
					}
				}
			}
		});
		lblImage.setToolTipText("더블클릭하여 사진을 선택하시요");
		lblImage.setOpaque(true);
		lblImage.setBackground(Color.YELLOW);
		lblImage.setBounds(12, 22, 150, 200);
		add(lblImage);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(174, 34, 57, 15);
		add(lblId);
		
		JLabel lblPw = new JLabel("PW :");
		lblPw.setBounds(174, 64, 57, 15);
		add(lblPw);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(174, 94, 57, 15);
		add(lblName);
		
		JLabel lblMobile = new JLabel("Mobile :");
		lblMobile.setBounds(174, 124, 57, 15);
		add(lblMobile);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(242, 31, 116, 21);
		add(tfID);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfMobile.requestFocus();
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(242, 91, 166, 21);
		add(tfName);
		
		tfMobile = new JTextField();
		tfMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cbregionc.requestFocus();
				}
			}
		});
		tfMobile.setColumns(10);
		tfMobile.setBounds(242, 121, 116, 21);
		add(tfMobile);
		
		passwordFild = new JPasswordField();
		passwordFild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});
		passwordFild.setBounds(243, 61, 115, 21);
		add(passwordFild);
		
		btnDup = new JButton("중복확인");
		btnDup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Memberdoublecheck()) {
					JOptionPane.showMessageDialog(null,"중복된아이디입니다");
					tfID.requestFocus();
				}else {
					JOptionPane.showMessageDialog(null,"사용가능한아이디입니다");
					passwordFild.requestFocus();
				}
			}
		});
		btnDup.setBounds(370, 30, 96, 23);
		add(btnDup);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(new String(passwordFild.getPassword()).equals(new String(passwordField.getPassword()))) {
						lblPWCheak.setText("비밀번호 일치");
						lblPWCheak.setForeground(new Color(0, 255, 0));
						tfName.setEnabled(true);
						tfName.requestFocus();
					}else {
						lblPWCheak.setText("비밀번호 불일치");
						lblPWCheak.setForeground(Color.RED);
						passwordFild.requestFocus();
					}
				}
			}
		});
		passwordField.setBounds(439, 61, 115, 21);
		add(passwordField);
		
		lblConfirm = new JLabel("Confirm");
		lblConfirm.setBounds(380, 64, 57, 15);
		add(lblConfirm);
		
		btnMemberAdd = new JButton("회원 추가");
		btnMemberAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMember();
				Clear();
			}
		});
		btnMemberAdd.setBounds(193, 199, 98, 23);
		add(btnMemberAdd);
		
		btmMemberModify = new JButton("회원변겅");
		btmMemberModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonMembermodify();
			}
		});
		btmMemberModify.setBounds(303, 199, 99, 23);
		add(btmMemberModify);
		
		btnMemberRemove = new JButton("회원 탈퇴");
		btnMemberRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonMemberDelete();
			}
		});
		btnMemberRemove.setBounds(417, 199, 96, 23);
		add(btnMemberRemove);
		
		JLabel lblRegion = new JLabel("regionc:");
		lblRegion.setBounds(174, 159, 57, 15);
		add(lblRegion);
		
		cbregionc = new JComboBox();
		cbregionc.setModel(new DefaultComboBoxModel(new String[] {"인천", "서울", "경기"}));
		cbregionc.setSelectedIndex(1);
		cbregionc.setBounds(242, 152, 69, 23);
		add(cbregionc);
		
		lblPWCheak = new JLabel("중복체크");
		lblPWCheak.setBounds(567, 64, 92, 15);
		add(lblPWCheak);
	}

		protected void ButtonMemberDelete() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
				
				//=============================================		
				String sql = "delete from membertbl where mid='";
				sql = sql + tfID.getText() +"'";
				Statement stmt = con.createStatement();
				if(stmt.executeUpdate(sql) > 0) {
					JOptionPane.showMessageDialog(null, "삭제 완료");
					;
				}else {
					JOptionPane.showMessageDialog(null, "삭제 오류");
				}
				
				
				stmt.close();
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			}					
	}

		protected void ButtonMembermodify() {
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
				
				//=============================================		
				String sql = "Update memberTBL set mpw=?, mname=?, mobile=?, mpic=?, regionc=? where mid=? ";
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        
				pstmt.setString(2,String.valueOf(passwordFild.getPassword()));
				pstmt.setString(3,tfName.getText());
				pstmt.setString(4,tfMobile.getText());
				pstmt.setString(5,filepath);
				pstmt.setString(6,(String)cbregionc.getSelectedItem());		       
				
		        pstmt.executeUpdate();
			
				pstmt.close();
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			} 			
		}	
		

		protected void Clear() {
			tfID.setText("");
			tfMobile.setText("");
			tfName.setText("");
			passwordFild.setText("");
			passwordField.setText("");
			lblImage.setIcon(null);
	}					
		protected Boolean Memberdoublecheck() {
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
				
				//=============================================		
				String sql = "select count(*) from memberTBL where mid= '"+ tfID.getText() +"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					if(rs.getInt(1)==1)//중복
						return true;
					else
						return false;
					
					
				}
				rs.close();
				stmt.close();	
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			} 
			return false;
		
	}

		protected void AddMember() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
				
				//=============================================		
				String sql = "insert into membertbl values(?,?,?,?,?,?)";
				PreparedStatement pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1,tfID.getText());
				pstmt.setString(2,String.valueOf(passwordFild.getPassword()));
				pstmt.setString(3,tfName.getText());
				pstmt.setString(4,tfMobile.getText());
				pstmt.setString(5,filepath);
				pstmt.setString(6,(String)cbregionc.getSelectedItem());
				
				
				pstmt.execute();	
				pstmt.close();
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			} 
		
	}

		public Member(int sType, String sname) {
			this();
			type=sType;
			
			if(type == 1) {
				btnMemberRemove.setVisible(false);
				btmMemberModify.setVisible(false);			
			}else if(type==2){
				btnMemberAdd.setVisible(false);
				btmMemberModify.setVisible(false);	
				btnDup.setVisible(false);
				lblImage.setToolTipText(null);
				MemberDelete(sname);
			}else if(type==3){
				btnMemberAdd.setVisible(false);
				btnMemberRemove.setVisible(false);
				Membermodify(sname);
			}else {
				btmMemberModify.setVisible(false);
				btnMemberAdd.setVisible(false);
				btnMemberRemove.setVisible(false);
				btnDup.setVisible(false);
			}
		
	
	}

		private void MemberDelete(String sname) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = 
							DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
					
					//=============================================		
					String sql = "delete from membertbl where mname= '"+ sname +"'";				
					Statement stmt = con.createStatement();
					if(stmt.executeUpdate(sql) > 0) {
						JOptionPane.showMessageDialog(null, "삭제 완료");
						;
					}else {
						JOptionPane.showMessageDialog(null, "삭제 오류");
					}
					
					
					stmt.close();
					//==============================================
					con.close();
				} catch (ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버 로드 에러");
				} catch (SQLException e1) {
					System.out.println("DB 연결 오류");
				}			
		}

		private void Membermodify(String sname) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
				
				//=============================================		
				String sql = "SELECT * FROM memberTBL where mname='"+sname+"'";		
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println(sql);
				
				while(rs.next()) {
					tfID.setText(rs.getString(1));
					passwordFild.setText(rs.getString(2));
					tfName.setText(rs.getString(3));
					tfMobile.setText(rs.getString(4));			
					filepath=rs.getString(5);
					ImageIcon icon = new ImageIcon(filepath);
					Image img = icon.getImage();
					img = img.getScaledInstance(382, 286, Image.SCALE_SMOOTH);
					ImageIcon image = new ImageIcon(img);
					lblImage.setIcon(image);
					cbregionc.setSelectedItem(rs.getString(6));
					
					}
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e1) {
				System.out.println("DB 연결 오류");
			}
		}
}