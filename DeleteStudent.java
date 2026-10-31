import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class DeleteStudent extends JFrame{
	private JLabel header;
	private JLabel nic;
	private JLabel name;
	private JLabel prfMarks;
	private JLabel dbmsMarks;
	
	private JTextField searchField;
	private JTextField nictxt;
	private JTextField nametxt;
	private JTextField prftxt;
	private JTextField dbmstxt;
	
	private JButton deleteBtn;
	private JButton closeBtn;
	private JButton searchBtn;
	
	private String studentId="";
	DeleteStudent (){
		setTitle("Delete Student Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("Delete Student");
		header.setFont(new Font("",1,25));
		header.setHorizontalAlignment(JLabel.RIGHT);
		add("North",header);
		
		JPanel lblPanel=new JPanel(new GridLayout(4,2,10,15));
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
		
		nictxt=new JTextField(10);
		nictxt.setFont(new Font("",1,20));
		
		nametxt=new JTextField(15);
		nametxt.setFont(new Font("",1,20));
		
		prftxt=new JTextField(10);
		prftxt.setFont(new Font("",1,20));
		dbmstxt=new JTextField(10);
		dbmstxt.setFont(new Font("",1,20));
		
		lblPanel.add(nic);
		lblPanel.add(nictxt);
		lblPanel.add(name);
		lblPanel.add(nametxt);
		lblPanel.add(prfMarks);
		lblPanel.add(prftxt);
		lblPanel.add(dbmsMarks);
		lblPanel.add(dbmstxt);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		deleteBtn=new JButton("Delete Student");
		deleteBtn.setFont(new Font("",1,15)); 
		
		closeBtn=new JButton("Close");
		closeBtn.setFont(new Font("",1,15)); 
		closeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		btnPanel.add(deleteBtn);
		btnPanel.add(closeBtn);
		
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
			}	
		});
		deleteBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Student student=new Student(studentId);
				boolean isEnter=AccessCollection.getInstatnce().getStudentManagement().deleteStudent(student);
				if(isEnter){
					new PopupMessage(
					studentId+" Successfully Deleted!",
						null,
						"DELETE NEW STUDENT",
						new Runnable(){
							public void run(){
								new DeleteStudent().setVisible(true);
							}
						}
					).setVisible(true);
					dispose();
				}
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
}
