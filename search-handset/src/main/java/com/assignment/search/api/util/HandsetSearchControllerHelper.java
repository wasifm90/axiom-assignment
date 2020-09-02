package com.assignment.search.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.assignment.search.api.response.HandsetDetails;

@Component
public class HandsetSearchControllerHelper {

	
	public List<Predicate<HandsetDetails>> processParams(Map<String, String> searchParams) {

		List<Predicate<HandsetDetails>> handsetSearchPredicates = new ArrayList<Predicate<HandsetDetails>>();
		searchParams.keySet().forEach(key ->{
			handsetSearchPredicates.add(selectPredicate(key, searchParams.get(key)));
		});
		return handsetSearchPredicates;
	}
	
	private Predicate<HandsetDetails> selectPredicate(String key, String value) {
		
		switch (key) {
		
		case "brand" : 
			return  search -> value!= null ? search.getBrand().equalsIgnoreCase(value) : true;
		case "phone" :
			return  search -> !StringUtils.isEmpty(value) ? search.getPhone().equalsIgnoreCase(value) : true;
		case "announceDate" :
			return  search -> !StringUtils.isEmpty(value) ? search.getRelease().getAnnounceDate().equalsIgnoreCase(value) : true;
		case "priceEur" :
			return  search -> !StringUtils.isEmpty(value) ? search.getRelease().getPriceEur().equals(Integer.valueOf(value)) : true;
		case "sim" :
			return  search -> !StringUtils.isEmpty(value) ? search.getSim().toLowerCase().contains(value.toLowerCase()) : true;
		case "resolution" :
			return  search -> !StringUtils.isEmpty(value) ? search.getResolution().equalsIgnoreCase(value) : true;
		case "audioJack" :
			return  search -> !StringUtils.isEmpty(value) ? search.getHardware().getAudioJack().equalsIgnoreCase(value) : true;
		case "gps" :
			return  search -> !StringUtils.isEmpty(value) ? search.getHardware().getGps().equalsIgnoreCase(value) : true;
		case "battery" :
			return  search -> !StringUtils.isEmpty(value) ? search.getHardware().getBattery().equalsIgnoreCase(value) : true;
		default : 
			return null;
			
		}
		
	
}

}
