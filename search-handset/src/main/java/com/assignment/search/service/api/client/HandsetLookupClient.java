package com.assignment.search.service.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.search.api.fallback.FeignFallbackFactory;

@FeignClient(name = "lookup", url = "${handset.search.url}", fallbackFactory = FeignFallbackFactory.class)
public interface HandsetLookupClient {

	@GetMapping(value = "/handsets/list")
	String getHandsets();
}
