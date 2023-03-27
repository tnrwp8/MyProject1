package Hotelinformation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;

public class HotelModify extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelModify dialog = new HotelModify(3,"");
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
	 * @param typeHotelModify 
	 */
	

	public HotelModify(int typeHotelModify, String sname) {
		setBounds(100, 100, 679, 537);
		
		Hotel hotel = new Hotel(typeHotelModify,sname);
		
		System.out.println(sname);
		getContentPane().add(hotel, BorderLayout.CENTER);
	}

	


}
