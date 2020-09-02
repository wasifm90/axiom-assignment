package com.assignment.search.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.search.api.response.HandsetDetails;
import com.assignment.search.api.service.HandsetSearchService;
import com.assignment.search.api.util.HandsetSearchControllerHelper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/handset")
public class HandsetSearchController {
	
	@Autowired
	HandsetSearchService searchService;
	
	@Autowired
	HandsetSearchControllerHelper searchControllerHelper;

	@GetMapping
	@ApiOperation(value = "Search handset by criteria", response = HandsetDetails.class)
	public ResponseEntity<Optional<List<HandsetDetails>>> fetchHandsets(@RequestParam Optional<String> brand,
									    @RequestParam Optional<String> phone,
									    @RequestParam Optional<String> announceDate,
									    @RequestParam Optional<String> priceEur,
									    @RequestParam Optional<String> sim,
									    @RequestParam Optional<String> resolution,
								            @RequestParam Optional<String> audioJack,
									    @RequestParam Optional<String> gps,
									    @RequestParam Optional<String> battery){
		
		Map<String, String> searchParams = new HashMap<>();

		if(brand.isPresent())
			searchParams.put("brand", brand.get());
		if(phone.isPresent())
			searchParams.put("phone", phone.get());
		if(announceDate.isPresent())
			searchParams.put("announceDate", announceDate.get());
		if(priceEur.isPresent())
			searchParams.put("priceEur", priceEur.get());
		if(sim.isPresent())
			searchParams.put("sim", sim.get());
		if(resolution.isPresent())
			searchParams.put("resolution", resolution.get());
		if(audioJack.isPresent())
			searchParams.put("audioJack", audioJack.get());
		if(gps.isPresent())
			searchParams.put("gps", audioJack.get());
		if(battery.isPresent())
			searchParams.put("battery", audioJack.get());
		
		return ResponseEntity.ok(searchService.fetchHandsets(searchControllerHelper.processParams(searchParams)));
	}
	
}
