package minocha.foodistan.inventory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class test {
	public static void main(String arg [])
	{
		Calendar calendar = new GregorianCalendar();
		  String am_pm;
		  long j=0;
		  while(j<100000){
			  j++;
		  int hour = calendar.get(Calendar.HOUR);
		  int minute = calendar.get(Calendar.MINUTE);
		  int second = calendar.get(Calendar.SECOND);
		  if(calendar.get(Calendar.AM_PM) == 0)
		  am_pm = "AM";
		  else
		  am_pm = "PM";
		  System.out.println("Current Time : " + hour + ":" 
		+ minute + ":" + second + " " + am_pm);
			  long i = System.currentTimeMillis();
			  System.out.println(i);
			  
			  }
		
	}
	}