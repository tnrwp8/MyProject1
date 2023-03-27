package Hotelinformation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;

public class HotelDelete extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelDelete dialog = new HotelDelete(6,"");
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
	 * @param typeHotelDelete 
	 */	
	public HotelDelete(int typeHotelDelete, String sname) {
		setBounds(100, 100, 684, 537);
		
		Hotel hotel = new Hotel(6,sname);
		getContentPane().add(hotel, BorderLayout.CENTER);	
		}

}
