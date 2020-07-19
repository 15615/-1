package com.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Decision_layer extends JDialog {

	static String[] strArr;
	private final JPanel contentPanel = new JPanel();
	public static zhongjian[] zj=new zhongjian[20];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			String a = "1 3 5";
			Decision_layer dialog = new Decision_layer(5,a);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Decision_layer(int n,String m) {
		strArr= m.split(" ");//这里将第二个参数设置为-1
//		final int[] array = new int[20];
		
		setTitle("\u51B3\u7B56\u5C42\u5224\u65AD\u77E9\u9635");
		setBounds(100, 100, 450, 300);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel shagnceng = new JPanel();
		shagnceng.setLayout(new FlowLayout());
		contentPanel.setLayout(new BorderLayout());
		{
			JLabel lblNewLabel = new JLabel("\u51B3\u7B56\u5C42\u76F8\u5BF9\u4E8E\u51C6\u5219\u5C42\u77E9\u9635\u8F93\u5165");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 21));
			lblNewLabel.setBounds(76, 13, 285, 40);
			contentPanel.add(lblNewLabel,BorderLayout.NORTH);
		}
		contentPanel.add(shagnceng,BorderLayout.CENTER);
		
		JButton[] buttons = new JButton[n];
		for( int i=0; i<buttons.length; i++)
			buttons[i] = new JButton("相对于第"+(i+1)+"准则层" );
		for( int i=0; i<buttons.length; i++)
			shagnceng.add( buttons[i] );
		
		//解决匿名内部类访问外部的变量
		for( int i=0; i<buttons.length; i++) {
			buttons[i].addActionListener(new ActionListener() {
			private int ii;//匿名类内部的参数
			private int a_in;//匿名类内部的参数
			public void actionPerformed(ActionEvent e) {
				try {
					zj[ii] = new zhongjian(Integer.parseInt(strArr[ii]),ii);
					zj[ii].setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					zj[ii].setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			public ActionListener accept(int str) {
                this.ii = str;
                return this;
            }
			
			}.accept(i));
		}
//		buttons[1].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				zhongjian zj_1;
//				try {
//					zj_1 = new zhongjian(3);
//					zj_1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					zj_1.setVisible(true);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				}
//			});
//		buttons[2].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				zhongjian zj_1;
//				try {
//					zj_1 = new zhongjian(3);
//					zj_1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					zj_1.setVisible(true);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				}
//			});
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						for(int i=0;i<n;i++)
//							for(int j = 0;j<m;j++)
//							System.out.println("第"+(i+1)+"层"+zj[i].ww[j]);
					}
				});
				{
					JButton btnNewButton = new JButton("New button");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});

					buttonPane.add(btnNewButton);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
