package com.frame;

public class TE {

	public static void main(String[] args) {
		String a = "1 3 5";
        String str = "3 1 4 3 3";//ע��������������һ��|�ָ���
        String[] strArr = str.split(" ");//���ｫ�ڶ�����������Ϊ-1
        System.out.println(strArr.length); //�������4
        for (int i = 0; i < strArr.length; ++i){
        	System.out.println(strArr[i]);//�������a b c ���ַ���

	}

}
}