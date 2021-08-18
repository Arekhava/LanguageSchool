package com.languageschool.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.languageschool.entity.Course;

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
	public static BigDecimal calculateTotalCost(List<Course> courses) {
		BigDecimal totalCost = BigDecimal.ZERO;
		if (courses == null) {
			return totalCost;
		/*}
		for (Map.Entry<Product, Integer> productAndQuantity : products.entrySet()) {
			BigDecimal price = productAndQuantity.getKey().getPrice();
			BigDecimal quantity = new BigDecimal(productAndQuantity.getValue());
			BigDecimal productCost = price.multiply(quantity);
			totalCost = totalCost.add(productCost);*/
		}
		
		return totalCost;	
	}
}
