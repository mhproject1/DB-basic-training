

/*************************************************************************
 * import ���� *
 * import java.awt.BorderLayout;
 * >> BorderLayout�� �����̳ʸ� North, South, East, West, Center ��� 5���� �������� ������
 * >> �� ������ �ϳ��� ������Ʈ���� ��ġ�� �� �ֵ��� �մϴ�.
 *  
 * import java.awt.Image;
 * >> �̹����� ����� �����ϱ� ���� Ŭ����
 * 
 * import java.awt.event.ActionEvent;
 * >> ��ư�� Ŭ���ǰų� ����Ʈ, �޴� ���� ���õǾ��� �� �߻��ϴ� �̺�Ʈ Ŭ����
 * >> ActionListener �������̽��� actionPerformed() �޼��带 �̿��ؼ� ó���մϴ�. 
 * 
 * import java.awt.event.ActionListener;
 * >> �̺�Ʈ �������̽�
 * 
 * import java.sql.Connection;
 * >> Connection ��ü�� �ڵ��ϼ����� import �ϱ� ���� java ǥ�� java.sql.Connection Ŭ����
 * 
 * import java.sql.DriverManager;
 * >> java.sql�� �������̽����� ����ؼ� �޼ҵ��� ��ü�� ������ JDBC ����̹� Ŭ����
 * 
 * import java.sql.ResultSet;
 * >> select ���� ���� �� executeQuery() �޼��带 ����ϸ�
 * >> ���� ����� java.sql.ResultSet������ �����ϴµ�, �� ��� ������ ����ϱ� ���� Ŭ����
 * 
 * import java.sql.SQLException;
 * >> DB �׼��� ���� �Ǵ� �� ���� ������ ���� ������ �����մϴ�.
 * 
 * import java.sql.Statement;
 * >> ������ �������� ó���� �� �ִ� Statement�� ����ϱ� ���� Ŭ����
 * 
 * import javax.swing.ImageIcon;
 * >> �̹������� �������� �׸��� Icon �������̽��� ������ Ŭ����
 * 
 * import javax.swing.JButton;
 * >> ��ư�� �����ϱ����� ����ϴ� Ŭ����
 * 
 * import javax.swing.JFrame;
 * >> �������� ������Ű�� ���� ����ϴ� Ŭ����
 * 
 * import javax.swing.JLabel;
 * >> ������Ʈ�� �ؽ�Ʈ�� �̹����� ���� �� ����ϴ� Ŭ����
 * >> getText()�� setText(String str)�� ����� �� �ֽ��ϴ�. 
 * 
 * import javax.swing.JPanel;
 * >> ������Ʈ���� �׷캰�� ��� ó���� �� ����ϴ� �����̳� Ŭ����
 * >> �Ϲ������� Frame�� ������Ʈ���� ���� ������ �ʰ� Panel�� ����մϴ�.
 * 
 * import javax.swing.JScrollPane;
 * >> ��ũ���� �̿��ؼ� ������Ʈ���� �����ִ� ������Ʈ Ŭ����
 * >> ��ũ���� �̿��ؼ� �����ִ� ȭ���� �����¿�� �̵��Ͽ� ���Ե� ������Ʈ�� ���� ũ�⸦ �����մϴ�.
 * 
 * import javax.swing.JTable;
 * >> �����͸� ��� ���� �����Ǿ� �ִ� ���̺� �������� �����ִ� ������Ʈ Ŭ����
 * >> JTable�� ����ϱ� ���ؼ��� ���� �����͸� ������ ���� ����� JTable�� �����մϴ�.
 * 
 * import javax.swing.SwingConstants;
 * >> �Ϲ������� ȭ�鿡�� ���� ��Ҹ� ��ġ�ϰ� ������ ������ �� ����ϴ� Ŭ����
 * 
 * import javax.swing.table.DefaultTableModel;
 * >> �����͸� ��� ���� �����Ǿ� �ִ� ���̺� �������� �����ִ� ������Ʈ Ŭ���� 
 * >> �����͸� Vector�� �����ϱ� ������ JTable���� �������� �߰�, ������ �� �� �ֽ��ϴ�.
 * 
 *************************************************************************/

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/********************************************************************************
* Title : ��ȭ�� ��ȭ �󿵰����� ���� Oracle DB ���� �� java��� ���� 
* Date : 2019.12.21
* Purpose : Oracle DB ���� �� JFrame ���
* 
* ���� : �����ͺ��̽�
* �̸� : ����ȸ
* �й� : 20154215
* �а� : ��ǻ�Ͱ��а�
********************************************************************************/

public class GUI extends JFrame {
	private Connection conn = null;	// DB�� ����� ���¸� ���� ��ü conn�� �����߽��ϴ�.
	private JLabel state;	
	
	// �ʱ� ȭ�鿡�� ������ ���̺��� �ʵ尪 ����
	private String colName[] = { "��ȭ�ڵ�", "��ȭ�̸�", "������", "������(��)", "������(%)", "����", "��ȭȨ������" };	
	private DefaultTableModel model = new DefaultTableModel(colName, 0);	
	private JTable table = new JTable(model);	
	private String row[] = new String[7];

	public GUI() {
		/********************************************************************************
		 * >> setTitle() �޼ҵ带 ����ؼ� �������� Ÿ��Ʋ�� �����մϴ�. DataBase Test 20154215 ����ȸ
		 * >> setDefaultCloseOperation(EXIT_ON_CLOSE) : ������ �����츦 ������ ���α׷��� ����˴ϴ�.
		 ********************************************************************************/
		setTitle("DataBase Test 20154215 ����ȸ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*********************************************************************************
		* ���� �����ϱ�  *
		* >> ImageIcon() �޼ҵ带 ����ؼ� ������ �о�Խ��ϴ�.
		* >> ������ ��δ� ���� ������Ʈ �������� ������ image���͸��Դϴ�. 
		* >> �׷��� �о�� ������ ũ�Ⱑ Ŀ�� ũ�⸦ �����߽��ϴ�.
		*********************************************************************************/
		ImageIcon image = new ImageIcon("image/Create_homework.jpg");		
		Image resizeImage = image.getImage();
		Image FinresizeImage = resizeImage.getScaledInstance(900, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimage = new ImageIcon(FinresizeImage);		
		JLabel imageLabel = new JLabel(reimage);		
		
		/*********************************************************************************
		 * ������Ʈ�� �г� *
		 * >> �ϴܺ� ���� �� ��� ��ư ������Ʈ�� �г�
		 * >> �̹����� �г�
		*********************************************************************************/
		JPanel Btn_panel = new JPanel();		
		JPanel image_panel = new JPanel();
		
		/*********************************************************************************
		 * ������Ʈ ���� *
		 * >> ��ư�� �̸��� ���� Connect�� Show�� �����մϴ�.
		 * >> add() �޼ҵ带 ����ؼ� �гο� ��ư�� �̹����� �����մϴ�.
		*********************************************************************************/
		// ���� ���� �߰� ��ȸ ��ư
		JButton con = new JButton("Connect");	JButton show = new JButton("Show");
		JButton add = new JButton("add");		JButton del = new JButton("delete");
		JButton upd = new JButton("update");					
		JTextField tf = new JTextField(10);		add(tf);
		image_panel.add(imageLabel);
		Btn_panel.add(con);						Btn_panel.add(show);		
		Btn_panel.add(add);						Btn_panel.add(del);		
		Btn_panel.add(upd);
				
		/*********************************************************************************
		 * Connect ��ư �̺�Ʈ ������ (�׼� ������) *
		 * >> Connect ��ư�� Ŭ������ �� DB�� �����ϴ� Connect() �޼ҵ带 ȣ���ϴ� �κ��Դϴ�. 
		 *********************************************************************************/
		con.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		/*********************************************************************************
		 * Show ��ư �̺�Ʈ ������ (�׼� ������) *
		 * >> Show ��ư�� Ŭ������ �� DB�� �о���� show() �޼ҵ带 ȣ���ϴ� �κ��Դϴ�.
		 * >> ��ư�� Ŭ���ϸ� �������� ������ ����� �����ݴϴ�.
		 * >> model.setRowCount(0)
		 * >> �����ͺ��̽����� ��ȭ ����Ʈ�� �����ͼ� ����� �� ���ڵ尡 �ߺ��Ǿ� ��Ÿ���Ƿ� 
		 * >> �� �޼ҵ带 ����ؼ� JTable�� �ʱ�ȭ�����ݴϴ�.
		 * *********************************************************************************/
		show.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				show_db();
			}
		});
		
		/*********************************************************************************
		 * add ��ư �̺�Ʈ ������ (�׼� ������) *
		 * >> add ��ư�� Ŭ���ϸ� �����ͺ��̽��� ���ڵ带 ������ �� �ֽ��ϴ�.
		 * *********************************************************************************/				
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new add_db();
			}
		});
		
		/*********************************************************************************
		 * del ��ư �̺�Ʈ ������ (�׼� ������) *
		 * >> del ��ư�� Ŭ���ϸ� �����ͺ��̽��� ����� ���ڵ带 ������ �� �ֽ��ϴ�.
		 * *********************************************************************************/
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new del_db();
			}
		});
		
		/*********************************************************************************
		 * upd ��ư �̺�Ʈ ������ (�׼� ������) *
		 * >> upd ��ư�� Ŭ���ϸ� �����ͺ��̽��� ����� ���ڵ带 ������ �� �ֽ��ϴ�.
		 * *********************************************************************************/
		upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new upd_db();
			}
		});
		
		/*********************************************************************************
		 * ��ġ *
		 * >> DB ���� ��¿� �󺧿� "Oracle DB ���� �׽�Ʈ"�� ����մϴ�.
		 * >> DB ���� ��¿� ���� ��ġ�� BorderLayout�� ����ؼ� ��ġ�մϴ�.
		 * >> JScrollPane() : ȭ�鿡�� �Ѿ ��� ��ũ�ѹٰ� ����ϴ�.
		 * >> Btn_panel�� image_panel�� ��ġ�� ���� ���� �����߽��ϴ�.
		 * >> setSize() �޼ҵ带 ����ؼ� ȭ�鿡 900x500 ũ���� �������� �����ݴϴ�.
		 * >> setResizable(false) : �������� ũ��� ������ �� �����ϴ�.  
		 *********************************************************************************/
		state = new JLabel();
		state.setText("Oracle DB ���� �׽�Ʈ");		
		add(state, BorderLayout.NORTH);

		add(new JScrollPane(table), BorderLayout.CENTER);
		add(Btn_panel, BorderLayout.SOUTH);					
		add(image_panel, BorderLayout.WEST);
		
		setSize(900, 500);	setVisible(true);	setResizable(false);
	}
	
	/*********************************************************************************
	 * add class *
	 * >> �� Ŭ�������� ������ ���Կ� ���� ����� ó���մϴ� 
	 * >> �ʱ�ȭ�鿡�� add��ư�� ������ �����Ǵ� �ڽ�â���� MOVIE_THEATER ���̺� ���� �ʵ带 �Է��� �� �ֽ��ϴ�.
	 * >> �ʵ带 �Է��ϰ� insert�� ������ ������ ������ ���̺� �ʵ� ���Ŀ� �°� ��ȯ�˴ϴ�. (ex) string > int)
	 * >> ��ȯ�� ���������� insert_db() �޼ҵ��� ���ڰ����� �Ѱ��ݴϴ�. 
	  *********************************************************************************/
	class add_db extends JFrame {
		// insert
		Container text;		JButton btn;
		
		add_db(){			
			setTitle("add");
			// ����Ʈ���� �ּҸ� �˾Ƴ�
			text = getContentPane();
			text.add(new insertData(), BorderLayout.CENTER);
			
			setSize(330,300);	setResizable(false);	setVisible(true);
		}

		class insertData extends JPanel {			
			
			insertData(){
				/*********************************************************************************
				 * �ʵ尪 �Է� *
				 * >> �ʵ带 �Է��� ������ �� �����鿡 string���·� ����˴ϴ�.
				  *********************************************************************************/
				JTextField movie_code	 = new JTextField(20);
				JTextField movie_name 	 = new JTextField(20);
				JTextField movie_release = new JTextField(20);
				JTextField movie_aud 	 = new JTextField(20);
				JTextField movie_ratio	 = new JTextField(20);
				JTextField movie_rating  = new JTextField(20);
				JTextField movie_website = new JTextField(20);				
							
				add(new JLabel("��ȭ�ڵ�"));		add(movie_code);
				add(new JLabel("��ȭ�̸�"));		add(movie_name);
				add(new JLabel("������"));		add(movie_release);
				add(new JLabel("������(��)"));		add(movie_aud);
				add(new JLabel("������(%)"));		add(movie_ratio);
				add(new JLabel("����"));			add(movie_rating);
				add(new JLabel("��ȭȨ������")); 	add(movie_website);
				
				btn = new JButton("insert");	add(btn);
				
				/*********************************************************************************
				 * ��ư �̺�Ʈ *
				 * >> ���⼭ �� �������� ��ȯ��Ų ���� �޼ҵ��� ���ڰ����� �Ѱ��ݴϴ�. 
				  *********************************************************************************/
				btn.addActionListener(new ActionListener(){		
					public void actionPerformed(ActionEvent e) {
						
						int m_code 		= Integer.parseInt(movie_code.getText());
						String m_name 	= movie_name.getText();
						String m_rel 	= movie_release.getText();
						int m_aud		= Integer.parseInt(movie_aud.getText());
						Double m_ratio  = Double.parseDouble(movie_ratio.getText());
						Double m_rating = Double.parseDouble(movie_rating.getText());
						String m_website= movie_website.getText();					
						
						insert_db(m_code, m_name, m_rel, m_aud, m_ratio, m_rating, m_website);						
					}
				});
			}
		}
	}
	
	/*********************************************************************************
	 * del class *
	 * >> �� Ŭ�������� ������ ������ ���� ����� ó���մϴ�.
	 * >> �ʱ�ȭ�鿡�� delete��ư�� ������ �����Ǵ� �ڽ�â���� MOVIE_THEATER ���̺� ����Ǿ� �ִ� ��ȭ�� �߿�
	 * >> �����ϰ���� ��ȭ�� ��ȭ�ڵ带 �Է��� �� �ֽ��ϴ�.
	 * >> ��ȭ�ڵ带 �Է��ϰ� delete�� ������ �Է��� ��ȭ�ڵ尪�� delete_db() �޼ҵ忡�� ���ڰ����� �Ѱ��ָ�
	 * >> ��ȭ�ڵ忡 �ش��ϴ� ���ڵ尡 �����˴ϴ�.
	  *********************************************************************************/
	class del_db extends JFrame {
		// delete
		Container text;		JButton btn;
		
		del_db(){
			setTitle("delete");
			text = getContentPane(); // ����Ʈ���� �ּҸ� �˾Ƴ�			
			text.add(new deleteData(), BorderLayout.CENTER);
			
			setSize(300,130);	setResizable(false);	setVisible(true);
		}

		class deleteData extends JPanel {
			
			deleteData(){
				/*********************************************************************************
				 * �ʵ尪 �Է� *
				 * >> �ʵ带 �Է��� ���� ������ string���·� ����˴ϴ�.
				  *********************************************************************************/
				JTextField movie_code = new JTextField(20);
				
				btn = new JButton("delete");						
				add(new JLabel("��ȭ�ڵ�"));		add(movie_code);
												add(btn);
											
				/*********************************************************************************
				 * ��ư �̺�Ʈ *
				 * >> ���⼭ �������� ��ȯ��Ų ���� �޼ҵ��� ���ڰ����� �Ѱ��ݴϴ�. 
				  *********************************************************************************/												
				btn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						int m_code = Integer.parseInt(movie_code.getText());
						delete_db(m_code);
					}
				});			
			}
		}
	}
	
	/*********************************************************************************
	 * upd class *
	 * >> �� Ŭ�������� ������ ������ ���� ����� ó���մϴ�.
	 * >> �ʱ�ȭ�鿡�� update��ư�� ������ �ڽ�â���� MOVIE_THEATER ���̺� ����Ǿ� �ִ� ��ȭ�� �߿�
	 * >> �����ϰ� ���� ��ȭ�� ��ȭ�ڵ带 �Է��ϰ�, ������ ��ȭ�� �������� �Է��� �� �ֽ��ϴ�.
	 * >> �ش� ������ ���� �Է��ϰ� update�� ������ insert�� �����ϰ� �Է��� ������ ������ �ٲ㼭
	 * >> update_db() �޼ҵ��� ���ڷ� �����ϸ� �ش� ���ڵ��� ������ ����˴ϴ�.
	  *********************************************************************************/
	class upd_db extends JFrame {
		// update
		Container text;		JButton btn;
		
		upd_db(){
			setTitle("update");
			text = getContentPane(); // ����Ʈ���� �ּҸ� �˾Ƴ�			
			text.add(new updateData(), BorderLayout.CENTER);
			
			setSize(330,350);	setResizable(false);	setVisible(true);
		}
		
		class updateData extends JPanel {
			
			updateData(){
			
				/*********************************************************************************
				 * �ʵ尪 �Է� *
				 * >> �ʵ带 �Է��� ������ �� �����鿡 string���·� ����˴ϴ�.
				  *********************************************************************************/
				JTextField b_movie_code = new JTextField(20);
				JTextField movie_code 	= new JTextField(20);
				JTextField movie_name 	= new JTextField(20);
				JTextField movie_release= new JTextField(20);
				JTextField movie_aud 	= new JTextField(20);
				JTextField movie_ratio  = new JTextField(20);
				JTextField movie_rating = new JTextField(20);
				JTextField movie_website= new JTextField(20);				
				
				btn = new JButton("update");				
									
				add(new JLabel("�ٲٰ� ���� ��ȭ�� �ڵ带 �Է��ϼ���"));		
				add(b_movie_code);			
				add(new JLabel("������ ��ȭ�� ������ �Է��ϼ��� (���� �Է��ؾ� ��)"));				
				add(new JLabel("��ȭ�ڵ�"));		add(movie_code);
				add(new JLabel("��ȭ�̸�"));		add(movie_name);
				add(new JLabel("������"));		add(movie_release);
				add(new JLabel("������(��)"));		add(movie_aud);
				add(new JLabel("������(%)"));		add(movie_ratio);
				add(new JLabel("����"));			add(movie_rating);
				add(new JLabel("��ȭȨ������")); 	add(movie_website);
												add(btn);
				
				/*********************************************************************************
				 * ��ư �̺�Ʈ *
				 * >> ���⼭ �������� ��ȯ��Ų ���� �޼ҵ��� ���ڰ����� �Ѱ��ݴϴ�. 
				  *********************************************************************************/	
				btn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						int m_bcode 	= Integer.parseInt(b_movie_code.getText());
						int m_code 		= Integer.parseInt(movie_code.getText());
						String m_name 	= movie_name.getText();
						String m_rel 	= movie_release.getText();
						int m_aud 		= Integer.parseInt(movie_aud.getText());
						Double m_ratio 	= Double.parseDouble(movie_ratio.getText());
						Double m_rating = Double.parseDouble(movie_rating.getText());
						String m_website= movie_website.getText();					
						
						upd_db(m_bcode, m_code, m_name, m_rel, m_aud, m_ratio, m_rating, m_website);						
					}
				});				
			}			
		}	
	}
	
	
	/*********************************************************************************************
	 * DB ���� ��¿� ���� �����մϴ�.
	 * �ʱ� ���´� "Oracle DB ���� �׽�Ʈ"�� ����ϸ�
	 * Connect ��ư�� ������ �� DB�� ������ �����ϸ� "������ ����"��������, �����ϸ� "DB ���� ����" �������� �ٲپ� ����մϴ�.
	 * Show ��ư�� ������ �� ������ ���࿡ �����ϸ� "DB �б� ����"��������, �����ϸ� "DB �б� ����" �������� �ٲپ� ����մϴ�. 
	 *********************************************************************************************/
	public void connect() {
		try {			
			/**********************************************************************************************
			 * DriverManager Ŭ������ ��� *
			 * >> ����̹� �ε�
			 * >> ����̹� �������̽��� ������ Ŭ������ �ε��մϴ�.
			 * 
			 * DriverManager.getConnection() : DB�� ����(����)�ϱ� ���� �޼ҵ� *
			 * >> ����̹� �Ŵ������� Connection ��ü�� ��û�մϴ�.
			 * >> Connection�� ������� getConnection�޼ҵ带 ����ϸ� ���ڴ� ("����url", "����� ����", "���� ��й�ȣ")�Դϴ�.
			 * >> �� ��ü�� ����ؼ� statement�� �������� ������ �� �ֽ��ϴ�. 
			 **********************************************************************************************/
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MHHW4", "1234");
			System.out.println("������ �ε�");
			state.setText("������ ����");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			state.setText("DB ���� ���� " + e.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB ���� ���� " + e.toString());
		}
	}
	
	/**********************************************************************************************
	 * �������� �����ϴ� �޼ҵ��Դϴ�.
	 * >> Show ��ư�� �̺�Ʈ �����ʿ� ���� ȣ��Ǹ�, ��ư�� Ŭ���Ǹ� try ~ catch���� �����ؼ� ����� �����ݴϴ�.
	 **********************************************************************************************/
	public void show_db() {
		try {
			/*******************************************************************
			 * conn.createStatement() : Statement ���� *
			 * >> select ���� ������ ���� Statement ��ü�� �����մϴ�. 
			 * ������ ���� *
			 * >> JDBC���� ������ �ۼ��� �� �����ݷ� (;)�� ���� �ۼ��մϴ�.
			 * >> *�� ��� Į���� �о���� �ͺ��� �����;� �� Į���� ���� ������ִ°� ���ٰ� �մϴ�.
			 * >> ������ �� �ٷ� ���� ����� ��� �鿩���⸦ ����ص� ��� �����ϴ�. 
			 * >> ���ڵ���� ResultSet ��ü�� �߰��˴ϴ�.	 
			 *******************************************************************/ 
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from MOVIE_THEATER");
			System.out.println("��ȭ�ڵ�\t��ȭ�̸�\t������\t������(��)\t������(%)\t����\t��ȭȨ������");
			
			/************************************************
			 * while(rset.next()) : ������ ��� ��� *
			 * >> test table
			 * >> num		name	address		phone 
			 * >>  1 		����� 	���� ���� 		010-1234-5678
			 * >>  2 		������ 	���� �ϱ� 		010-1111-7148
			 * >>  3 		��ҹ�	���� ���� 		010-2222-1248			  
			 * >>  .... �̷������� ����Ǿ� �ְ� 
			 * >>  next() �޼ҵ�� ���� �࿡�� �� �� �������� �̵��ϹǷ�
			 * >>  test�� ���ڵ� ù����� ����ϰ� �˴ϴ�.
			 * JTable�� ��� *
			 * addRow() �޼ҵ带 ����ؼ� DB���� �о�� ���ڵ���� �����ݴϴ�.			  
			 *************************************************/		
			while (rset.next()) {
				for (int i = 1; i <8; i++) {
					System.out.print(rset.getString(i) + "\t");
					row[i - 1] = rset.getString(i);
				}
				
				System.out.println();
				model.addRow(row); // JTable�� ���				
			}
			
			state.setText("DB �б� ����");			
		} catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB �б� ���� " + e.toString());			
		}		
	}

	public void insert_db(int code, String name, String release, int audience, Double ratio, Double rating, String website) {

		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement ���� *
			 * >> insert ���� ������ ���� Statement ��ü�� �����մϴ�. 
			 * ������ ���� *
			 * >> JDBC���� ������ �ۼ��� �� �����ݷ� (;)�� ���� �ۼ��մϴ�.
			 * >> *�� ��� Į���� �о���� �ͺ��� �����;� �� Į���� ���� ������ִ°� ���ٰ� �մϴ�.
			 * >> ������ �� �ٷ� ���� ����� ��� �鿩���⸦ ����ص� ��� �����ϴ�.
			 * >> pstmt.executeUpdate() : ���� �Ҵ�Ǹ�  �� �޼��带 ����ؼ� insert ������ �����մϴ�. 
			 * >> count : ��ȯ ���� ������ ��ģ row �����Դϴ�.
			 * >> 		  ���� �Էµ��� �ʾ����� count���� 0�̹Ƿ� �Է� ���и� ����մϴ�.
			 *******************************************************************/				
			//(MOVIE_CODE, MOVIE_NAME, MOVIE_RELEASE, MOVIE_AUDIENCE, MOVIE_RATIO, MOVIE_RATING, MOVIE_WEBSITE)
			String sql = "INSERT INTO MOVIE_THEATER VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);				pstmt.setString(2, name);
			pstmt.setString(3, release);		pstmt.setInt(4, audience);
			pstmt.setDouble(5, ratio);			pstmt.setDouble(6, rating);
			pstmt.setString(7, website);
			
			int count = pstmt.executeUpdate();			
			if (count == 0) 	System.out.println("������ �Է� ����");
			else				System.out.println("������ �Է� ����");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB �б� ���� " + e.toString());			
		}
	}

	public void delete_db(int code)	{
		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement ���� *
			 * >> delete ���� ������ ���� Statement ��ü�� �����մϴ�. 
			 * ������ ���� *
			 * >> JDBC���� ������ �ۼ��� �� �����ݷ� (;)�� ���� �ۼ��մϴ�.
			 * >> *�� ��� Į���� �о���� �ͺ��� �����;� �� Į���� ���� ������ִ°� ���ٰ� �մϴ�.
			 * >> ������ �� �ٷ� ���� ����� ��� �鿩���⸦ ����ص� ��� �����ϴ�.
			 * >> pstmt.executeUpdate() : ���� �Ҵ�Ǹ�  �� �޼��带 ����ؼ� delete ������ �����մϴ�. 
			 * >> count : ��ȯ ���� ������ ��ģ row �����Դϴ�.
			 * >> 		  ���� �Էµ��� �ʾ����� count���� 0�̹Ƿ� ���� ���и� ����մϴ�.
			 *******************************************************************/ 
			String sql = "DELETE FROM MOVIE_THEATER WHERE MOVIE_CODE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);			
			
			int count = pstmt.executeUpdate();			
			if (count == 0) 	System.out.println("������ ���� ����");
			else				System.out.println("������ ���� ����");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB �б� ���� " + e.toString());			
		}
	}
	
	public void upd_db(int b_code, int code, String name, String release, int audience, Double ratio, Double rating, String website) {
		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement ���� *
			 * >> update ���� ������ ���� Statement ��ü�� �����մϴ�. 
			 * ������ ���� *
			 * >> JDBC���� ������ �ۼ��� �� �����ݷ� (;)�� ���� �ۼ��մϴ�.
			 * >> *�� ��� Į���� �о���� �ͺ��� �����;� �� Į���� ���� ������ִ°� ���ٰ� �մϴ�.
			 * >> ������ �� �ٷ� ���� ����� ��� �鿩���⸦ ����ص� ��� �����ϴ�.
			 * >> pstmt.executeUpdate() : ���� �Ҵ�Ǹ�  �� �޼��带 ����ؼ� update ������ �����մϴ�. 
			 * >> count : ��ȯ ���� ������ ��ģ row �����Դϴ�.
			 * >> 		  ���� �Էµ��� �ʾ����� count���� 0�̹Ƿ� ���� ���и� ����մϴ�.
			 *******************************************************************/ 
			//(MOVIE_CODE = ?, MOVIE_NAME = ?, MOVIE_RELEASE = ?, MOVIE_AUDIENCE = ?, MOVIE_RATIO = ?, MOVIE_RATING = ?, MOVIE_WEBSITE = ?)
			String sql = "UPDATE MOVIE_THEATER "
					   + "SET MOVIE_CODE = ?,     MOVIE_NAME = ?,     MOVIE_RELEASE = ?,   MOVIE_AUDIENCE = ?, "
					   + "MOVIE_RATIO = ?, 	      MOVIE_RATING = ?,   MOVIE_WEBSITE = ?"
					   + "WHERE MOVIE_CODE = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);			pstmt.setString(2, name);
			pstmt.setString(3, release);	pstmt.setInt(4, audience);
			pstmt.setDouble(5, ratio);		pstmt.setDouble(6, rating);
			pstmt.setString(7, website);	pstmt.setInt(8, b_code);
			
			int count = pstmt.executeUpdate();			
			if (count == 0)		System.out.println("������ ���� ����");
			else				System.out.println("������ ���� ����");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB �б� ���� " + e.toString());			
		}
	}
	
	public static void main(String[] args) 
	{ 
		new GUI();
	}
}