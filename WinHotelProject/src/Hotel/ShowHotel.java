package Hotel;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ShowHotel extends JDialog {
	private JTextField tfHotelName;
	private JTextField tfPrice;
	private JTextField tfRegion;
	private JTextField tfDay;
	private JTextArea taInformation;
	protected String filePath;
	private JLabel lblHotelPic;
	private JLabel lblDay;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowHotel dialog = new ShowHotel();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ShowHotel() {
		setBounds(100, 100, 651, 523);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblHotelPic = new JLabel("");
		lblHotelPic.setOpaque(true);
		lblHotelPic.setBackground(Color.YELLOW);
		lblHotelPic.setBounds(12, 10, 382, 286);
		panel.add(lblHotelPic);
		
		JLabel lblName = new JLabel("호텔이름 :");
		lblName.setBounds(406, 10, 80, 26);
		panel.add(lblName);
		
		tfHotelName = new JTextField();
		tfHotelName.setColumns(10);
		tfHotelName.setBounds(484, 10, 148, 26);
		panel.add(tfHotelName);
		
		JLabel lblPrice = new JLabel("가격 :");
		lblPrice.setBounds(406, 83, 50, 26);
		panel.add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(484, 84, 148, 26);
		panel.add(tfPrice);
		
		JLabel lblRegion = new JLabel("지역 :");
		lblRegion.setBounds(406, 137, 80, 26);
		panel.add(lblRegion);
		
		tfRegion = new JTextField();
		tfRegion.setColumns(10);
		tfRegion.setBounds(484, 138, 146, 26);
		panel.add(tfRegion);
		
		taInformation = new JTextArea();
		taInformation.setToolTipText("더블클릭하여 사진선택");
		taInformation.setBounds(12, 333, 620, 137);
		panel.add(taInformation);
		
		JLabel lblNewLabel_1 = new JLabel("호텔정보");
		lblNewLabel_1.setBounds(12, 306, 89, 17);
		panel.add(lblNewLabel_1);
		
		JButton btnHotelDelete = new JButton("호텔예약하기");
		btnHotelDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellHotle(tfHotelName.getText());
			}
		});
		btnHotelDelete.setBounds(406, 283, 226, 40);
		panel.add(btnHotelDelete);
		
		btnNewButton = new JButton("날짜 지정하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar calendar = new WinCalendar();
				calendar.setModal(true);
				calendar.setVisible(true);				
				tfDay.setText(calendar.getDate());
			}
		});
		btnNewButton.setBounds(484, 220, 141, 23);
		panel.add(btnNewButton);
		
		lblDay = new JLabel(" 날짜 :");
		lblDay.setBounds(406, 193, 50, 15);
		panel.add(lblDay);
		
		tfDay = new JTextField();
		tfDay.setEnabled(false);
		tfDay.setBounds(484, 190, 148, 21);
		panel.add(tfDay);
		tfDay.setColumns(10);

	}

	protected void SellHotle(String HotelName) {
		System.out.println(HotelName);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================		
			String sql ="UPDATE sellTBL SET SellHotel = SellHOtel+1 where HotelName = '"+HotelName+"'";
			Statement pstmt=con.createStatement();		
			pstmt.executeUpdate(sql);		
			pstmt.close();
			
			System.out.println(sql);
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		} 
		
	}

	public ShowHotel(String HotelName) {
		this();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================		
			String sql = "SELECT * FROM hoteltbl where Name= '"+HotelName+"' ";		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				tfHotelName.setText(rs.getString(2));
				tfPrice.setText(rs.getString(3)+"원");
				tfRegion.setText(rs.getString(4));
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

	public ShowHotel(String HotelName, int type) {
		this();
		type=1;
		lblDay.setVisible(false);
		tfDay.setVisible(false);
		btnNewButton.setVisible(false);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================		
			String sql = "SELECT * FROM hoteltbl where Name= '"+HotelName+"' ";		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				tfHotelName.setText(rs.getString(2));
				tfPrice.setText(rs.getString(3));
				tfRegion.setText(rs.getString(4));
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
