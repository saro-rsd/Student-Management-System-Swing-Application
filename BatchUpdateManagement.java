import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class BatcUpdatehManagement extends JFrame{
	
	private  JLabel header;
	private JLabel container;
	
	private JButton addBatches;
	private JButton updateBatches;
	private JButton viewBatches;
	private JButton homeBtn;
	
	private AddBatches addBatchesForm;
	private UpdateBatches updateBatchesForm;
	private ViewBatches viewBatchesForm;
	
	BatcUpdatehManagement(){
		setTitle("iCET Learning Management System");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//divide to two colums  main fraim
		JPanel main=new JPanel(new BorderLayout());
		JPanel rightPanel=new JPanel(new BorderLayout());
		JPanel leftPanel=new JPanel(new BorderLayout());
		
		JPanel firstSection=new JPanel(new GridLayout(3,1));
		JPanel secondSection=new JPanel(new GridLayout(3,1,0,0));
		
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
		header=new JLabel("Batch Management");
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
		addBatches=new JButton("Add Batches");
		addBatches.setFont(new Font("",Font.BOLD,15));
		addBatches.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(addBatchesForm==null){
					addBatchesForm=new AddBatches();
				}
				addBatchesForm.setVisible(true);
			}
		});
		
		//Update Button
		JPanel updateBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		updateBatches=new JButton("Update Batches");
		updateBatches.setFont(new Font("",Font.BOLD,15));
		updateBatches.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(updateBatchesForm==null){
					updateBatchesForm=new UpdateBatches();
				}
				updateBatchesForm.setVisible(true);
			}
		});
		
		//View Button
		JPanel viewBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		viewBatches=new JButton("View Batches Details");
		viewBatches.setFont(new Font("",Font.BOLD,15));
		viewBatches.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(viewBatchesForm==null){
					viewBatchesForm=new ViewBatches();
				}
				viewBatchesForm.setVisible(true);
			}
		});
		
		JPanel homeBtnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
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
		homeBtn.setOpaque(false);
		header.setOpaque(false);
		container.setOpaque(false);
		homeBtn.setOpaque(false);
		
		addBtn.add(addBatches);
		updateBtn.add(updateBatches);
		viewBtn.add(viewBatches);
		secondSection.add(addBtn);
		secondSection.add(updateBtn);
		secondSection.add(viewBtn);
		
		add(main);	
	}
}
