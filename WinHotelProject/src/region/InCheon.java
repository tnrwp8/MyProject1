package region;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class InCheon extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InCheon dialog = new InCheon();
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
	public InCheon() {
		Random random = new Random();
		setBounds(100, 100, 450, 612);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("인천호텔");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 27));
		lblNewLabel.setBounds(63, 10, 307, 53);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 73, 436, 502);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		int[] j = new int[3];
		int a=0;
		int count=0;
		boolean bExist=false;
		while( true ) { 
			bExist=false;
			j[a] = random.nextInt(1,4);		
			for(int b=0; b<a; b++) {
				if(j[a] == j[b]) {
					bExist=true;
					break;
				}
			}
			if(bExist==false) {
				count++;
				a++;
			}
			if(count==3) break;
		}
		
		for(int i = 0; i <= 2; i++) {
		    MiniregionHotel hotel = new MiniregionHotel("인천",j[i]);
		    panel.add(hotel);
		}
		getContentPane().add(panel);		
		
		
	}			

}
