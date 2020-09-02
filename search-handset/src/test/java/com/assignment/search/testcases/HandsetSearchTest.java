package com.assignment.search.testcases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.search.api.response.HandsetDetails;
import com.assignment.search.api.service.HandsetSearchService;
import com.assignment.search.service.api.client.HandsetLookupClient;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HandsetSearchTest {

	private HandsetSearchService searchService;

	private List<Predicate<HandsetDetails>> predicateList = new ArrayList<Predicate<HandsetDetails>>();

	private HandsetLookupClient lookupClient;
	
	@Before
	public void setUp() throws IOException {
		
		lookupClient = mock(HandsetLookupClient.class);
		Mockito.when(lookupClient.getHandsets())
	   	   .thenReturn(new BufferedReader(
				      new InputStreamReader(this.getClass().getResourceAsStream("/handsetData.json"), StandardCharsets.UTF_8))
			        .lines()
			        .collect(Collectors.joining("\n")));

		searchService = new HandsetSearchService(lookupClient);
		
	}


	@Test
	public void testDateAndPrice() {

		Predicate<HandsetDetails> predicate1 = search -> search.getRelease().getAnnounceDate().equalsIgnoreCase("1999");
		Predicate<HandsetDetails> predicate2 = search -> search.getRelease().getPriceEur().equals(200);
		predicateList.add(predicate1);
		predicateList.add(predicate2);
		
		Optional<List<HandsetDetails>> handsets = searchService.fetchHandsets(predicateList);
		Assert.assertTrue(handsets.isPresent());
		assertEquals(handsets.get().size(), 2);
	}

	@Test
	public void testPrice() {
		Predicate<HandsetDetails> predicate2 = search -> search.getRelease().getPriceEur().equals(200);
		predicateList.add(predicate2);

		Optional<List<HandsetDetails>> handsets = searchService.fetchHandsets(predicateList);
		Assert.assertTrue(handsets.isPresent());
		assertEquals(handsets.get().size(), 10);
	}

	@Test
	public void testSimType() {
		Predicate<HandsetDetails> predicate1 = search -> search.getSim().contains("eSIM");
		predicateList.add(predicate1);
		Optional<List<HandsetDetails>> handsets = searchService.fetchHandsets(predicateList);
		Assert.assertTrue(handsets.isPresent());
		assertEquals(handsets.get().size(), 18);
	}


}
