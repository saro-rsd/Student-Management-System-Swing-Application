import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class AddBatches extends JFrame{
	private JLabel header;
	private JLabel batchName;
	
	private JTextField batchtxt;
	
	private JButton addBtn;
	private JButton closeBtn;
	private JButton homeBtn;
		
	AddBatches(){
		setTitle("Add Batches Form");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		header=new JLabel("Add Batches");
		header.setFont(new Font("",1,25));
		header.setHorizontalAlignment(JLabel.RIGHT);
		add("North",header);
		
		JPanel lblPanel=new JPanel(new GridLayout(1,2,10,15));
		lblPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));	
		
		JPanel BatchNamePanel=new JPanel(new BorderLayout());
		batchName=new JLabel("Batch Name");
		batchName.setFont(new Font("",1,25));
		
		batchtxt=new JTextField(8);
		batchtxt.setFont(new Font("",1,20));
		
		lblPanel.add(batchName);
		lblPanel.add(batchtxt);

		BatchNamePanel.add(lblPanel,BorderLayout.NORTH);
		
		
		add("North",lblPanel);
		
		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addBtn=new JButton("Add Batch");
		addBtn.setFont(new Font("",1,15)); 
		
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int batchNameInt;
				
				try{
					batchNameInt=Integer.parseInt(batchtxt.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please enter a valid batch number!");
					return;
				}
				
				Batch batch=new Batch(batchNameInt,1);
				if(!isBatchValid(batchNameInt)){
					JOptionPane.showMessageDialog(null, "This Batch Already Added!");
					return;
				}
				
				boolean isEnter=StudentManagementSystemOOP.addBatch(batch);
				if(isEnter){
					new PopupMessage(
						batchNameInt+" Successfully Added!",
						null,
						"ADD NEW BATCH",
						new Runnable(){
							public void run(){
								new AddBatches().setVisible(true);
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
				dispose();
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
	public boolean isBatchValid(int batchNameInt){
		for(int i=0;i<StudentManagementSystemOOP.batches.length;i++){
			if(batchNameInt==StudentManagementSystemOOP.batches[i].getBatchName()){
				return false;
			}
		}
		return true;
	}
	
}
