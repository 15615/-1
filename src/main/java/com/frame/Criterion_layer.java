package com.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Criterion_layer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public Double[] ww;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Criterion_layer dialog = new Criterion_layer(5);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Criterion_layer(final int n) throws Exception{
		Double[][] matrix=new Double[n][n];
		Double[] column=new Double[n];
		Double[][] matrixColumn= new Double[n][n];
		Double[] line=new Double[n];
        Double[] w=new Double[n];//特征向量
        Double[] bw=new Double[n];
        Double[] lamda = new Double[n]; //防止报错  匿名内部类和局部内部类只能引用外部的fianl变量
        //分别存储    最大特征值、一致性指标、一致性比率
        final double RI[]={0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49,1.52,1.54,1.56,1.58,1.59};
        
        
        for(int i=0;i<n;i++){
            matrix[i][i]=1.0;
        }
		
		setTitle("\u51C6\u5219\u5C42\u5224\u65AD\u77E9\u9635");
		setBounds(100, 100, 450, 300);
		contentPanel.setLayout(new GridLayout(n,n, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel[] labels = new JLabel[n*n];
		JPanel[] panels = new JPanel[n*n];
		final JTextField[] textField = new JTextField[n*n];
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}
			
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   a"+(i/n+1)+(i%n+1) );
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER);}
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );
		{
			
			JPanel buttonPane = new JPanel();
			
				final JLabel lblLamda = new JLabel("lamda=           ");
				buttonPane.add(lblLamda);
			
			
				final JLabel lblNewLabel = new JLabel("CR=              ");
				buttonPane.add(lblNewLabel);
				
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						Double[][] matrix=new Double[n][n];
						Double[] column=new Double[n];
						Double[][] matrixColumn= new Double[n][n];
						Double[] line=new Double[n];
				        Double[] w=new Double[n];//特征向量
				        Double[] bw=new Double[n];
				        Double[] lamda = new Double[n];
						Double sum=0.0;
						Double  sumR=0.0; 
						for( int i=0; i<n*n; i++)
						{	
							try {
								Object tem = engine.eval(textField[i].getText());
								String tem_String = String.valueOf(tem);
								matrix[i/n][i%n]=Double.valueOf(tem_String);
							} catch (ScriptException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						for(int i=0;i<n;i++)
							for(int j=0;j<n;j++)
							System.out.println(matrix[i][j]);
						
						//列求和
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                if(column[j]!=null){
				                    column[j]=column[j]+matrix[i][j];
				                }else{
				                    column[j]=matrix[i][j];
				                }
				            }
				            
				        }
						//矩阵列归一化
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                matrixColumn[i][j]=matrix[i][j]/column[j];
				            }
				        }
				        
				      //获得行数组（行求和）
				        for(int i=0;i<n;i++){
				            for(int j=0;j<n;j++){
				                if(line[i]!=null){
				                    line[i]=line[i]+matrixColumn[i][j];
				                }else{
				                    line[i]=matrixColumn[i][j];
				                }
				            }
				        }
				        
				        //行归一化获得特征向量

				        
				        for(int i=0;i<n;i++){
				            sum=sum+line[i];
				        }
				        for(int i=0;i<n;i++){
				            w[i]=line[i]/sum;                    //特征向量
				        }

				        for(int i=0;i<n;i++){
				            for(int j=0;j<n;j++){
				                if(bw[i]!=null){
				                    bw[i]=bw[i]+matrix[i][j]*w[j];
				                }else{
				                    bw[i]=matrix[i][j]*w[j];
				                }    
				            }
				        }
				        
				                               //最大特征跟R
				        for(int i=0;i<n;i++){
				            sumR = sumR+bw[i]/(n*w[i]);
				        }
				        lamda[0] = sumR; //即为最大特征值
				        Double ci=(sumR-n)/(n-1);                //矩阵一致性指标
				        System.out.println("计算出的矩阵一致性指标CI="+ci+"\n");
				        Double cr=ci/RI[n-1];                        //随机一致性比率 1.24为6阶矩阵的平均一致性指标
				        lamda[1]=ci;
				        lamda[2]=cr;
				        if(cr>=0.1){
				            System.out.println("权重设置不合理");
				        }else{
				            //输出特征向量
				            for(int i=0;i<n;i++){
				                System.out.println("特征"+(i+1)+"的权重："+w[i]);
				            }
				        }
				        BigDecimal b = new BigDecimal(sumR);  
						double sumR_2 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						BigDecimal b_1 = new BigDecimal(cr);  
						double cr_2 = b_1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				        lblLamda.setText("lamda="+sumR_2+"     ");
				        lblNewLabel.setText("CR="+cr_2+"        ");

				        ww = w;
						for(int i=0;i<ww.length;i++)
							System.out.println(ww[i]);
					}
				});
				
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
	public Double[] jisuan() {
		
		return ww;
		
	}
}
