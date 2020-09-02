package com.assignment.search.api.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandsetReleaseDetails {
	
	String announceDate;
	Integer priceEur;

}
