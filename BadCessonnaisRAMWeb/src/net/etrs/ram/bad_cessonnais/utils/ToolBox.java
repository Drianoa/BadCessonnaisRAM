package net.etrs.ram.bad_cessonnais.utils;

public class ToolBox {

	
	public static boolean isIntegerException(String val) {
	    try {
	        Integer.valueOf(val);
	        return !val.startsWith("-");
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
}
