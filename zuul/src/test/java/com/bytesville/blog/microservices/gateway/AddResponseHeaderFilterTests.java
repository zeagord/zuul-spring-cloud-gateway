package com.bytesville.blog.microservices.gateway;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class AddResponseHeaderFilterTests {

	@LocalServerPort
	int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();


	private AddResponseHeaderFilter filter;

	@Before
	public void setup(){
		this.filter = new AddResponseHeaderFilter();
	}

	@Test
	public void testFilterType(){
		assertEquals(filter.filterType(), "post");
	}

	@Test
	public void testShouldFilter(){
		assertTrue(filter.shouldFilter());
	}

	@Test
	public void testFilterOrder(){
		assertEquals(filter.filterOrder(), 999);
	}

	@Test
	public void testRun(){
		// Write the test run for the headers later
	}
}
