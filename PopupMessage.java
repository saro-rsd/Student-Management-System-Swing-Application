import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class PopupMessage extends JFrame{
	private JButton nextBtn;
	private JButton homeBtn;
	private JLabel firstmessage;
	private JLabel secondmessage;
	
	PopupMessage(String message, String studentId,String nextBtnText,Runnable onNextAction){
		setTitle("Successfull");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	
		JPanel btnPanel=new JPanel(new GridLayout(1,2));
		JPanel nextBtnPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel homeBtnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		nextBtn=new JButton(nextBtnText);
		nextBtn.setFont(new Font("",1,20));
		nextBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				onNextAction.run();  //Runnable method eke parameter name eka -> new AddStudent().setVisvible();
			}
		});
		
		homeBtn=new JButton("BACK TO HOME PAGE");
		homeBtn.setFont(new Font("",1,20));
		homeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new StudentManagement().setVisible(true);
				dispose();
			}
		});
		
		nextBtnPanel.add(nextBtn);
		homeBtnPanel.add(homeBtn);
		btnPanel.add(homeBtnPanel);
		btnPanel.add(nextBtnPanel);
		add("South",btnPanel);
		
		JPanel msgPanel=new JPanel(new GridLayout(2,1));
		JPanel firstMsgpanel=new JPanel(new BorderLayout());
		JPanel secondMsgpanel=new JPanel(new BorderLayout());
		
		firstmessage=new JLabel(message);
		firstmessage.setFont(new Font("",1,30));
		firstMsgpanel.add(firstmessage);
		firstmessage.setHorizontalAlignment(SwingConstants.CENTER);
		firstmessage.setVerticalAlignment(SwingConstants.CENTER);  
		msgPanel.add(firstMsgpanel,BorderLayout.CENTER);
		
		if(studentId !=null){
			secondmessage=new JLabel(studentId);
			secondmessage.setFont(new Font("",1,14)); 
			secondMsgpanel.add(secondmessage);  
			secondmessage.setHorizontalAlignment(SwingConstants.CENTER);
			secondmessage.setVerticalAlignment(SwingConstants.CENTER);
			msgPanel.add(secondMsgpanel,BorderLayout.CENTER);
		}
		add("Center",msgPanel);
	}
}
