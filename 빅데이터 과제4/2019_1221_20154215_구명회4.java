

/*************************************************************************
 * import 설명 *
 * import java.awt.BorderLayout;
 * >> BorderLayout은 컨테이너를 North, South, East, West, Center 모두 5개의 영역으로 나누고
 * >> 각 영역에 하나의 컴포넌트만을 배치할 수 있도록 합니다.
 *  
 * import java.awt.Image;
 * >> 이미지를 만들고 수정하기 위한 클래스
 * 
 * import java.awt.event.ActionEvent;
 * >> 버튼이 클릭되거나 리스트, 메뉴 등이 선택되었을 때 발생하는 이벤트 클래스
 * >> ActionListener 인터페이스의 actionPerformed() 메서드를 이용해서 처리합니다. 
 * 
 * import java.awt.event.ActionListener;
 * >> 이벤트 인터페이스
 * 
 * import java.sql.Connection;
 * >> Connection 객체를 자동완성으로 import 하기 위한 java 표준 java.sql.Connection 클래스
 * 
 * import java.sql.DriverManager;
 * >> java.sql의 인터페이스들을 상속해서 메소드의 몸체를 구현한 JDBC 드라이버 클래스
 * 
 * import java.sql.ResultSet;
 * >> select 쿼리 실행 시 executeQuery() 메서드를 사용하며
 * >> 실행 결과를 java.sql.ResultSet형으로 리턴하는데, 이 결과 집합을 사용하기 위한 클래스
 * 
 * import java.sql.SQLException;
 * >> DB 액세스 에러 또는 그 외의 에러에 관한 정보를 제공합니다.
 * 
 * import java.sql.Statement;
 * >> 정적인 쿼리문을 처리할 수 있는 Statement를 사용하기 위한 클래스
 * 
 * import javax.swing.ImageIcon;
 * >> 이미지에서 아이콘을 그리는 Icon 인터페이스를 구현한 클래스
 * 
 * import javax.swing.JButton;
 * >> 버튼을 구성하기위해 사용하는 클래스
 * 
 * import javax.swing.JFrame;
 * >> 프레임을 생성시키기 위해 사용하는 클래스
 * 
 * import javax.swing.JLabel;
 * >> 컴포넌트에 텍스트와 이미지를 넣을 때 사용하는 클래스
 * >> getText()와 setText(String str)을 사용할 수 있습니다. 
 * 
 * import javax.swing.JPanel;
 * >> 컴포넌트들을 그룹별로 묶어서 처리할 때 사용하는 컨테이너 클래스
 * >> 일반적으로 Frame에 컴포넌트들을 직접 붙이지 않고 Panel을 사용합니다.
 * 
 * import javax.swing.JScrollPane;
 * >> 스크롤을 이용해서 컴포넌트들을 보여주는 컴포넌트 클래스
 * >> 스크롤을 이용해서 보여주는 화면을 상하좌우로 이동하여 포함된 컴포넌트의 원래 크기를 유지합니다.
 * 
 * import javax.swing.JTable;
 * >> 데이터를 행과 열로 구성되어 있는 테이블 형식으로 보여주는 컴포넌트 클래스
 * >> JTable을 사용하기 위해서는 먼저 데이터를 저장할 모델을 만들고 JTable에 연결합니다.
 * 
 * import javax.swing.SwingConstants;
 * >> 일반적으로 화면에서 구성 요소를 배치하고 방향을 지정할 때 사용하는 클래스
 * 
 * import javax.swing.table.DefaultTableModel;
 * >> 데이터를 행과 열로 구성되어 있는 테이블 형식으로 보여주는 컴포넌트 클래스 
 * >> 데이터를 Vector로 생성하기 때문에 JTable에서 데이터의 추가, 삭제를 할 수 있습니다.
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
* Title : 영화관 영화 상영관리에 대한 Oracle DB 연동 및 java언어 구현 
* Date : 2019.12.21
* Purpose : Oracle DB 연동 및 JFrame 사용
* 
* 과목 : 데이터베이스
* 이름 : 구명회
* 학번 : 20154215
* 학과 : 컴퓨터공학과
********************************************************************************/

public class GUI extends JFrame {
	private Connection conn = null;	// DB와 연결된 상태를 담은 객체 conn을 선언했습니다.
	private JLabel state;	
	
	// 초기 화면에서 보여질 테이블의 필드값 설정
	private String colName[] = { "영화코드", "영화이름", "개봉일", "관객수(명)", "예매율(%)", "평점", "영화홈페이지" };	
	private DefaultTableModel model = new DefaultTableModel(colName, 0);	
	private JTable table = new JTable(model);	
	private String row[] = new String[7];

	public GUI() {
		/********************************************************************************
		 * >> setTitle() 메소드를 사용해서 프레임의 타이틀을 설정합니다. DataBase Test 20154215 구명회
		 * >> setDefaultCloseOperation(EXIT_ON_CLOSE) : 프레임 윈도우를 닫으면 프로그램이 종료됩니다.
		 ********************************************************************************/
		setTitle("DataBase Test 20154215 구명회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*********************************************************************************
		* 사진 부착하기  *
		* >> ImageIcon() 메소드를 사용해서 사진을 읽어왔습니다.
		* >> 사진의 경로는 현재 프로젝트 폴더에서 생성한 image디렉터리입니다. 
		* >> 그런데 읽어온 사진의 크기가 커서 크기를 조절했습니다.
		*********************************************************************************/
		ImageIcon image = new ImageIcon("image/Create_homework.jpg");		
		Image resizeImage = image.getImage();
		Image FinresizeImage = resizeImage.getScaledInstance(900, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimage = new ImageIcon(FinresizeImage);		
		JLabel imageLabel = new JLabel(reimage);		
		
		/*********************************************************************************
		 * 컴포넌트옹 패널 *
		 * >> 하단부 연결 및 출력 버튼 컴포넌트용 패널
		 * >> 이미지용 패널
		*********************************************************************************/
		JPanel Btn_panel = new JPanel();		
		JPanel image_panel = new JPanel();
		
		/*********************************************************************************
		 * 컴포넌트 설정 *
		 * >> 버튼의 이름을 각각 Connect와 Show로 설정합니다.
		 * >> add() 메소드를 사용해서 패널에 버튼과 이미지를 부착합니다.
		*********************************************************************************/
		// 삽입 삭제 추가 조회 버튼
		JButton con = new JButton("Connect");	JButton show = new JButton("Show");
		JButton add = new JButton("add");		JButton del = new JButton("delete");
		JButton upd = new JButton("update");					
		JTextField tf = new JTextField(10);		add(tf);
		image_panel.add(imageLabel);
		Btn_panel.add(con);						Btn_panel.add(show);		
		Btn_panel.add(add);						Btn_panel.add(del);		
		Btn_panel.add(upd);
				
		/*********************************************************************************
		 * Connect 버튼 이벤트 리스너 (액션 리스너) *
		 * >> Connect 버튼을 클릭했을 때 DB와 연결하는 Connect() 메소드를 호출하는 부분입니다. 
		 *********************************************************************************/
		con.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		/*********************************************************************************
		 * Show 버튼 이벤트 리스너 (액션 리스너) *
		 * >> Show 버튼을 클릭했을 때 DB를 읽어오는 show() 메소드를 호출하는 부분입니다.
		 * >> 버튼을 클릭하면 쿼리문을 수행한 결과를 보여줍니다.
		 * >> model.setRowCount(0)
		 * >> 데이터베이스에서 영화 리스트를 가져와서 출력할 때 레코드가 중복되어 나타나므로 
		 * >> 이 메소드를 사용해서 JTable을 초기화시켜줍니다.
		 * *********************************************************************************/
		show.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				show_db();
			}
		});
		
		/*********************************************************************************
		 * add 버튼 이벤트 리스너 (액션 리스너) *
		 * >> add 버튼을 클릭하면 데이터베이스에 레코드를 삽입할 수 있습니다.
		 * *********************************************************************************/				
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new add_db();
			}
		});
		
		/*********************************************************************************
		 * del 버튼 이벤트 리스너 (액션 리스너) *
		 * >> del 버튼을 클릭하면 데이터베이스에 저장된 레코드를 삭제할 수 있습니다.
		 * *********************************************************************************/
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new del_db();
			}
		});
		
		/*********************************************************************************
		 * upd 버튼 이벤트 리스너 (액션 리스너) *
		 * >> upd 버튼을 클릭하면 데이터베이스에 저장된 레코드를 수정할 수 있습니다.
		 * *********************************************************************************/
		upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new upd_db();
			}
		});
		
		/*********************************************************************************
		 * 배치 *
		 * >> DB 상태 출력용 라벨에 "Oracle DB 연동 테스트"를 출력합니다.
		 * >> DB 상태 출력용 라벨의 위치를 BorderLayout을 사용해서 배치합니다.
		 * >> JScrollPane() : 화면에서 넘어갈 경우 스크롤바가 생깁니다.
		 * >> Btn_panel과 image_panel의 위치를 폼에 맞춰 조절했습니다.
		 * >> setSize() 메소드를 사용해서 화면에 900x500 크기의 프레임을 보여줍니다.
		 * >> setResizable(false) : 프레임의 크기는 수정할 수 없습니다.  
		 *********************************************************************************/
		state = new JLabel();
		state.setText("Oracle DB 연동 테스트");		
		add(state, BorderLayout.NORTH);

		add(new JScrollPane(table), BorderLayout.CENTER);
		add(Btn_panel, BorderLayout.SOUTH);					
		add(image_panel, BorderLayout.WEST);
		
		setSize(900, 500);	setVisible(true);	setResizable(false);
	}
	
	/*********************************************************************************
	 * add class *
	 * >> 이 클래스에서 데이터 삽입에 관한 명령을 처리합니다 
	 * >> 초기화면에서 add버튼을 누르면 생성되는 자식창에서 MOVIE_THEATER 테이블에 대한 필드를 입력할 수 있습니다.
	 * >> 필드를 입력하고 insert를 누르면 각각의 값들이 테이블 필드 형식에 맞게 변환됩니다. (ex) string > int)
	 * >> 변환된 변수값들을 insert_db() 메소드의 인자값으로 넘겨줍니다. 
	  *********************************************************************************/
	class add_db extends JFrame {
		// insert
		Container text;		JButton btn;
		
		add_db(){			
			setTitle("add");
			// 컨텐트팬의 주소를 알아냄
			text = getContentPane();
			text.add(new insertData(), BorderLayout.CENTER);
			
			setSize(330,300);	setResizable(false);	setVisible(true);
		}

		class insertData extends JPanel {			
			
			insertData(){
				/*********************************************************************************
				 * 필드값 입력 *
				 * >> 필드를 입력한 값들이 각 변수들에 string형태로 저장됩니다.
				  *********************************************************************************/
				JTextField movie_code	 = new JTextField(20);
				JTextField movie_name 	 = new JTextField(20);
				JTextField movie_release = new JTextField(20);
				JTextField movie_aud 	 = new JTextField(20);
				JTextField movie_ratio	 = new JTextField(20);
				JTextField movie_rating  = new JTextField(20);
				JTextField movie_website = new JTextField(20);				
							
				add(new JLabel("영화코드"));		add(movie_code);
				add(new JLabel("영화이름"));		add(movie_name);
				add(new JLabel("개봉일"));		add(movie_release);
				add(new JLabel("관객수(명)"));		add(movie_aud);
				add(new JLabel("예매율(%)"));		add(movie_ratio);
				add(new JLabel("평점"));			add(movie_rating);
				add(new JLabel("영화홈페이지")); 	add(movie_website);
				
				btn = new JButton("insert");	add(btn);
				
				/*********************************************************************************
				 * 버튼 이벤트 *
				 * >> 여기서 각 변수값을 변환시킨 것을 메소드의 인자값으로 넘겨줍니다. 
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
	 * >> 이 클래스에서 데이터 삭제에 관한 명령을 처리합니다.
	 * >> 초기화면에서 delete버튼을 누르면 생성되는 자식창에서 MOVIE_THEATER 테이블에 저장되어 있는 영화들 중에
	 * >> 삭제하고싶은 영화의 영화코드를 입력할 수 있습니다.
	 * >> 영화코드를 입력하고 delete를 누르면 입력한 영화코드값을 delete_db() 메소드에게 인자값으로 넘겨주면
	 * >> 영화코드에 해당하는 레코드가 삭제됩니다.
	  *********************************************************************************/
	class del_db extends JFrame {
		// delete
		Container text;		JButton btn;
		
		del_db(){
			setTitle("delete");
			text = getContentPane(); // 컨텐트팬의 주소를 알아냄			
			text.add(new deleteData(), BorderLayout.CENTER);
			
			setSize(300,130);	setResizable(false);	setVisible(true);
		}

		class deleteData extends JPanel {
			
			deleteData(){
				/*********************************************************************************
				 * 필드값 입력 *
				 * >> 필드를 입력한 값이 변수에 string형태로 저장됩니다.
				  *********************************************************************************/
				JTextField movie_code = new JTextField(20);
				
				btn = new JButton("delete");						
				add(new JLabel("영화코드"));		add(movie_code);
												add(btn);
											
				/*********************************************************************************
				 * 버튼 이벤트 *
				 * >> 여기서 변수값을 변환시킨 것을 메소드의 인자값으로 넘겨줍니다. 
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
	 * >> 이 클래스에서 데이터 삭제에 관한 명령을 처리합니다.
	 * >> 초기화면에서 update버튼을 누르면 자식창에서 MOVIE_THEATER 테이블에 저장되어 있는 영화들 중에
	 * >> 변경하고 싶은 영화의 영화코드를 입력하고, 변경할 영화의 정보들을 입력할 수 있습니다.
	 * >> 해당 값들을 전부 입력하고 update를 누르면 insert와 동일하게 입력한 값들의 형식을 바꿔서
	 * >> update_db() 메소드의 인자로 전달하면 해당 레코드의 정보가 변경됩니다.
	  *********************************************************************************/
	class upd_db extends JFrame {
		// update
		Container text;		JButton btn;
		
		upd_db(){
			setTitle("update");
			text = getContentPane(); // 컨텐트팬의 주소를 알아냄			
			text.add(new updateData(), BorderLayout.CENTER);
			
			setSize(330,350);	setResizable(false);	setVisible(true);
		}
		
		class updateData extends JPanel {
			
			updateData(){
			
				/*********************************************************************************
				 * 필드값 입력 *
				 * >> 필드를 입력한 값들이 각 변수들에 string형태로 저장됩니다.
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
									
				add(new JLabel("바꾸고 싶은 영화의 코드를 입력하세요"));		
				add(b_movie_code);			
				add(new JLabel("변경할 영화의 정보를 입력하세요 (전부 입력해야 함)"));				
				add(new JLabel("영화코드"));		add(movie_code);
				add(new JLabel("영화이름"));		add(movie_name);
				add(new JLabel("개봉일"));		add(movie_release);
				add(new JLabel("관객수(명)"));		add(movie_aud);
				add(new JLabel("예매율(%)"));		add(movie_ratio);
				add(new JLabel("평점"));			add(movie_rating);
				add(new JLabel("영화홈페이지")); 	add(movie_website);
												add(btn);
				
				/*********************************************************************************
				 * 버튼 이벤트 *
				 * >> 여기서 변수값을 변환시킨 것을 메소드의 인자값으로 넘겨줍니다. 
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
	 * DB 상태 출력용 라벨을 변경합니다.
	 * 초기 상태는 "Oracle DB 연동 테스트"를 출력하며
	 * Connect 버튼을 눌렀을 때 DB와 연결이 성공하면 "성공적 연결"문장으로, 실패하면 "DB 연결 실패" 문장으로 바꾸어 출력합니다.
	 * Show 버튼을 눌렀을 때 쿼리문 수행에 성공하면 "DB 읽기 성공"문장으로, 실패하면 "DB 읽기 실패" 문장으로 바꾸어 출력합니다. 
	 *********************************************************************************************/
	public void connect() {
		try {			
			/**********************************************************************************************
			 * DriverManager 클래스에 등록 *
			 * >> 드라이버 로딩
			 * >> 드라이버 인터페이스를 구현한 클래스를 로딩합니다.
			 * 
			 * DriverManager.getConnection() : DB에 연결(접속)하기 위한 메소드 *
			 * >> 드라이버 매니저에게 Connection 객체를 요청합니다.
			 * >> Connection을 얻기위해 getConnection메소드를 사용하며 인자는 ("접속url", "사용자 계정", "계정 비밀번호")입니다.
			 * >> 이 객체를 사용해서 statement로 쿼리문을 수행할 수 있습니다. 
			 **********************************************************************************************/
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MHHW4", "1234");
			System.out.println("성공적 로딩");
			state.setText("성공적 연결");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			state.setText("DB 연결 실패 " + e.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB 연결 실패 " + e.toString());
		}
	}
	
	/**********************************************************************************************
	 * 쿼리문을 수행하는 메소드입니다.
	 * >> Show 버튼의 이벤트 리스너에 의해 호출되며, 버튼이 클릭되면 try ~ catch문을 수행해서 결과를 보여줍니다.
	 **********************************************************************************************/
	public void show_db() {
		try {
			/*******************************************************************
			 * conn.createStatement() : Statement 생성 *
			 * >> select 쿼리 수행을 위한 Statement 객체를 생성합니다. 
			 * 쿼리문 수행 *
			 * >> JDBC에서 쿼리를 작성할 때 세미콜론 (;)을 빼고 작성합니다.
			 * >> *로 모든 칼럼을 읽어오는 것보다 가져와야 할 칼럼을 직접 명시해주는게 좋다고 합니다.
			 * >> 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 상관 없습니다. 
			 * >> 레코드들은 ResultSet 객체에 추가됩니다.	 
			 *******************************************************************/ 
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from MOVIE_THEATER");
			System.out.println("영화코드\t영화이름\t개봉일\t관객수(명)\t예매율(%)\t평점\t영화홈페이지");
			
			/************************************************
			 * while(rset.next()) : 쿼리문 결과 출력 *
			 * >> test table
			 * >> num		name	address		phone 
			 * >>  1 		용길정 	광주 서구 		010-1234-5678
			 * >>  2 		최정성 	광주 북구 		010-1111-7148
			 * >>  3 		김소민	광주 남구 		010-2222-1248			  
			 * >>  .... 이런식으로 저장되어 있고 
			 * >>  next() 메소드는 현재 행에서 한 행 다음으로 이동하므로
			 * >>  test의 레코드 첫행부터 출력하게 됩니다.
			 * JTable에 출력 *
			 * addRow() 메소드를 사용해서 DB에서 읽어온 레코드들을 보여줍니다.			  
			 *************************************************/		
			while (rset.next()) {
				for (int i = 1; i <8; i++) {
					System.out.print(rset.getString(i) + "\t");
					row[i - 1] = rset.getString(i);
				}
				
				System.out.println();
				model.addRow(row); // JTable에 출력				
			}
			
			state.setText("DB 읽기 성공");			
		} catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB 읽기 실패 " + e.toString());			
		}		
	}

	public void insert_db(int code, String name, String release, int audience, Double ratio, Double rating, String website) {

		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement 생성 *
			 * >> insert 쿼리 수행을 위한 Statement 객체를 생성합니다. 
			 * 쿼리문 수행 *
			 * >> JDBC에서 쿼리를 작성할 때 세미콜론 (;)을 빼고 작성합니다.
			 * >> *로 모든 칼럼을 읽어오는 것보다 가져와야 할 칼럼을 직접 명시해주는게 좋다고 합니다.
			 * >> 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 상관 없습니다.
			 * >> pstmt.executeUpdate() : 값이 할당되면  이 메서드를 사용해서 insert 쿼리를 실행합니다. 
			 * >> count : 반환 값은 영향을 미친 row 개수입니다.
			 * >> 		  값이 입력되지 않았으면 count값이 0이므로 입력 실패를 출력합니다.
			 *******************************************************************/				
			//(MOVIE_CODE, MOVIE_NAME, MOVIE_RELEASE, MOVIE_AUDIENCE, MOVIE_RATIO, MOVIE_RATING, MOVIE_WEBSITE)
			String sql = "INSERT INTO MOVIE_THEATER VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);				pstmt.setString(2, name);
			pstmt.setString(3, release);		pstmt.setInt(4, audience);
			pstmt.setDouble(5, ratio);			pstmt.setDouble(6, rating);
			pstmt.setString(7, website);
			
			int count = pstmt.executeUpdate();			
			if (count == 0) 	System.out.println("데이터 입력 실패");
			else				System.out.println("데이터 입력 성공");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB 읽기 실패 " + e.toString());			
		}
	}

	public void delete_db(int code)	{
		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement 생성 *
			 * >> delete 쿼리 수행을 위한 Statement 객체를 생성합니다. 
			 * 쿼리문 수행 *
			 * >> JDBC에서 쿼리를 작성할 때 세미콜론 (;)을 빼고 작성합니다.
			 * >> *로 모든 칼럼을 읽어오는 것보다 가져와야 할 칼럼을 직접 명시해주는게 좋다고 합니다.
			 * >> 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 상관 없습니다.
			 * >> pstmt.executeUpdate() : 값이 할당되면  이 메서드를 사용해서 delete 쿼리를 실행합니다. 
			 * >> count : 반환 값은 영향을 미친 row 개수입니다.
			 * >> 		  값이 입력되지 않았으면 count값이 0이므로 삭제 실패를 출력합니다.
			 *******************************************************************/ 
			String sql = "DELETE FROM MOVIE_THEATER WHERE MOVIE_CODE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);			
			
			int count = pstmt.executeUpdate();			
			if (count == 0) 	System.out.println("데이터 삭제 실패");
			else				System.out.println("데이터 삭제 성공");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB 읽기 실패 " + e.toString());			
		}
	}
	
	public void upd_db(int b_code, int code, String name, String release, int audience, Double ratio, Double rating, String website) {
		try {
			/*******************************************************************
			 * conn.prepareStatement() : Statement 생성 *
			 * >> update 쿼리 수행을 위한 Statement 객체를 생성합니다. 
			 * 쿼리문 수행 *
			 * >> JDBC에서 쿼리를 작성할 때 세미콜론 (;)을 빼고 작성합니다.
			 * >> *로 모든 칼럼을 읽어오는 것보다 가져와야 할 칼럼을 직접 명시해주는게 좋다고 합니다.
			 * >> 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 상관 없습니다.
			 * >> pstmt.executeUpdate() : 값이 할당되면  이 메서드를 사용해서 update 쿼리를 실행합니다. 
			 * >> count : 반환 값은 영향을 미친 row 개수입니다.
			 * >> 		  값이 입력되지 않았으면 count값이 0이므로 변경 실패를 출력합니다.
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
			if (count == 0)		System.out.println("데이터 변경 실패");
			else				System.out.println("데이터 변경 성공");
						
		}
		catch (SQLException e) {
			e.printStackTrace();
			state.setText("DB 읽기 실패 " + e.toString());			
		}
	}
	
	public static void main(String[] args) 
	{ 
		new GUI();
	}
}