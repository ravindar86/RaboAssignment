package com.cts.rabo.git.util;

import org.junit.Before;
import org.junit.Test;

public class JsonMapperUtilTest {

	String repoURL = "\"repos_url\": \"https://api.github.com/users/simon3c/repos\"";
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void repoURLTest() {
		
		JsonMapperUtil.convertJSONToModel(repoURL);
	}
}
