package com.languageschool.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;


/**
 * Describes the entity Course
 * 
 * @author N
 */
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long courseId;
	private Long languageId;
	private String courseName;
	private String teacherName;
	private Date nextStart;
	private BigDecimal price;
	

	/**
	 * Constructs a new Course
	 */
	public Course() {
	}



	public Long getCourseId() {
		return courseId;
	}



	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}



	public Long getLanguageId() {
		return languageId;
	}



	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public String getTeacherName() {
		return teacherName;
	}



	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}



	public Date getNextStart() {
		return nextStart;
	}



	public void setNextStart(Date nextStart) {
		this.nextStart = nextStart;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
		result = prime * result + ((nextStart == null) ? 0 : nextStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (courseId == null) {
			if (other.languageId != null)
				return false;
		} else if (!courseId.equals(other.languageId))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		if (nextStart == null) {
			if (other.nextStart != null)
				return false;
		} else if (!nextStart.equals(other.nextStart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [courseId=");
		builder.append(courseId);
		builder.append(", categoryId=");
		builder.append(languageId);
		builder.append(", courseName=");
		builder.append(courseName);
		builder.append(", teacherName=");
		builder.append(teacherName);
		builder.append(", nextStart=");
		builder.append(nextStart);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
}