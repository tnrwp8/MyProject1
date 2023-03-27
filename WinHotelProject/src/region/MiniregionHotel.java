package region;

import javax.swing.JPanel;

import Hotel.ShowHotel;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiniregionHotel extends JPanel {
	private JLabel lblHotelPic;
	private JLabel lblRoomName;
	private JLabel lblPrice;
	private String filePath;
	private int type=1;

	public MiniregionHotel() {
		setLayout(null);
		
		lblHotelPic = new JLabel("New label");
		lblHotelPic.setOpaque(true);
		lblHotelPic.setBackground(Color.YELLOW);
		lblHotelPic.setBounds(12, 10, 195, 122);
		add(lblHotelPic);
		
		lblRoomName = new JLabel("방이름 :");
		lblRoomName.setBounds(237, 15, 90, 26);
		add(lblRoomName);
		
		JButton btnShowRoom = new JButton("자세히 보기..");
		btnShowRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowHotel hotel = new ShowHotel(lblRoomName.getText(),1);
				hotel.setModal(true);
				hotel.setVisible(true);
			}
		});
		btnShowRoom.setBounds(310, 100, 107, 32);
		add(btnShowRoom);
		
		lblPrice = new JLabel("가격 :");
		lblPrice.setBounds(236, 64, 91, 26);
		add(lblPrice);
	}

	public MiniregionHotel(String sRegion, int j) {
		this();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb", "root","1234");
			
			//=============================================	
			
			String sql = "SELECT * FROM hoteltbl where region ='"+ sRegion +"' AND idx ='"+j +"'";		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				lblRoomName.setText(rs.getString(2));
				lblPrice.setText(rs.getString(3));
				filePath=rs.getString(6);
				ImageIcon icon = new ImageIcon(filePath);	
				Image img = icon.getImage();
				img = img.getScaledInstance(382, 286, Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(img);
				lblHotelPic.setIcon(image);	
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
