package Hotelinformation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;

import memberManager.Member;

public class HotelAdd extends JDialog {

	/**
	 * Create the dialog.
	 */
	public HotelAdd(int typeHotelAdd) {
		setTitle("호텔 추가");
		setBounds(100, 100, 725, 568);
		
		Hotel hotel = new Hotel(4,"");
		getContentPane().add(hotel, BorderLayout.CENTER);
	}	
}
