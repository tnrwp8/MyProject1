package memberManager;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class WInMemberModify extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WInMemberModify dialog = new WInMemberModify(3,"");
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
	 * @param typeModify 
	 */
	public WInMemberModify(int typeModify) {
		

	}

	public WInMemberModify(int typeModify, String sname) {
		setBounds(100, 100, 655, 300);
			
		Member member=new Member(3,sname);
		getContentPane().add(member, BorderLayout.CENTER);	}

}
