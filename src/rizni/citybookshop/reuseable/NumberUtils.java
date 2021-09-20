
package rizni.citybookshop.reuseable;

public class NumberUtils {
	
	public static boolean isNumeric(String s) {
	    try { 
	    	if (s.contains("."))
	    		Double.parseDouble(s); 
	    	else
	    		Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
