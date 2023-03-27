package Hotel;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiniHotel extends JPanel {	
	private JLabel lblHotelNames;
	private JLabel lblHotelPrice;
	private JLabel lblPic;
	protected String filePath;

	public MiniHotel() {
		setLayout(null);
		
		lblPic = new JLabel("New label");
		lblPic.setBackground(new Color(255, 255, 0));
		lblPic.setOpaque(true);
		lblPic.setBounds(0, 10, 183, 132);
		add(lblPic);
		
		lblHotelNames = new JLabel("New label");
		lblHotelNames.setBounds(10, 152, 50, 15);
		add(lblHotelNames);
		
		lblHotelPrice = new JLabel("New label");
		lblHotelPrice.setBounds(10, 188, 50, 15);
		add(lblHotelPrice);
		
		JButton btnNewButton = new JButton("예약하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowHotel hotel = new ShowHotel(lblHotelNames.getText());
				hotel.setModal(true);
				hotel.setVisible(true);
			}
		});
		btnNewButton.setBounds(92, 152, 91, 67);
		add(btnNewButton);
	}

	public MiniHotel(int idx) {
		this();
				

	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================			
			String sql = "SELECT * FROM hotelTBL where idx='"+ idx +"'";		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				lblHotelNames.setText(rs.getString(2));
				lblHotelPrice.setText(rs.getString(3)+"원");
				filePath=rs.getString(6);
				ImageIcon icon = new ImageIcon(filePath);	
				Image img = icon.getImage();
				img = img.getScaledInstance(382, 286, Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(img);
				lblPic.setIcon(image);	
				System.out.println(filePath);
				}
			stmt.close();
			rs.close();
			//==============================================
			con.close();
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		} 
					
	}

}
