package com.arekhava.languageschool.entity;


/**
 * Describes the entity Language
 * 
 * @author N
 */
public class Language extends Entity   {
	private Long languageId;
	private String languageName;
	private String imageName;
	

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

	

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
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
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		return false;
		}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [languageId=");
		builder.append(languageId);
		builder.append(", languageName=");
		builder.append(languageName);
		builder.append(", imageName=");
		builder.append(imageName);
		builder.append("]");
		return builder.toString();
	}
}