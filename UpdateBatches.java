import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class UpdateBatches extends JFrame{
	private JLabel header;
	private JLabel studentCountLbl;
	private JLabel statusLbl;
	
	
	private JTextField batchtxt;
	private JTextField studentCounttxt;
	
	private JCheckBox openChk;
	private JCheckBox closedChk;
	
	private JButton searchBtn;
	private JButton updateBtn;
	private JButton cancelBtn;
	private JButton homeBtn;
	
	private Batch foundBatch;
		
	UpdateBatches(){
		setTitle("Update Batches Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("Update Batches");
		header.setFont(new Font("",1,25));
		header.setHorizontalAlignment(JLabel.RIGHT);
		add("North",header);
		
		JPanel centerPanel=new JPanel(new GridLayout(3,1,10,10));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));	
		
		JPanel searchPanel=new JPanel(new GridLayout(1,2,20,0));
		batchtxt=new JTextField(8);
		batchtxt.setFont(new Font("",1,20));	
		batchtxt.setHorizontalAlignment(JTextField.CENTER);
		
		searchBtn=new JButton("SEARCH");
		searchBtn.setFont(new Font("",1,15));	
		
		
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int batchNameInt;
				try{
					batchNameInt=Integer.parseInt(batchtxt.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null,"Please enter a valid batch number!");
					return;
				}
				
				foundBatch=findBatch(batchNameInt);
				if(foundBatch==null){
					JOptionPane.showMessageDialog(null,"Batch not found!");
					studentCounttxt.setText("");
					openChk.setSelected(false);
					closedChk.setSelected(false);
					return;
				}
				
				studentCounttxt.setText(String.valueOf(StudentManagementSystemOOP.getStudentCount(foundBatch.getBatchName())));
				boolean isOpen=foundBatch.getBatchStatus()==1;
				openChk.setSelected(isOpen);
				closedChk.setSelected(!isOpen);
			}
		});
		
		searchPanel.add(batchtxt);
		searchPanel.add(searchBtn);
		centerPanel.add(searchPanel);
		
		JPanel countPanel=new JPanel(new GridLayout(1,2,20,0));
		studentCountLbl=new JLabel("Student Count");
		studentCountLbl.setFont(new Font("",1,18));
		studentCounttxt=new JTextField(8);
		studentCounttxt.setFont(new Font("",1,20));
		studentCounttxt.setHorizontalAlignment(JTextField.CENTER);
		studentCounttxt.setEditable(false);
		countPanel.add(studentCountLbl);
		countPanel.add(studentCounttxt);
		centerPanel.add(countPanel);
		
		JPanel statusPanel=new JPanel(new GridLayout(1,3,10,0));
		statusLbl=new JLabel("Current Status");
		statusLbl.setFont(new Font("",1,18));
		openChk=new JCheckBox("ENROLLMENT OPEN");
		closedChk=new JCheckBox("ENROLLMENT CLOSED");
		openChk.setFont(new Font("",1,14));
		closedChk.setFont(new Font("",1,14));
		
		ButtonGroup group=new ButtonGroup();
		group.add(openChk);
		group.add(closedChk);	
		
		statusPanel.add(statusLbl);
		statusPanel.add(openChk);
		statusPanel.add(closedChk);
		centerPanel.add(statusPanel);

		add("Center",centerPanel);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));

		cancelBtn=new JButton("CANCEL");
		cancelBtn.setFont(new Font("",1,15));
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		updateBtn=new JButton("UPDATE");
		updateBtn.setFont(new Font("",1,15));
		updateBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(foundBatch==null){
					JOptionPane.showMessageDialog(null,"Please search a batch first!");
					return;
				}
				if(!openChk.isSelected() && !closedChk.isSelected()){
					JOptionPane.showMessageDialog(null,"Please select a status!");
					return;
				}

				int newStatus=openChk.isSelected() ? 1 : 0;
				foundBatch.setBatchStatus(newStatus);

				new PopupMessage(
					foundBatch.getBatchName()+" Batch Status Successfully Updated!",
					null,
					"UPDATE NEW BATCH",
					new Runnable(){
						public void run(){
							new UpdateBatches().setVisible(true);
						}
					}
				).setVisible(true);
				dispose();
			}
		});
			
		btnPanel.add(cancelBtn);
		btnPanel.add(updateBtn);

		JPanel homeBtnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		homeBtn=new JButton("BACK TO HOME PAGE");
		homeBtn.setFont(new Font("",1,15));
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new StudentManagement().setVisible(true);
				dispose();
			}
		});
		homeBtnPanel.add(homeBtn);

		JPanel southPanel=new JPanel(new BorderLayout());
		southPanel.add(homeBtnPanel,BorderLayout.WEST);
		southPanel.add(btnPanel,BorderLayout.EAST);
		add("South",southPanel);
	}
	
	private Batch findBatch(int batchNameInt){
		for(int i=0;i<StudentManagementSystemOOP.batches.length;i++){
			if(StudentManagementSystemOOP.batches[i]!=null &&
			   StudentManagementSystemOOP.batches[i].getBatchName()==batchNameInt){
				return StudentManagementSystemOOP.batches[i];
			}
		}
		return null;
	}
}
