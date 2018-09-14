package cn.zcbdqn.commoninventory.utils;

public class StringUtils {
	
	public static Integer str2Integer(String str){
		return Integer.valueOf(str);
	}
	
	public static int str2Int(String str,int defaultVal){
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultVal;
		}
	}
	
	

}
