 

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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Hotelinformation.HotelDelete;
import Hotelinformation.HotelModify;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class WinHotelAllShow extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinHotelAllShow dialog = new WinHotelAllShow();
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
	public WinHotelAllShow() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				showTable();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setBounds(100, 100, 566, 546);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String columnNames[] = {"이름","가격","지역"};
	    DefaultTableModel dtm = new DefaultTableModel(columnNames,0) {

	         @Override
	         public boolean isCellEditable(int row, int column) {
	            // TODO Auto-generated method stub
	            return false;
	         }
	         
	      };
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					ShowHotel hotel=new ShowHotel();
					hotel.setModal(true);
					hotel.setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mnuModify = new JMenuItem("호텔정보 수정하기");
		mnuModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelModify modify = new HotelModify(5,table.getValueAt(table.getSelectedRow(),0).toString());
				modify.setModal(true);
				modify.setVisible(true);
			}
		});
		popupMenu.add(mnuModify);
		
		JMenuItem mnuDelete = new JMenuItem("호텔정보 삭제하기");
		mnuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelDelete delete = new HotelDelete(6,table.getValueAt(table.getSelectedRow(),0).toString());
			}
		});
		popupMenu.add(mnuDelete);
		
		showTable();

	}
	private void showTable() {
		
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = 
	               DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root","1234");
	         
	         //=============================================      
	         String sql = "select * from hotelTBL";
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql);
	         DefaultTableModel dtm = (DefaultTableModel)table.getModel();
	         dtm.setRowCount(0);
	         while(rs.next()) {
	            Vector <String> vec = new Vector<>();
	            for(int i=1;i<=5;i++) {
	               if(i!=1 && i!=5 && i!=6)
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
