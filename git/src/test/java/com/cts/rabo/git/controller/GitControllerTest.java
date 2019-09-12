package com.cts.rabo.git.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.rabo.git.service.GitService;

public class GitControllerTest {

	@InjectMocks
	private GitController gitController;
	
	@InjectMocks
	private GitService gitService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(gitService).build();
	}
	
	@Test
	public void testGitRepoURL() throws Exception {
		this.mockMvc.perform(get("/users/simon3c")).andExpect(status().isOk());
	}
}
