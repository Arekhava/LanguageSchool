package com.arekhava.languageschool.util;

import java.math.BigDecimal;
import java.util.List;

import com.arekhava.languageschool.entity.Course;

/**
 * The utility is responsible for the calculation of prices
 * 
 * @author N
 */
public final class PriceCalculator {

	private PriceCalculator() {
	}

	/**
	 * Calculates the total cost of courses
	 * 
	 * @param courses {@link List} of {@link Course} 
	 * @return {@link BigDecimal} the total cost of courses
	 */
	public static BigDecimal calculateTotalCost(List <Course> courses) {
		BigDecimal totalCost = BigDecimal.ZERO;	
		if (courses == null ){
			return totalCost;
	}
	for (Course course : courses)
	{
		BigDecimal price = course.getPrice();
		totalCost = totalCost.add(price);
	}
	return totalCost;
	}
}

