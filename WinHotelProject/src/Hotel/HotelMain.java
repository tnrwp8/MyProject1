package Hotel;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Hotelinformation.HotelAdd;
import memberManager.WinMemberAdd;
import region.Gyeonggi;
import region.InCheon;
import region.Seoul;
import java.awt.Color;

public class HotelMain extends JDialog {
	private final int typeAdd= 1;
	private final int typeRemove= 2;
	private final int typeModify= 3;
	private final int typeHotelAdd =4;
	private final int typeHotelModify=5;
	private final int typeHotelDelete=6;
	private final int InCheon =7;
	private final int Seoul = 8;
	private final int Gyeonnggi = 9;
	private JMenuItem mnuHotelAllShow;
	Random random = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelMain dialog = new HotelMain();
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
	public HotelMain() {
		getContentPane().setBackground(new Color(240, 230, 140));
		setTitle("호텔 예약 프로그램");
		setBounds(100, 100, 665, 715);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnuMember = new JMenu("회원");
		menuBar.add(mnuMember);
		
		JMenuItem mnuMemberAdd = new JMenuItem("회원 등록...");			
		mnuMemberAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberAdd winmemberadd = new WinMemberAdd(typeAdd);
	      		winmemberadd.setModal(true);
	      		winmemberadd.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("로그인");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnuMember.add(mntmNewMenuItem_1);
		mnuMember.add(mnuMemberAdd);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("회원정보 조회");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberAllView allView = new WinMemberAllView();
				allView.setModal(true);
				allView.setVisible(true);
				}
		});
		mnuMember.add(mntmNewMenuItem);
		
		JMenu mnuregion = new JMenu("지역");
		menuBar.add(mnuregion);
		
		JMenuItem mnuIncheon = new JMenuItem("인천");
		mnuIncheon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InCheon incheon = new InCheon();
				incheon.setModal(true);
				incheon.setVisible(true);
			}
		});
		mnuregion.add(mnuIncheon);
		
		JMenuItem mnuSeoul = new JMenuItem("서울");
		mnuSeoul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seoul seoul = new Seoul();
				seoul.setModal(true);
				seoul.setVisible(true);
			}
		});
		mnuregion.add(mnuSeoul);
		
		JMenuItem mnuGyeonggi = new JMenuItem("경기");
		mnuGyeonggi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gyeonggi gyeonggi = new Gyeonggi();
				gyeonggi.setModal(true);
				gyeonggi.setVisible(true);
				
			}
		});
		mnuregion.add(mnuGyeonggi);
		
		JMenu mnuHotel = new JMenu("호텔");
		menuBar.add(mnuHotel);
		
		JMenuItem mnuHotelAdd = new JMenuItem("호텔 등록하기...");
		mnuHotelAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelAdd hoteladd = new HotelAdd(typeHotelAdd);
				hoteladd.setModal(true);
				hoteladd.setVisible(true);
			}
		});
		mnuHotel.add(mnuHotelAdd);
		
		mnuHotelAllShow = new JMenuItem("호텔 전체 보기");
		mnuHotelAllShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinHotelAllShow allshow = new  WinHotelAllShow();
				allshow.setModal(true);
				allshow.setVisible(true);								
			}
		});
		mnuHotel.add(mnuHotelAllShow);
		getContentPane().setLayout(null);
		
		JPanel MiniHotelView = new JPanel();
		MiniHotelView.setBounds(12, 84, 627, 227);	
		MiniHotelView.setLayout(new GridLayout(0, 3, 0, 0));
		
		int[] j = new int[3];
		int a=0;
		int count=0;
		boolean bExist=false;
		while( true ) { 
			bExist=false;
			j[a] = random.nextInt(1, 10);		
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
		    MiniHotel hotel = new MiniHotel(j[i]);
		    MiniHotelView.add(hotel);
		}
		getContentPane().add(MiniHotelView);		
		JPanel MiniHotelView2 = new JPanel();
		MiniHotelView2.setBounds(12, 376, 627, 252); 
		MiniHotelView2.setLayout(new GridLayout(0, 3, 0, 0));	
		int[] y = new int[3];
		y[0]=9;
		y[1]= 3;
		y[2]=6;
		for(int i = 0; i <= 2; i++) {
		    MiniHotel hotel = new MiniHotel(y[i]);
		    MiniHotelView2.add(hotel);
		}
		getContentPane().add(MiniHotelView2);
				
		JPanel titlePane = new JPanel();
		titlePane.setBounds(12, 321, 627, 45);
		getContentPane().add(titlePane);
		titlePane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("이번주 인기호텔 ! !");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(200, 10, 232, 35);
		titlePane.add(lblNewLabel_1);
		
		JPanel TitlePane = new JPanel();
		TitlePane.setBounds(12, 19, 627, 55);
		getContentPane().add(TitlePane);
		TitlePane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("호텔 살펴보기!!");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(192, 10, 232, 35);
		TitlePane.add(lblNewLabel);

	}
}
