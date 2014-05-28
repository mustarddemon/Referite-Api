package com.referite.models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class FavoriteModel {

	// _id is a mongo thing that we don't care about but it needs to be here so
	// serialization works
	@JsonIgnore
	public Object _id;
	private String name;
	private String id;
	private String category;
	private String customDescription;
	private String starRating;
	private LinkObject[] links;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCustomDescription() {
		return customDescription;
	}

	public void setCustomDescription(String customDescription) {
		this.customDescription = customDescription;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public LinkObject[] getLinks() {
		return links;
	}

	public void setLinks(LinkObject[] links) {
		this.links = links;
	}

	@JsonIgnore
	public boolean isContractValid() {
		boolean contractIsValid = true;

		String[] requiredFields = new String[] { getName(), getCategory() };

		for (String field : requiredFields) {
			if (field == null || "".equals(field)) {
				contractIsValid = false;
			}
		}

		return contractIsValid;

	}

}
