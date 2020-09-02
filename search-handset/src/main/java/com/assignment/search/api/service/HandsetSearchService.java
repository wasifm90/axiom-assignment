package com.assignment.search.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.search.api.response.HandsetDetails;
import com.assignment.search.service.api.client.HandsetLookupClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HandsetSearchService {

	@Autowired
	HandsetLookupClient lookupClient;
	
//	public HandsetSearchService() {
//	}
//	
	public HandsetSearchService(HandsetLookupClient handsetLookupClient) {
		lookupClient = handsetLookupClient;
	}


	public Optional<List<HandsetDetails>> fetchHandsets(List<Predicate<HandsetDetails>> searchPredicates) {

		ObjectMapper mapper = new ObjectMapper(); 
		
			try {
				return Optional.ofNullable(generateSearchResults(Arrays
													    .asList(mapper
															    .readValue(lookupClient
																	   	   .getHandsets(), HandsetDetails[].class)), chainPrediactes(searchPredicates)));
			} catch (Exception e) {
				// log the exception here
			}
		
		return Optional.empty();
	}
	

	private Predicate<HandsetDetails> chainPrediactes(List<Predicate<HandsetDetails>> searchPredicates) {

		Predicate<HandsetDetails> predicateChain = searchPredicates.remove(0);
		
		for (Predicate<HandsetDetails> predicate : searchPredicates) {
			
			predicateChain = predicateChain.and(predicate);
		}
		return predicateChain;
	}


	private List<HandsetDetails> generateSearchResults(List<HandsetDetails> handsetDetails, Predicate<HandsetDetails> searchPredicates) {
		
		return handsetDetails.stream()
					  		 .filter(searchPredicates)
					  		 .collect(Collectors.toList());
		
		
		
		
	}
	
}
