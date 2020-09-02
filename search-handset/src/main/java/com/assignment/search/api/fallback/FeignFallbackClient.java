package com.assignment.search.api.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.search.service.api.client.HandsetLookupClient;

import feign.FeignException;

public class FeignFallbackClient implements HandsetLookupClient {

    Logger logger = LoggerFactory.getLogger(FeignFallbackClient.class);

    private final Throwable cause;

    public FeignFallbackClient(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getHandsets() {
        if (cause instanceof FeignException && ((FeignException) cause)
                .status() == 404) {
            logger.error("Service not Found. 404 error while fetching Handsets: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }
        return new String();
    }}
