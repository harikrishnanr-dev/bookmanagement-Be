package com.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genre {
	@JsonProperty("Fiction")
	FICTION,
	@JsonProperty("Non-Fiction")
	NON_FICTION,
	@JsonProperty("Mystery")
	MYSTERY,
	@JsonProperty("Fantasy")
	FANTASY,
	@JsonProperty("Romance")
	ROMANCE,
	@JsonProperty("Sci-Fi")
	SCI_FI,
	@JsonProperty("Others")
	OTHERS;


}
