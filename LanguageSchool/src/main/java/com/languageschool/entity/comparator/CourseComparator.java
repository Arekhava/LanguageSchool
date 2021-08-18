package com.languageschool.entity.comparator;

import java.util.Comparator;

import com.languageschool.entity.Course;



/**
 * Describes all course comparators
 * 
 */
public enum CourseComparator {

	/**
	 * A comparison function, which sets course in subscription of increasing price
	 */
	INCREASE_PRICE((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())),

	/**
	 * A comparison function, which sets courses in subscription of decreasing price
	 */
	DECREASE_PRICE((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));

	private Comparator<Course> comporator;

	/**
	 * Constructs a new CourseComporator with the specified course comparison
	 * functions
	 * 
	 * @param comporator {@link Comparator} of {@link Course}} comparison functions
	 */
	CourseComparator(Comparator<Course> comporator) {
		this.comporator = comporator;
	}

	/**
	 * Gets course comparator
	 * 
	 * @return {@link Comparator} of {@link Course} comparison functions
	 */
	public Comparator<Course> getComporator() {
		return comporator;
	}
}
