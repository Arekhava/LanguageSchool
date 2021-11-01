package Exceptions;

import java.text.NumberFormat;

import java.text.ParseException;
import java.util.Locale;

public class LearnMainBorder {
	public static void main(String[] args) {
		double value = Double.parseDouble("98374.98");
		System.out.println(value);
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		try {
		value = (double) format.parse("98,374.98");
		System.out.println(value);
		}catch (ParseException e) {
			e.printStackTrace();
			
		}
	}
}
