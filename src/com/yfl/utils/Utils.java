package com.yfl.utils;

import java.awt.Font;

public class Utils {

	//定义字体
	public static Font f1=new Font("宋体",Font.PLAIN,24);
	public static Font f2=new Font("宋体",Font.PLAIN,14);
	
	//过滤空字符串
	public static boolean isEmpty(String str){
		
		boolean b=false;
		
		if(str!=null&&str.length()!=0){
			b=true;
		}
		
		return b;
	}
}
