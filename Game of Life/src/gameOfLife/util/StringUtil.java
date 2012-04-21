package gameOfLife.util;

public class StringUtil {

	public static String stripSpaceAndNewLineCharacters(String s){
		return s.replaceAll(" ", "").replaceAll("\n", "");
	}
	
}
