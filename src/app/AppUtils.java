package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String dateToString (Date date) 
	{
		return dateFormat.format(date);
	}

}
