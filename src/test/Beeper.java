package test;

/*
 * Beeper.java requires no other files.
 */
 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
 
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
 
public class Beeper extends JPanel  {
	public static DBConnector DBConnector = new DBConnector();
	public static String uname;
	public static String pwd;
	//public static String identity;
	static String name = null;
	static String gender = null;
	static String pwd1 = null;
	static String age = null;
	static String college = null;
	static String grade = null;
	static String iclass = null;
	static String phone = null;
	static String province = null;
    static JButton button;
 
//    public Beeper() {
//        super(new BorderLayout());
//        button = new JButton("Click Me");
//        button.setPreferredSize(new Dimension(2000, 800));
//        add(button, BorderLayout.CENTER);
//        button.addActionListener(
//        		new ActionListener() {
//        			public void actionPerformed(ActionEvent e) {
//        				Toolkit.getDefaultToolkit().beep();
//        			}
//        		}
//        		);
//    }
 
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static JFrame frame = new JFrame("学生成绩查询管理系统-请登录");
    private static void createAndShowGUI() {
        //Create and set up the window.

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
//        JComponent newContentPane = new Beeper();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
        
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
 
        //Display the window.
//        frame.pack();
        frame.setVisible(true);
    }
    private static void funcClassInfoButtonPanel() throws SQLException {
    	JFrame changeGradeFrame = new JFrame("成绩修改");
    	int windowWidth = frame.getWidth(); //获得窗体宽
    	int windowHeight = frame.getHeight(); //获得窗体高
    	Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
    	int screenWidth = screenSize.width; //获取屏幕的宽
    	int screenHeight = screenSize.height; //获取屏幕的高
    	changeGradeFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
    	changeGradeFrame.setSize(200, 400);
        changeGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if (DBConnector.identity.equals("student")) {
        	JFrame errorFrame = new JFrame();
        	JPanel falsePanel = new JPanel();
        	errorFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
        	errorFrame.setSize(200, 400);
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	errorFrame.add(falsePanel);
//	        placeComponents(panel);
	        //Display the window.
	        errorFrame.pack();
	        errorFrame.setVisible(true);
			JLabel errorLabel = new JLabel("您没有权限！！");
//			errorLabel.setBounds(10,20,80,25);
			falsePanel.add(errorLabel);
//			confirmButton.setBounds(10, 80, 80, 25);
			JButton confirmButton = new JButton("确认");
			falsePanel.add(confirmButton);
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					errorFrame.dispose();
				}
			});
        }
        if (DBConnector.identity.equals("teacher")) {
        String[][] tableData = new String [1000][5];
    	JFrame gradeFrame = new JFrame("班级表格");
    	gradeFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
    	gradeFrame.setSize(200, 400);
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JTable table;
    	Object[] columnTitle = {"Name" , "StuID", "Gender", "Age", "Province" };
	
    	ResultSet infoRS = null;
    	ResultSet gradeRS = null;
    	gradeRS = DBConnector.getClassInfo();
    	int row = 0;
    	while (gradeRS.next()) {
    		System.out.println(gradeRS.getString(3));
    		
    		tableData[row][0] = gradeRS.getString(3).trim();
    		tableData[row][1] = gradeRS.getString(1).trim();
    		tableData[row][2] = gradeRS.getString(4).trim();
    		tableData[row][3] = gradeRS.getString(5).trim();
    		tableData[row][4] = gradeRS.getString(8).trim();
    		row++;
    		
    		
    	}
    	table = new JTable(tableData , columnTitle);
    	//table.setFont(new java.awt.Font("Times New Roman", 1, 16));
        //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
        gradeFrame.add(new JScrollPane(table));
        gradeFrame.pack();
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeFrame.setVisible(true);
        
        }
}

    private static void funcChangeGradeButtonPanel() throws SQLException {
    	JFrame changeGradeFrame = new JFrame("成绩录入");
    	changeGradeFrame.setSize(200, 400);
    	int windowWidth = frame.getWidth(); //获得窗体宽
    	int windowHeight = frame.getHeight(); //获得窗体高
    	Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
    	int screenWidth = screenSize.width; //获取屏幕的宽
    	int screenHeight = screenSize.height; //获取屏幕的高
    	changeGradeFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
    	changeGradeFrame.setSize(200, 400);
        changeGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if (DBConnector.identity.equals("student")) {
        	JFrame errorFrame = new JFrame();
        	JPanel falsePanel = new JPanel();
        	errorFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
        	errorFrame.setSize(200, 400);
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	errorFrame.add(falsePanel);
        	
//	        placeComponents(panel);
	        //Display the window.
	        errorFrame.pack();
	        errorFrame.setVisible(true);
			JLabel errorLabel = new JLabel("您没有权限！！");
//			errorLabel.setBounds(10,20,80,25);
			falsePanel.add(errorLabel);
//			confirmButton.setBounds(10, 80, 80, 25);
			JButton confirmButton = new JButton("确认");
			falsePanel.add(confirmButton);
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					errorFrame.dispose();
				}
			});
        }
        if (DBConnector.identity.equals("teacher")) {
        	JPanel changePanel = new JPanel();
        	changeGradeFrame.add(changePanel);
        	changePanel.setLayout(null);
//        	changeGradeFrame.pack();
        	JLabel IDLabel = new JLabel("学号");
        	JLabel subjectLabel = new JLabel("科目");
        	JLabel gradeLabel = new JLabel("成绩");
        	JTextField IDText = new JTextField();
        	JTextField subjectText = new JTextField();
        	JTextField gradeText = new JTextField();
        	JButton changeButton = new JButton("录入成绩！！");
        	
            IDLabel.setFont(new java.awt.Font("楷体", 1, 16));
            subjectLabel.setFont(new java.awt.Font("楷体", 1, 16));
            gradeLabel.setFont(new java.awt.Font("楷体", 1, 16));
            IDLabel.setBounds(30,20,80,25);
            subjectLabel.setBounds(30,60,80,25);
            gradeLabel.setBounds(30,100,80,25);
            IDLabel.setFont(new java.awt.Font("楷体", 1, 16));
            subjectLabel.setFont(new java.awt.Font("楷体", 1, 16));
            gradeLabel.setFont(new java.awt.Font("楷体", 1, 16));
            changeButton.setBounds(30,200,180,45);
            
            IDText.setBounds(120,20,100,25);
            subjectText.setBounds(120,60,100,25);
            gradeText.setBounds(120,100,100,25);
            changePanel.add(IDText);
            changePanel.add(gradeText);
            changePanel.add(subjectText);
            changePanel.add(IDLabel);
            changePanel.add(gradeLabel);
            changePanel.add(subjectLabel);
            changeGradeFrame.setVisible(true);
            changePanel.add(changeButton);

        	
        }
        
        ResultSet changeGradeRS = null;
        changeGradeRS = DBConnector.getGrade();
    }
    private static void funcGradeButtonPanel() throws SQLException {
    	
    	
    	String[][] tableData = new String [1000][3];
    	JFrame gradeFrame = new JFrame("成绩表格");
    	int windowWidth = frame.getWidth(); //获得窗体宽
    	int windowHeight = frame.getHeight(); //获得窗体高
    	Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
    	int screenWidth = screenSize.width; //获取屏幕的宽
    	int screenHeight = screenSize.height; //获取屏幕的高
    	gradeFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
    	gradeFrame.setSize(200, 400);
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JTable table;
    	Object[] columnTitle = {"STUDENT_ID" , "SUBJECT" , "GRADE"};
	
    	ResultSet infoRS = null;
    	ResultSet gradeRS = null;
    	gradeRS = DBConnector.getGrade();
    	int row = 0;
    	while (gradeRS.next()) {
    		System.out.println(gradeRS.getString(2));
    		
    		tableData[row][0] = gradeRS.getString(2).trim();
    		tableData[row][1] = gradeRS.getString(3).trim();
    		tableData[row][2] = gradeRS.getString(4).trim();
    		row++;
    		
    		
    	}
    	table = new JTable(tableData , columnTitle);
    	table.setFont(new java.awt.Font("Times New Roman", 1, 16));
        //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
        gradeFrame.add(new JScrollPane(table));
        gradeFrame.pack();
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeFrame.setVisible(true);
    	
        JMenu jm=new JMenu("选项") ;     //创建JMenu菜单对象
        JMenuItem t1=new JMenuItem("成绩修改") ;  //菜单项
        JMenuItem t2=new JMenuItem("成绩删除") ;//菜单项
        t1.setFont(new java.awt.Font("楷体", 1, 16));
        t2.setFont(new java.awt.Font("楷体", 1, 16));
        jm.setFont(new java.awt.Font("楷体", 1, 16));
        if (DBConnector.identity.equals("student")) {
        	t1.setEnabled(false);
        	t2.setEnabled(false);
        }
        t1.addActionListener(
        		new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				if (DBConnector.equals("student")) {
          					System.out.println("您没有权限");
          					
          				}
          				
									}
								}
        		);
        
        jm.add(t1) ;   //将菜单项目添加到菜单
        jm.add(t2) ;    //将菜单项目添加到菜单
        JMenuBar br = new JMenuBar() ;  //创建菜单工具栏
        br.add(jm) ;      //将菜单增加到菜单工具栏
        gradeFrame.setJMenuBar(br) ;  //为 窗体设置  菜单工具栏

        
        JPanel gradePanel = new JPanel();
    	JLabel nameLabel = new JLabel();
    	JTextField nameText = new JTextField(30);
    	nameText.setFont(new java.awt.Font("Times New Roman", 1, 16));
    	nameLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
    	
        if (DBConnector.identity.equals("student")) {
        	try {
    			infoRS = DBConnector.getInfo();
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	while (infoRS.next()) {
            	name = infoRS.getString(3);
            	gender = infoRS.getString(4);
            	age = infoRS.getString(5);
            	grade = infoRS.getString(6);
            	iclass = infoRS.getString(7);
            	province = infoRS.getString(8);
            	pwd = infoRS.getString(9);
        	}
        }
        if (DBConnector.identity.equals("teahcer")) {
        	try {
        		infoRS = DBConnector.getInfo();
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
    	
    	
    	
    	
    	
    	
    	
    }
    private static void funcInfoButtonPanel() throws SQLException {

    	ResultSet rs = null;
    	JFrame infoFrame = new JFrame();
    	int windowWidth = frame.getWidth(); //获得窗体宽
    	int windowHeight = frame.getHeight(); //获得窗体高
    	Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
    	int screenWidth = screenSize.width; //获取屏幕的宽
    	int screenHeight = screenSize.height; //获取屏幕的高
    	infoFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示

        infoFrame.setSize(200, 400);
        infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	try {
			rs = DBConnector.getInfo();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	JPanel infoPanel = new JPanel();
    	infoFrame.add(infoPanel);

    	infoPanel.setLayout(null);
    	JButton changeButton = new JButton("修改个人信息");
        JLabel nameLabel = new JLabel("Name:");
        JLabel genderLabel = new JLabel("Gender:");
        JLabel ageLabel = new JLabel("Age:");

        JTextField infoText = new JTextField(30);
        JTextField nameText = new JTextField(30);
        JTextField genderText = new JTextField(30);
        JTextField ageText = new JTextField(30);

        nameLabel.setBounds(30,20,80,25);
        genderLabel.setBounds(30,60,80,25);
        ageLabel.setBounds(30,100,80,25);
        genderLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
        ageLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
        nameLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
        

        
        nameText.setBounds(120,20,100,25);
        genderText.setBounds(120,60,100,25);
        ageText.setBounds(120,100,100,25);
        nameText.setFont(new java.awt.Font("Times New Roman", 1, 16));
        genderText.setFont(new java.awt.Font("宋体", 1, 16));
        ageText.setFont(new java.awt.Font("Times New Roman", 1, 16));
        
        infoPanel.add(nameLabel);
        infoPanel.add(nameText);
        infoPanel.add(genderLabel);
        infoPanel.add(genderText);
        infoPanel.add(ageLabel);
        infoPanel.add(ageText);

        

        if (DBConnector.identity.equals("student")) {
        	while (rs.next()) {
            	name = rs.getString(3);
            	gender = rs.getString(4);
            	age = rs.getString(5);
            	grade = rs.getString(6);
            	iclass = rs.getString(7);
            	province = rs.getString(8);
            	pwd = rs.getString(9);
            	nameText.setText(name.trim());
        	}
        	
            JLabel gradeLabel = new JLabel("Grade:");
        	JLabel classLabel = new JLabel("Class:");
            JLabel provinceLabel = new JLabel("Province:");
            JLabel pwdLabel = new JLabel("Password:");
            JTextField gradeText = new JTextField(30);
            JTextField classText = new JTextField(30);
            JTextField provinceText = new JTextField(30);
            JTextField pwdText = new JTextField(30);
            

            gradeLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
            classLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
            provinceLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));
            pwdLabel.setFont(new java.awt.Font("Times New Roman", 1, 16));

            
            
            classLabel.setBounds(30,140,80,25);
            provinceLabel.setBounds(30,180,80,25);
            gradeLabel.setBounds(30,220,80,25);
            pwdLabel.setBounds(30,260,80,25);
            
            classText.setBounds(120,140,100,25);
            provinceText.setBounds(120,180,100,25);
            gradeText.setBounds(120,220,100,25);
            pwdText.setBounds(120,260,100,25);
            classText.setFont(new java.awt.Font("Times New Roman", 1, 16));
            provinceText.setFont(new java.awt.Font("Times New Roman", 1, 16));
            gradeText.setFont(new java.awt.Font("Times New Roman", 1, 16));
            pwdText.setFont(new java.awt.Font("Times New Roman", 1, 16));
            
            nameText.setText(name.trim());
            genderText.setText(gender.trim());
            ageText.setText(age.trim());
            gradeText.setText(grade.trim());
            pwdText.setText(pwd.trim());
            classText.setText(iclass.trim());
            gradeText.setText(grade.trim());
            provinceText.setText(province.trim());
            infoPanel.add(classLabel);
            infoPanel.add(classText);
            infoPanel.add(provinceLabel);
            infoPanel.add(provinceText);
            infoPanel.add(pwdLabel);
            infoPanel.add(pwdText);        
            infoPanel.add(gradeLabel);
            infoPanel.add(gradeText);
            
            
            changeButton.addActionListener(
    				new ActionListener() {
    					public void actionPerformed(ActionEvent e) {
    						
    						try {
    							if (DBConnector.isLeagalFunction("changeInfo")) {
    								
    								//uname = userText.getText();
    								name = nameText.getText().toString().trim();
    								gender = genderText.getText().trim();
    								age = ageText.getText().trim();
    								grade = gradeText.getText().trim();
    								pwd = pwdText.getText().trim();
    								iclass = classText.getText().trim();
    								province = provinceText.getText().trim();
    								int rs = DBConnector.changeInfo(name, gender, age, grade, iclass, province, pwd);
    								JFrame successFrame = new JFrame();
    								JPanel successPanel = new JPanel();
    								successFrame.add(successPanel);
    								if (rs == 1)successPanel.add(new JLabel("成功！"));
    								else successPanel.add(new JLabel("失败！"));
    								successFrame.setVisible(true);
    								
    								
    								
    							}
    						} catch (SQLException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						} catch (Throwable e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    					}
    				}
    				);
            changeButton.setBounds(45, 310, 150, 35);
            infoPanel.add(changeButton);
        }
        if (DBConnector.identity.equals("Teacher")) {
        	JLabel collegeLabel = new JLabel("College:");
            JLabel phoneLabel = new JLabel("Phone:");
            JLabel pwdLabel = new JLabel("Password:");
            JTextField collegeText = new JTextField(20);
            JTextField phoneText = new JTextField(20);
            JTextField pwdText = new JTextField(20);
            infoPanel.add(collegeLabel);
            infoPanel.add(collegeText);
            infoPanel.add(phoneLabel);
            infoPanel.add(phoneText);
            infoPanel.add(pwdLabel);
            infoPanel.add(pwdText);
        }
        

        infoFrame.setVisible(true);

    }
    private static void placeComponents(JPanel panel) {

    	
    	
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* 
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        userText.setText("217001");
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
 
        panel.add(passwordLabel);

        /* 
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);

        panel.add(passwordText);

        
        
        JButton confirmButton = new JButton("确认");

        
        
        
        // 创建登录按钮
        
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(
          		new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				uname = userText.getText();
          				pwd = String.valueOf(passwordText.getPassword());
          				try {
							boolean rs =  DBConnector.login(uname, pwd);
							if (rs == false) {
								JFrame errorFrame = new JFrame("Waring");
								JPanel falsePanel = new JPanel();
								errorFrame.add(falsePanel);
						        placeComponents(panel);
						        //Display the window.
						        errorFrame.pack();
						        errorFrame.setVisible(true);
								JLabel errorLabel = new JLabel("用户名或密码错误");
//								errorLabel.setBounds(10,20,80,25);
								falsePanel.add(errorLabel);
//								confirmButton.setBounds(10, 80, 80, 25);
								falsePanel.add(confirmButton);
								confirmButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										errorFrame.dispose();
									}
								});
								
								
							}
							else {
								frame.dispose();
								frame.setVisible(false);
		          				detailedInfo();
								
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

          			}
          		}
          		);
    }
    
    public static void detailedInfo() {
    	JFrame mainFrame = new JFrame("主界面");
        mainFrame.setSize(300, 200*2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		int windowWidth = frame.getWidth(); //获得窗体宽
		int windowHeight = frame.getHeight(); //获得窗体高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		mainFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗体居中显示
		mainPanel.setLayout(null);
        JButton funcInfoButton = new JButton("个人信息");
        JButton funcGradeButton = new JButton("查询成绩");
        JButton funcChangeGradeButton = new JButton("成绩录入");
        JButton funcClassInfoButton = new JButton("查询班级信息");
        funcInfoButton.setBounds(60, 40, 160, 60);
        funcGradeButton.setBounds(60, 120, 160, 60);
        funcChangeGradeButton.setBounds(60, 200, 160, 60);
        funcClassInfoButton.setBounds(60, 280, 160, 60);
		mainFrame.add(mainPanel);
//        placeComponents(panel);
        //Display the window.
        mainPanel.setVisible(true);
		JLabel infoLabel = new JLabel("学生成绩查询系统");
		infoLabel.setBounds(90,20,350,15);
		mainPanel.add(infoLabel);
		mainPanel.add(funcClassInfoButton);
		mainPanel.add(funcChangeGradeButton);
		mainPanel.add(funcGradeButton);
		mainPanel.add(funcInfoButton);
		funcClassInfoButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							funcClassInfoButtonPanel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				);
		funcInfoButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							funcInfoButtonPanel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				);
		funcGradeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							funcGradeButtonPanel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				);
		funcChangeGradeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							funcChangeGradeButtonPanel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				);
		
		
		
//        mainFrame.pack();
        mainFrame.setVisible(true);
		
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}