import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class UpdateStudent  extends JFrame{
	private JLabel header;
	private JLabel nic;
	private JLabel name;
	private JLabel prfMarks;
	private JLabel dbmsMarks;
	private JLabel dateOfBirth;
	private JLabel address;
	
	private JTextField searchField;
	private JTextField nictxt;
	private JTextField nametxt;
	private JTextField prftxt;
	private JTextField dbmstxt;
	private JTextField dateOfBirthtxt;
	private JTextField addresstxt;
	
	private JButton updateBtn;
	private JButton closeBtn;
	private JButton searchBtn;
	
	private String studentId="";
	UpdateStudent (){
		setTitle("Update Student Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("Update Student");
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
		
		address=new JLabel("Address");
		address.setFont(new Font("",1,20));
		dateOfBirth=new JLabel("Date Of Birth");
		dateOfBirth.setFont(new Font("",1,20));
		
		dateOfBirthtxt=new JTextField(15);
		dateOfBirthtxt.setFont(new Font("",1,20));
		
		addresstxt=new JTextField(15);
		addresstxt.setFont(new Font("",1,20));
		
		lblPanel.add(nic);
		lblPanel.add(nictxt);
		lblPanel.add(name);
		lblPanel.add(nametxt);
		lblPanel.add(prfMarks);
		lblPanel.add(prftxt);
		lblPanel.add(dbmsMarks);
		lblPanel.add(dbmstxt);
		lblPanel.add(dateOfBirth);
		lblPanel.add(dateOfBirthtxt);
		lblPanel.add(address);
		lblPanel.add(addresstxt);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		updateBtn=new JButton("Update Student");
		updateBtn.setFont(new Font("",1,15)); 
		
		closeBtn=new JButton("Close");
		closeBtn.setFont(new Font("",1,15)); 
		closeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		btnPanel.add(updateBtn);
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
		updateBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String nic = nictxt.getText();
				String name = nametxt.getText();
				String dob=dateOfBirthtxt.getText();
				String homeAddress=addresstxt.getText();
				
				int prf=0;
				int dbms=0;
				if(prftxt.getText().equals("Absent")){
						prf=-1;
						dbms=Integer.parseInt(dbmstxt.getText());
					}else if(prftxt.getText().equals("No been Conducted")){
						prf=-2;
						dbms=Integer.parseInt(dbmstxt.getText());
					}else if(dbmstxt.getText().equals("Absent")){
						prf=Integer.parseInt(prftxt.getText());
						dbms=-1;
					}else if(dbmstxt.equals("-2")){
						prf=Integer.parseInt(prftxt.getText());
						dbms=-2;
					}else{
						prf=Integer.parseInt(prftxt.getText());
						dbms=Integer.parseInt(dbmstxt.getText());
					}
				Student student=new Student(studentId,nic,name,prf,dbms,dob,homeAddress);
				boolean isEnter=AccessCollection.getInstatnce().getStudentManagement().updateStudent(student);
				if(isEnter){
					new PopupMessage(
					studentId+" Successfully Updated!",
						null,
						"UPDATE NEW STUDENT",
						new Runnable(){
							public void run(){
								new UpdateStudent().setVisible(true);
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
