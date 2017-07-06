package com.resume.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.springframework.format.annotation.DateTimeFormat;

public class StringToDateFormatter {

	public LocalDateTime dateFormatter(String inputDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MM/dd/yyyy");
			LocalDateTime date = (LocalDateTime) formatter.parse(inputDate);
			return date;
	}
		
}

