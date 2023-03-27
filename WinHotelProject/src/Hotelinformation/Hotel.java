package Hotelinformation;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Hotel extends JPanel {
	private JTextField tfHotelName;
	private JTextField tfPrice;
	private JTextField tfregion;
	private int type;
	private JButton btnHotelAdd;
	private JButton btnHotelModify;
	private JButton btnHotelDelete;
	private JLabel lblHotelPic;
	protected String filePath;
	private JTextArea taInformation;
	
	public Hotel() {
		setLayout(null);
		
		lblHotelPic = new JLabel("");
		lblHotelPic.setToolTipText("더블클릭하여 사진선택");
		lblHotelPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일", "png","jpg","gif","bpm");
					chooser.setFileFilter(filter);
					int ret =chooser.showOpenDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION) {
						filePath = chooser.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(filePath);
						Image img = icon.getImage();
						img = img.getScaledInstance(382, 286, Image.SCALE_SMOOTH);
						ImageIcon image = new ImageIcon(img);
						lblHotelPic.setIcon(image);
						filePath = filePath.replaceAll("\\\\","\\\\\\\\");
					}			
				}
			}
		});
		lblHotelPic.setOpaque(true);
		lblHotelPic.setBackground(Color.YELLOW);
		lblHotelPic.setBounds(33, 26, 382, 286);
		add(lblHotelPic);
		
		JLabel lblName = new JLabel("호텔이름 :");
		lblName.setBounds(427, 26, 80, 26);
		add(lblName);
		
		tfHotelName = new JTextField();
		tfHotelName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPrice.requestFocus();
				}
			}
		});
		tfHotelName.setColumns(10);
		tfHotelName.setBounds(505, 26, 148, 26);
		add(tfHotelName);
		
		JLabel lblPrice = new JLabel("가격 :");
		lblPrice.setBounds(427, 99, 50, 26);
		add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfregion.requestFocus();
				}
			}
		});
		tfPrice.setColumns(10);
		tfPrice.setBounds(505, 100, 148, 26);
		add(tfPrice);
		
		JLabel lblRegion = new JLabel("지역 :");
		lblRegion.setBounds(427, 165, 80, 26);
		add(lblRegion);
		
		tfregion = new JTextField();
		tfregion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					taInformation.requestFocus();
				}
			}
		});
		tfregion.setColumns(10);
		tfregion.setBounds(505, 166, 146, 26);
		add(tfregion);
		
		taInformation = new JTextArea();
		taInformation.setToolTipText("더블클릭하여 사진선택");
		taInformation.setBounds(33, 349, 620, 137);
		add(taInformation);
		
		JLabel lblNewLabel_1 = new JLabel("호텔정보");
		lblNewLabel_1.setBounds(33, 322, 89, 17);
		add(lblNewLabel_1);
		
		btnHotelAdd = new JButton("호텔등록하기");
		btnHotelAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelAdd();
				Clear();
			}
		});
		btnHotelAdd.setBounds(427, 206, 226, 40);
		add(btnHotelAdd);
		
		btnHotelModify = new JButton("호텔 수정하기");
		btnHotelModify.setBounds(427, 252, 226, 40);
		add(btnHotelModify);
		
		btnHotelDelete = new JButton("호텔 삭제하기");
		btnHotelDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelDelete();
			}
		});
		btnHotelDelete.setBounds(427, 299, 226, 40);
		add(btnHotelDelete);
	}
	
	protected void HotelDelete() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
			
			//=============================================		
			String sql = "delete from hoteltbl where mid='";
			sql = sql + tfHotelName.getText() +"'";
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

	protected void Clear() {
		tfHotelName.setText("");
		taInformation.setText("");
		tfPrice.setText("");
		tfregion.setText("");
		
		lblHotelPic.setIcon(new ImageIcon());
		tfHotelName.requestFocus();
	}

	protected void HotelAdd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================		
			String sql = "insert into hoteltbl values(?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,null);
			pstmt.setString(2,tfHotelName.getText());
			pstmt.setInt(3,Integer.parseInt(tfPrice.getText()));
			pstmt.setString(4,tfregion.getText());
			pstmt.setString(5,taInformation.getText());
			pstmt.setString(6,filePath);
			
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

	public Hotel(int sType, String sname) {
		this();		
		type = sType;
		if(type==4) {
			btnHotelModify.setVisible(false);
			btnHotelDelete.setVisible(false);
		}
		if(type==5) {
			btnHotelAdd.setVisible(false);
			btnHotelDelete.setVisible(false);
			ModifyRecord(sname);
		}
		if(type==6) {		
			btnHotelModify.setVisible(false);
			btnHotelAdd.setVisible(false);
			DeleteRecord(sname);
		}
	}
	
	private void DeleteRecord(String sname) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
			
			//=============================================		
			String sql = "delete from hoteltbl where Name='"+ sname +"'";
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

	private void ModifyRecord(String sname) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================		
			String sql = "SELECT * FROM hoteltbl where Name= '"+sname+"' ";		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				tfHotelName.setText(rs.getString(2));
				tfPrice.setText(rs.getString(3));
				tfregion.setText(rs.getString(4));
				taInformation.setText(rs.getString(5));
				filePath=rs.getString(6);
				ImageIcon icon = new ImageIcon(filePath);	
				Image img = icon.getImage();
				img = img.getScaledInstance(382, 286, Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(img);
				lblHotelPic.setIcon(image);	
				System.out.println(filePath);
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
