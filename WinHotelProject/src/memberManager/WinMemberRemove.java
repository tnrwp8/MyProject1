package memberManager;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class WinMemberRemove extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberRemove dialog = new WinMemberRemove(2,"");
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
	 * @param typeRemove 
	 */
	public WinMemberRemove(int typeRemove, String sname) {
		setBounds(100, 100, 651, 300);
		
		Member member=new Member(2,sname);
		getContentPane().add(member, BorderLayout.CENTER);	}

}
