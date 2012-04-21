package gameOfLife.util;

public class MathUtil {

	public static int makePositiveInt(int i){
		if( i < 0){
			return i * -1; 
		}
		return i;
	}
	
}
