package app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String dateToString (Date date)
	{
		return dateFormat.format(date);
	}
	
	public static long StringDataToLong(String string)
	{
		try {
			return dateFormat.parse(string).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}

}
