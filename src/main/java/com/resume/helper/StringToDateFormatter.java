package com.resume.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateFormatter {

	public Date dateFormatter(String inputDate){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY");
		try {
			
			Date date = formatter.parse(inputDate);
			return date;
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
