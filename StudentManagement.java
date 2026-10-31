import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class StudentManagement extends JFrame{
	
	private  JLabel header;
	private JLabel container;
	
	private JButton addStudent;
	private JButton updateStudent;
	private JButton viewStudent;
	private JButton deleteStudent;
	private JButton homeBtn;
	
	private AddStudent addStudentForm;
	private UpdateStudent updateStudentForm;
	private ViewStudent viewStudentForm;
	private DeleteStudent deleteStudentForm;
	
	StudentManagement(){
		setTitle("iCET Learning Management System");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//divide to two colums  main fraim
		JPanel main=new JPanel(new BorderLayout());
		JPanel rightPanel=new JPanel(new BorderLayout());
		JPanel leftPanel=new JPanel(new BorderLayout());
		
		JPanel firstSection=new JPanel(new GridLayout(3,1));
		JPanel secondSection=new JPanel(new GridLayout(4,1,0,0));
		
		firstSection.setBackground(new Color(30, 40, 80));
		secondSection.setBackground(new Color(30, 40, 80));
		
		rightPanel.add(firstSection,BorderLayout.NORTH);
		rightPanel.add(secondSection,BorderLayout.CENTER);
		
		main.add(leftPanel,BorderLayout.WEST);
		main.add(rightPanel,BorderLayout.EAST);
		
		//left column
		JLabel logo=new JLabel("iCET LOGO");
		logo.setFont(new Font("",Font.BOLD,23));
		leftPanel.add(logo,BorderLayout.CENTER);
		
		//right column
		header=new JLabel("Student Management");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setFont(new Font("",Font.BOLD,23));
		header.setForeground(Color.WHITE);
		
		container=new JLabel("Choose an option from the menu below to get started");
		container.setHorizontalAlignment(JLabel.CENTER);
		container.setForeground(Color.WHITE);
		firstSection.add(header);
		firstSection.add(container);
		
		//Add Button
		JPanel addBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		addStudent=new JButton("Add Student");
		addStudent.setFont(new Font("",Font.BOLD,15));
		addStudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(addStudentForm==null){
					addStudentForm=new AddStudent();
				}
				addStudentForm.setVisible(true);
			}
		});
		
		//Update Button
		JPanel updateBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		updateStudent=new JButton("Update Student");
		updateStudent.setFont(new Font("",Font.BOLD,15));
		updateStudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(updateStudentForm==null){
					updateStudentForm=new UpdateStudent();
				}
				updateStudentForm.setVisible(true);
			}
		});
		
		//View Button
		JPanel viewBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		viewStudent=new JButton("View Student Profile");
		viewStudent.setFont(new Font("",Font.BOLD,15));
		viewStudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(viewStudentForm==null){
					viewStudentForm=new ViewStudent();
				}
				viewStudentForm.setVisible(true);
			}
		});
		
		//Delete Button
		JPanel deleteBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		deleteStudent=new JButton("Delete Student");
		deleteStudent.setFont(new Font("",Font.BOLD,15));		
		deleteStudent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(deleteStudentForm==null){
					deleteStudentForm=new DeleteStudent();
				}
				deleteStudentForm.setVisible(true);
			}
		});
		
		JPanel homeBtnPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		homeBtn=new JButton("BACK TO HOME PAGE");
		homeBtn.setFont(new Font("",1,20));
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new HomePage().setVisible(true);
			}
		});
		homeBtnPanel.add(homeBtn);
		leftPanel.add("South",homeBtnPanel);
		
		addBtn.setOpaque(false);
		updateBtn.setOpaque(false);
		viewBtn.setOpaque(false);
		deleteBtn.setOpaque(false);
		homeBtn.setOpaque(false);
		header.setOpaque(false);
		container.setOpaque(false);
		homeBtn.setOpaque(false);
		
		addBtn.add(addStudent);
		updateBtn.add(updateStudent);
		viewBtn.add(viewStudent);
		deleteBtn.add(deleteStudent);
		secondSection.add(addBtn);
		secondSection.add(updateBtn);
		secondSection.add(viewBtn);
		secondSection.add(deleteBtn);
		
		add(main);	
	}
	
}
