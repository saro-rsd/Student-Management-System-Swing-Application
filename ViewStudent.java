import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class ViewStudent  extends JFrame{
	private JLabel header;
	private JLabel nic;
	private JLabel name;
	private JLabel prfMarks;
	private JLabel dbmsMarks;
	private JLabel gpaMarks;
	
	private JTextField searchField;
	private JTextField nictxt;
	private JTextField nametxt;
	private JTextField prftxt;
	private JTextField dbmstxt;
	private JTextField gpatxt;
	
	private JButton viewBtn;
	private JButton homeBtn;
	private JButton searchBtn;
	
	private String studentId="";
	
	private double marksToGPA(int marks){
    if(marks >= 90 && marks <= 100) return 4.25;
    if(marks >= 80 && marks <= 89) return 4.00;
    if(marks >= 75 && marks <= 79) return 3.70;
    if(marks >= 70 && marks <= 74) return 3.30;
    if(marks >= 65 && marks <= 69) return 3.00;
    if(marks >= 60 && marks <= 64) return 2.70;
    if(marks >= 55 && marks <= 59) return 2.30;
    if(marks >= 50 && marks <= 54) return 2.00;
    if(marks >= 45 && marks <= 49) return 1.70;
    if(marks >= 40 && marks <= 44) return 1.30;
    if(marks >= 30 && marks <= 39) return 1.00;
    if(marks >= 20 && marks <= 29) return 0.70;
    return 0.0;
}
	
	ViewStudent (){
		setTitle("View Student Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("View Student");
		header.setFont(new Font("",1,25));
		header.setHorizontalAlignment(JLabel.RIGHT);
		add("North",header);
		
		JPanel lblPanel=new JPanel(new GridLayout(5,2,10,15));
		lblPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));	
		
		JPanel searchPanel=new JPanel(new GridLayout(1,2));
		searchField=new JTextField(15);
		searchField.setFont(new Font("",1,25));
		
		JPanel searchBtnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel searchFieldPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		searchBtn=new JButton("SEARCH");
		searchField.setFont(new Font("",1,20));
		searchBtnPanel.add(searchBtn);
		searchFieldPanel.add(searchField);
		searchPanel.add(searchField);
		searchPanel.add(searchBtn);
		add("North",searchPanel);
		
		nic=new JLabel("NIC");
		nic.setFont(new Font("",1,20));
		name=new JLabel("Name");
		name.setFont(new Font("",1,20));
		prfMarks=new JLabel("PRF Marks");
		prfMarks.setFont(new Font("",1,20));
		dbmsMarks=new JLabel("DBMS Marks");
		dbmsMarks.setFont(new Font("",1,20));
		gpaMarks=new JLabel("GPA Marks");
		gpaMarks.setFont(new Font("",1,20));
		
		nictxt=new JTextField(10);
		nictxt.setFont(new Font("",1,20));
		
		nametxt=new JTextField(15);
		nametxt.setFont(new Font("",1,20));
		
		prftxt=new JTextField(10);
		prftxt.setFont(new Font("",1,20));
		
		dbmstxt=new JTextField(10);
		dbmstxt.setFont(new Font("",1,20));
		
		gpatxt=new JTextField(10);
		gpatxt.setFont(new Font("",1,20));
		
		lblPanel.add(nic);
		lblPanel.add(nictxt);
		lblPanel.add(name);
		lblPanel.add(nametxt);
		lblPanel.add(prfMarks);
		lblPanel.add(prftxt);
		lblPanel.add(dbmsMarks);
		lblPanel.add(dbmstxt);
		lblPanel.add(gpaMarks);
		lblPanel.add(gpatxt);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		homeBtn=new JButton("BACK TO HOME PAGE");
		homeBtn.setFont(new Font("",1,20));
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new StudentManagement().setVisible(true);
				dispose();
			}
		});
		
		btnPanel.add(homeBtn);
		
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				studentId=searchField.getText().toUpperCase();
				boolean isStudentIdValid=isStudentIdValid(studentId);
				if(!isStudentIdValid){
					return;  
				}
				add("Center",lblPanel);
				add("South",btnPanel);
				revalidate(); 
				repaint();
				
				Student student=AccessCollection.getInstatnce().getStudentManagement().searchStudent(studentId);
				if(student==null){
					JOptionPane.showMessageDialog(null,studentId+" student not found...!!");
				}else{
					nictxt.setText(student.getStudentNIC());
					nametxt.setText(student.getStudentName());
					String prf=student.getPrfMarks()+"";
					String dbms=student.getdbmsMarks()+"";
					if(prf.equals("-1")){
						prftxt.setText("Absent");
						dbmstxt.setText(dbms);
					}else if(prf.equals("-2")){
						prftxt.setText("No been Conducted");
						dbmstxt.setText(dbms);
					}else if(dbms.equals("-1")){
						prftxt.setText(prf);
						dbmstxt.setText("Absent");
					}else if(dbms.equals("-2")){
						prftxt.setText(prf);
						dbmstxt.setText("No been Conducted");
					}else{
						prftxt.setText(prf);
						dbmstxt.setText(dbms);
					}
				}
				gpatxt.setText(gpaGenerate(student.getPrfMarks(),student.getdbmsMarks()));
			}	
		});
		
	}
	
	public boolean isStudentIdValid(String studentId){
		for(int i=0;i<AccessCollection.getInstatnce().getStudentManagement().students.length;i++){
			if(AccessCollection.getInstatnce().getStudentManagement().students[i].getStudentId().equals(studentId)){
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Student Id does not exist!");
		return false; 
	}
	
	public String gpaGenerate(int prfMarks, int dbmsMarks){
		double prfGPAValue = marksToGPA(prfMarks);
		double dbmsGPAValue = marksToGPA(dbmsMarks);
		
		double avgGPA = (prfGPAValue + dbmsGPAValue) / 2;
		return String.format("%.3f", avgGPA);
	}
}
