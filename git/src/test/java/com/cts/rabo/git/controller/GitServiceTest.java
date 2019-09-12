package com.cts.rabo.git.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.cts.rabo.git.exception.GitException;
import com.cts.rabo.git.service.impl.GitServiceImpl;

public class GitServiceTest {

	private GitServiceImpl gitServicImpl;
	
	@Mock
	private Environment env;
	
	@Mock
	private RestTemplate restTemplate;
	
	 @Before
	 public void setUp() {
		 
		 MockitoAnnotations.initMocks(this);
		 gitServicImpl=new GitServiceImpl();
		 env=mock(Environment.class);
		 restTemplate=new RestTemplate();
		 
	 }  
		 
	
	@Test
	public void getRepoUrlTest() throws GitException  {
		
		String repoURL = gitServicImpl.getRepositoryURL("simon3c");
		
		assertThat(repoURL).isNotNull();
	}
	
	@Test
	public void getRepoListTest() {
		
	}
}
