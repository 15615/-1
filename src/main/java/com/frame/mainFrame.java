package com.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static Criterion_layer dialog;
	public static Decision_layer dialog_2;
	public static Evaluation dialog_3;
	public static Double[] real_weight =new Double[100];
	public static int sum_decision;
	
	private JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() throws Exception{
		for(int i=0;i<real_weight.length;i++)
			real_weight[i]=(double) 0;
		
		
		setTitle("\u5C42\u6B21\u5206\u6790\u6CD5\u5DE5\u5177");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u51C6\u5219\u5C42\u8F93\u5165\uFF1A");
		lblNewLabel.setBounds(45, 70, 94, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u65B9\u6848\u5C42\u8F93\u5165\uFF1A");
		label.setBounds(45, 116, 94, 18);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(153, 31, 132, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(153, 70, 132, 18);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u76EE\u6807\u5C42\u8F93\u5165\uFF1A");
		label_1.setBounds(45, 31, 94, 18);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 113, 132, 18);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u51B3\u7B56\u540D\u79F0");
		lblNewLabel_1.setBounds(299, 31, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbln = new JLabel("/n\u51C6\u5219\u5C42\u4E2A\u6570");
		lbln.setBounds(299, 70, 93, 18);
		contentPane.add(lbln);
		
		JLabel lbln_1 = new JLabel("/n\u65B9\u6848\u5C42\u4E2A\u6570");
		lbln_1.setBounds(299, 113, 93, 18);
		contentPane.add(lbln_1);
		
		JButton btnNewButton = new JButton("\u5224\u65AD\u77E9\u9635");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dialog = new Criterion_layer(Integer.parseInt(textField_1.getText()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
//					for(int i=0;i<dialog.ww.length;i++)
//						System.out.println(dialog.ww[i]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.setBounds(425, 66, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5224\u65AD\u77E9\u9635");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog_2 = new Decision_layer(Integer.parseInt(textField_1.getText()),textField_2.getText());
				dialog_2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog_2.setVisible(true);
				
			}
		});
		button.setBounds(425, 112, 113, 27);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("\u8BA1\u7B97");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				for(int i=0;i<dialog.ww.length;i++)
//				System.out.println(dialog.ww[i]);
				String[] str= textField_2.getText().split(" ");//这里将第二个参数设置为-1
				String[] strr= new String[6];
				strr[0]="0" ;
				for (int i = 0; i < str.length; ++i){
					strr[i+1] =String.valueOf(Integer.parseInt(strr[i]) + Integer.parseInt(str[i]));
				}
		        for (int i = 0; i < str.length; ++i){
		        	sum_decision = sum_decision + Integer.parseInt(str[i]);
		        	}

		        for (int le = 0; le < str.length; ++le)
		        {
		        	for(int i = 0;i<Integer.parseInt(str[le]);i++)
					{
//						real_weight[i]=real_weight[i] + dialog_2.zj[j].ww[i] * dialog.ww[j];//完全层次_计算公式
		        		int p = Integer.parseInt(strr[le]);
						real_weight[i+p]= dialog.ww[le]*dialog_2.zj[le].ww[i];//不完全层次_计算公式
					System.out.println("方案层对目标层的组合权向量："+real_weight[i+p]);
					}
		        }
		        

				for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
				System.out.println("准则层参数："+dialog.ww[j]);
				
//					for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
//						for(int i = 0;i<sum_decision;i++)
//							System.out.println("决策层参数："+dialog_2.zj[j].ww[i]);
		        for (int le = 0; le < str.length; ++le)
		        {
		        	for(int i = 0;i<Integer.parseInt(str[le]);i++)
					{
						System.out.println("决策层参数："+dialog_2.zj[le].ww[i]);
					}
		        }
				
				textField_3.setText("计算结果\n");
				textField_3.setText(textField_3.getText()+"最终的组合权向量\n");
				for(int i = 0;i<sum_decision;i++)
					textField_3.setText(textField_3.getText()+real_weight[i]+"     "+"\n");	
				
				
			}
		});
		btnNewButton_1.setBounds(231, 169, 113, 27);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5185\u5B58\u9650\u5236\uFF0C\u65B9\u6848\u5C42\u4EC5\u9650\u4E8E3\u5C42");
		lblNewLabel_2.setBounds(228, 144, 198, 18);
		contentPane.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(51, 203, 475, 98);
		contentPane.add(textField_3);
		
		JButton button_1 = new JButton("\u7EFC\u5408\u8BC4\u4EF7");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dialog_3 = new Evaluation(sum_decision,real_weight);
					dialog_3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog_3.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(425, 163, 113, 27);
		contentPane.add(button_1);

	}
}
