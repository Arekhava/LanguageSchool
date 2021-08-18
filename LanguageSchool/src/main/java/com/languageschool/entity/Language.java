package com.languageschool.entity;

import java.io.Serializable;

/**
 * Describes the entity Language
 * 
 * @author N
 */
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long languageId;
	private String languageName;

	/**
	 * Constructs a new Language
	 */
	public Language() {
	}

	/**
	 * Constructs a new Language with the specified language id and language
	 * name
	 * 
	 */
	public Language(Long languageId, String languageName) {
		this.languageId = languageId;
		this.languageName = languageName;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
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
		Language other = (Language) obj;
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [languageId=");
		builder.append(languageId);
		builder.append(", languageName=");
		builder.append(languageName);
		builder.append("]");
		return builder.toString();
	}
}