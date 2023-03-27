package region;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Seoul extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seoul dialog = new Seoul();
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
	public Seoul() {
		Random random = new Random();
		setBounds(100, 100, 450, 612);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 73, 436, 502);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		int[] j = new int[3];
		int a=0;
		int count=0;
		boolean bExist=false;
		while( true ) { 
			bExist=false;
			j[a] = random.nextInt(4,7);		
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
		    MiniregionHotel hotel = new MiniregionHotel("서울",j[i]);
		    panel.add(hotel);
		}
		getContentPane().add(panel);		
		
		JLabel lblNewLabel = new JLabel("서울 호텔");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 27));
		lblNewLabel.setBounds(63, 10, 307, 53);
		getContentPane().add(lblNewLabel);
		}
	}


