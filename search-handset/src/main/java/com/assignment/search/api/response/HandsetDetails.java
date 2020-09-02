package com.assignment.search.api.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandsetDetails {

	Integer id;
	String brand;
	String phone;
	String picture;
	HandsetReleaseDetails release;
	String sim;
	String resolution;
	HandsetHardwareDetails hardware;
	
}
