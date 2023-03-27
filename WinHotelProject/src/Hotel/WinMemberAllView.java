package Hotel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import memberManager.WInMemberModify;
import memberManager.WinMemberRemove;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class WinMemberAllView extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberAllView dialog = new WinMemberAllView();
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
	public WinMemberAllView() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				ShowMember();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setBounds(100, 100, 490, 593);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		String columnNames[] = {"이름","전화번호","지역"};
	    DefaultTableModel dtm = new DefaultTableModel(columnNames,0) {

	         @Override
	         public boolean isCellEditable(int row, int column) {
	            // TODO Auto-generated method stub
	            return false;
	         }
	         
	      };
		scrollPane.setBounds(0, 10, 476, 556);
		
		panel.add(scrollPane);
		
		table = new JTable(dtm);	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mnuMemberModify = new JMenuItem("회원정보 수정");
		mnuMemberModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WInMemberModify modify=new WInMemberModify(3,table.getValueAt(table.getSelectedRow(),0).toString());
				modify.setModal(true);
				modify.setVisible(true);
			}
		});
		popupMenu.add(mnuMemberModify);
		
		JMenuItem mnuMemberDelete = new JMenuItem("회원정보 삭제");
		mnuMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberRemove remove =new WinMemberRemove(2,table.getValueAt(table.getSelectedRow(),0).toString());
			}
		});
		popupMenu.add(mnuMemberDelete);
		
		
		ShowMember();
	}

	private void ShowMember() {
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = 
	               DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");	         
	         //=============================================      
	         String sql = "select * from membertbl";
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql);
	         DefaultTableModel dtm = (DefaultTableModel)table.getModel();
	         dtm.setRowCount(0);
	         while(rs.next()) {
	        	 
	            Vector <String> vec = new Vector<>();
	            for(int i=1;i<=6;i++) {
	               if(i!=1 && i!=2 && i!=5 )
	                  vec.add(rs.getString(i));
	            }
	            dtm.addRow(vec);
	         }
	         rs.close();
	         stmt.close();
	         //==============================================
	         con.close();
	      } catch (ClassNotFoundException e1) {
	         System.out.println("JDBC 드라이버 로드 에러");
	      } catch (SQLException e1) {
	         System.out.println("DB 연결 오류");
	      } 
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
