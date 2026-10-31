import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class StudentManagementSystemOOP{
	public final int ENROLLMENTCLOSED=0;
	public final int ENROLLMENTOPEN=1;
	
	public Student[] students = {
        new Student("PR24105001", "199501012345", "Gunawardena Weerasinghe", 85, 66,"2005.08.21","123/A,Colombo"),
        new Student("PR24105002", "199503153872", "Senanayake Silva", 39, 45,"2004.09.10","23/2, Baththaramulla"),
        new Student("PR24105003", "199506202198", "Silva Kumara", -1, 93,"2005.06.23","Galle"),
        new Student("PR24105004", "199509102983", "Kumara Herath", 72, 58,"2005.03.05","Kegalle"), 
    };
	
	public Batch[] batches = {
        new Batch(105, 0),
        new Batch(106, 0),
        new Batch(107, 0),
        new Batch(108, 0),
        new Batch(109, 1),
        new Batch(110, 1)
    };
    public boolean addStudent(Student student){
		extendStudentArray();
		students[students.length-1]=student;
		return true;
	}
	
	public void extendStudentArray(){
		Student temp[] =new Student[students.length+1];
		for(int i=0;i<students.length;i++){
			temp[i]=students[i];
		}
		students=temp;
	}
    
    public Student searchStudent(String studentID){
		for(int i=0;i<students.length;i++){
			if(studentID.equalsIgnoreCase(students[i].getStudentId())){
				return students[i];
			}
		}
		return null;
	}
    
    public boolean updateStudent(Student student){
		int index=search(student);
		if(index!=-1){
			students[index]=student;
			return true;
		}
		return false;
	}
	
	public int search(Student student){
		for(int i=0;i<students.length;i++){
			if(student.getStudentId().equalsIgnoreCase(students[i].getStudentId())){
				return i;
			}
		}
		return -1;
	}
    
    public boolean deleteStudent(Student student){
		int index=search(student);
		if(index !=-1){
			Student[] temp=new Student[students.length-1];
			for(int i=0;i<index;i++){
				temp[i]=students[i];
			}
			for(int i=index;i<students.length-1;i++){
				temp[i]=students[i+1];
			}
			students=temp;
			return true;
		}
		return false;
	}
	
	public boolean addBatch(Batch batch){
		extendBatchArray();
		batches[batches.length-1]=batch;
		return true;
	}
	
	public void extendBatchArray(){
		Batch temp[]=new Batch[batches.length+1];
		for(int i=0;i<batches.length;i++){
			temp[i]=batches[i];
		}
		batches=temp;
	}
	
	public int getStudentCount(int batchName){
		int count=0;
		String batch=String.valueOf(batchName);   // 105 -> "105"
		for(int i=0;i<students.length;i++){
			if(students[i]!=null){
				String id=students[i].getStudentId();
				// ID format: PR24105001 -> characters 4 to 7 = "105"
				if(id!=null && id.length()>=7 && id.substring(4,7).equals(batch)){
					count++;
				}
			}
		}
		return count;	
	}
	
	
	
    public static void main(String args[]){
		new HomePage().setVisible(true);
	}
	
}



class HomePage extends JFrame{
	private  JLabel header;
	private  JLabel subheader;
	private JLabel container;
	
	private JButton studentManagement;
	private JButton batchManagement;
	private JButton gradeManagement;
	private JButton reportGenerator;
	
	HomePage(){
		setTitle("iCET Learning Management System");
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		header=new JLabel("iCET");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setFont(new Font("",Font.BOLD,23));
		header.setForeground(Color.WHITE);
		
		subheader=new JLabel("Learning Management System");
		subheader.setHorizontalAlignment(JLabel.CENTER);
		subheader.setFont(new Font("",Font.BOLD,23));
		subheader.setForeground(Color.WHITE);
		
		container=new JLabel("Choose an option from the menu below to get started");
		container.setHorizontalAlignment(JLabel.CENTER);
		container.setForeground(Color.WHITE);
		firstSection.add(header);
		firstSection.add(subheader);
		firstSection.add(container);
		
		JPanel studentBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		studentManagement=new JButton("Student Management");
		studentManagement.setFont(new Font("",Font.BOLD,15));
		studentManagement.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new StudentManagement().setVisible(true);
			}
		});
		
		JPanel batchBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		batchManagement=new JButton("Batch Management");
		batchManagement.setFont(new Font("",Font.BOLD,15));
		batchManagement.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new BatcUpdatehManagement().setVisible(true);
			}
		});
		
		JPanel gradeBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		gradeManagement=new JButton("Grade Management");
		gradeManagement.setFont(new Font("",Font.BOLD,15));
		gradeManagement.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				GradeManagement grademanagement=new GradeManagement();
				grademanagement.setVisible(true);
			}
		});
		
		JPanel reportBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		reportGenerator=new JButton("Report Generator");
		reportGenerator.setFont(new Font("",Font.BOLD,15));		
		reportGenerator.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				ReportGenerator reportgenerator=new ReportGenerator();
				reportgenerator.setVisible(true);
			}
		});
		
		
		studentBtn.setOpaque(false);
		batchBtn.setOpaque(false);
		gradeBtn.setOpaque(false);
		reportBtn.setOpaque(false);
		header.setOpaque(false);
		subheader.setOpaque(false);
		container.setOpaque(false);
		
		studentBtn.add(studentManagement);
		batchBtn.add(batchManagement);
		gradeBtn.add(gradeManagement);
		reportBtn.add(reportGenerator);
		secondSection.add(studentBtn);
		secondSection.add(batchBtn);
		secondSection.add(gradeBtn);
		secondSection.add(reportBtn);
		
		add(main);	
	}
}

class Student{
	private  String studentID;
	private  String studentNIC;
	private  String studentName;
	private  int prfMarks;
	private  int dbmsMarks;
	private  String studentBirthday;
	private  String studentAddress;
	
	Student(){
	
	}
	
	Student(String studentID){
		this.studentID=studentID;
	}
	Student(String studentID,String studentNIC,String studentName,int prfMarks,int dbmsMarks,String studentBirthday,String studentAddress){
		set(studentID,studentNIC,studentName,prfMarks,dbmsMarks,studentBirthday,studentAddress);
	}
	public void set(String studentID,String studentNIC,String studentName,int prfMarks,int dbmsMarks,String studentBirthday,String studentAddress){
		setStudentId(studentID);
		setStudentNIC(studentNIC);
		setStudentName(studentName);
		setPRFMarks(prfMarks);
		setDBMSMarks(dbmsMarks);
		setBirthday(studentBirthday);
		setAddress(studentAddress);
	}
	public String toString(){
		return studentID+"\t"+studentNIC+"\t"+studentName+"\t"+prfMarks+"\t"+dbmsMarks+"\t"+studentBirthday+"\t"+studentAddress;
	}
	public String getStudentId(){
		return studentID;
	}
	public String getStudentNIC(){
		return studentNIC;
	}
	public String getStudentName(){
		return studentName;
	}
	public String getStudentBirthday(){
		return studentBirthday;
	}
	public String getStudentAddress(){
		return studentAddress;
	}
	public int getPrfMarks(){
		return prfMarks;
	}
	public int getdbmsMarks(){
		return dbmsMarks;
	}
	public void setStudentId(String studentID){
		this.studentID=studentID;
	}
	public void setBirthday(String studentBirthday){
		this.studentBirthday=studentBirthday;
	}
	public void setAddress(String studentAddress){
		this.studentAddress=studentAddress;
	} 
	public void setStudentNIC(String studentNIC){
		this.studentNIC=studentNIC;
	}
	public void setStudentName(String studentName){
		this.studentName=studentName;
	}
	public void setPRFMarks(int prfMarks){
		this.prfMarks=prfMarks;
	}
	public void setDBMSMarks(int dbmsMarks){
		this.dbmsMarks=dbmsMarks;
	}
	public int getBatchName(){
		return Integer.parseInt(studentID.substring(4,7));
	}
}

class Batch{
	private int batchStatus;
	private int batchName;
	
	Batch(int batchName,int batchStatus){
		this.batchStatus=batchStatus;
		this.batchName=batchName;
	}
	public int getBatchStatus(){
		return batchStatus;
	}
	public int getBatchName(){
		return 
		batchName;
	}
	public void setBatchStatus(int batchStatus){
		this.batchStatus=batchStatus;
	}
}
