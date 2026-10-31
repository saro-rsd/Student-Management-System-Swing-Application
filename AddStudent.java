import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class AddStudent extends JFrame{
	private JLabel header;
	private JLabel batchName;
	private JLabel nic;
	private JLabel name;
	private JLabel lecturemode;
	private JLabel dateOfBirth;
	private JLabel address;
	
	private JTextField batchtxt;
	private JTextField nictxt;
	private JTextField nametxt;
	private JTextField dateOfBirthtxt;
	private JTextField addresstxt;
	
	private JButton addBtn;
	private JButton closeBtn;
	private JButton homeBtn;
		
	AddStudent(){
		setTitle("Add Student Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("Add Student");
		header.setFont(new Font("",1,25));
		header.setHorizontalAlignment(JLabel.RIGHT);
		add("North",header);
		
		JPanel lblPanel=new JPanel(new GridLayout(6,2,10,15));
		lblPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));	
		
		batchName=new JLabel("Batch Name");
		batchName.setFont(new Font("",1,20));
		nic=new JLabel("NIC");
		nic.setFont(new Font("",1,20));
		name=new JLabel("Name");
		name.setFont(new Font("",1,20));
		lecturemode=new JLabel("Lecture Mode");
		lecturemode.setFont(new Font("",1,20));
		address=new JLabel("Address");
		address.setFont(new Font("",1,20));
		dateOfBirth=new JLabel("Date Of Birth");
		dateOfBirth.setFont(new Font("",1,20));
		
		batchtxt=new JTextField(10);
		batchtxt.setFont(new Font("",1,20));
		
		nictxt=new JTextField(10);
		nictxt.setFont(new Font("",1,20));
		
		nametxt=new JTextField(15);
		nametxt.setFont(new Font("",1,20));
		
		dateOfBirthtxt=new JTextField(15);
		dateOfBirthtxt.setFont(new Font("",1,20));
		
		addresstxt=new JTextField(15);
		addresstxt.setFont(new Font("",1,20));
		
		String [] lecModeOptions={"PHYSICAL","ONLINE"};
		JComboBox lecMode=new JComboBox(lecModeOptions);
		
		lblPanel.add(batchName);
		lblPanel.add(batchtxt);
		lblPanel.add(nic);
		lblPanel.add(nictxt);
		lblPanel.add(name);
		lblPanel.add(nametxt);
		lblPanel.add(lecturemode);
		lblPanel.add(lecMode);
		lblPanel.add(dateOfBirth);
		lblPanel.add(dateOfBirthtxt);
		lblPanel.add(address);
		lblPanel.add(addresstxt);
		add("Center",lblPanel);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addBtn=new JButton("Add Student");
		addBtn.setFont(new Font("",1,15)); 
		
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int batchNameString;
			
				try{
					batchNameString=Integer.parseInt(batchtxt.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please enter a valid batch number!");
					return;
				}
				
				boolean isBatchValid=isBatchValid(batchNameString);
				if(!isBatchValid){
					return;  
				}
				
				String nicString=nictxt.getText();
				boolean isnicValid=isnicValid(nicString);
				if(!isnicValid){
					return;  
				}
				
				String nameString=nametxt.getText();
				String lectureMode=(String) lecMode.getSelectedItem();
				String studentId=generateStudentId(batchNameString,lectureMode);
				
				String dob=dateOfBirthtxt.getText();
				String homeAddress=addresstxt.getText();
				
				Student student=new Student(studentId,nicString,nameString,-2,-2,dob,homeAddress);
				
				boolean isEnter=AccessCollection.getInstatnce().getStudentManagement().addStudent(student);
				
				if(isEnter){
					new PopupMessage(
						nameString+" Successfully Added!",
						studentId,
						"ADD NEW STUDENT",
						new Runnable(){
							public void run(){
								new AddStudent().setVisible(true);
								dispose();
							}
						}
					).setVisible(true);
					dispose();
				}
			}
		});
		
		JPanel homeBtnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		homeBtn=new JButton("BACK TO HOME PAGE");
		homeBtn.setFont(new Font("",1,20));
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new StudentManagement().setVisible(true);
			}
		});
		homeBtnPanel.add(homeBtn);
		add("South",homeBtnPanel);
		
		closeBtn=new JButton("Close");
		closeBtn.setFont(new Font("",1,15)); 
		closeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		btnPanel.add(addBtn);
		btnPanel.add(closeBtn);
		add("South",btnPanel);
	}
	
	public boolean isBatchValid(int batchNameString){
	
		for(int i=0;i<AccessCollection.getInstatnce().getStudentManagement().batches.length;i++){
					if(AccessCollection.getInstatnce().getStudentManagement().batches[i].getBatchName()==batchNameString){
						if(AccessCollection.getInstatnce().getStudentManagement().batches[i].getBatchStatus()==0){
							JOptionPane.showMessageDialog(null,"Student Can not be added to this batch because enrollment is closed");
							return false;
						}else{
							return true;
						}
					}
		}
		JOptionPane.showMessageDialog(null, "Batch does not exist!");
		return false; 
	}
	
	public boolean isnicValid(String nicString){
		for(int i=0;i<AccessCollection.getInstatnce().getStudentManagement().students.length;i++){
			if(AccessCollection.getInstatnce().getStudentManagement().students[i].getStudentNIC().equals(nicString)){
				JOptionPane.showMessageDialog(null,"This student already added to the system");
				return false;
			}
		}
		return true; 
	}
	
	public static String generateStudentId(int batchNameString,String lectureMode){
		int max=0;
		String batchNum= String.valueOf(batchNameString);
		for(int i=0;i<AccessCollection.getInstatnce().getStudentManagement().students.length;i++){
			if(AccessCollection.getInstatnce().getStudentManagement().students[i].getStudentId().substring(4,7).equals(batchNum)){
				int tempReg=Integer.parseInt(AccessCollection.getInstatnce().getStudentManagement().students[i].getStudentId().substring(7,10));
				if(max<tempReg){
					max=tempReg;
				}
			}
		}
		String nextreg=String.format("%03d",(max+1));
		if(lectureMode.equals("ONLINE")){
			return "OR24"+batchNameString+nextreg;
		}else{
			return "PR24"+batchNameString+nextreg;
		}
	}
}
