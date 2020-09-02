package com.assignment.search.api.fallback;

import org.springframework.stereotype.Component;

import com.assignment.search.service.api.client.HandsetLookupClient;

import feign.hystrix.FallbackFactory;

@Component
public class FeignFallbackFactory implements FallbackFactory<HandsetLookupClient> {
	
    @Override
    public HandsetLookupClient create(Throwable cause) {
        return new FeignFallbackClient(cause);
    }

}
