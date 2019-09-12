package com.cts.rabo.git.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cts.rabo.git.constants.GitConstants;
import com.cts.rabo.git.exception.GitException;
import com.cts.rabo.git.model.GitResponseVO;
import com.cts.rabo.git.service.GitService;
import com.cts.rabo.git.util.JsonMapperUtil;

@Service
public class GitServiceImpl implements GitService {

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(GitService.class);
	
	/**
	 * Method to get the repository URL by providing the user id
	 */
	@Override
	public String getRepositoryURL(String userId) throws GitException {

		logger.info("getRepoURL method Begins");
		
		String repoURL = null;
		String uri = env.getProperty("api.github.users.uri")+userId;

		logger.info("GIT Request URL: "+uri);
		
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

			System.out.println(result);
			
		    Map<String,Object> object = JsonMapperUtil.convertJSONToModel(result.getBody());
		    
		    repoURL=(String)object.get(env.getProperty("github.user.repos_url"));

			logger.debug("RepoURL -> "+repoURL);
			
		} catch (HttpClientErrorException e) {
		    System.out.println(e.getStatusCode());
		    System.out.println(e.getResponseBodyAsString());
		    //repoURL = "Not Found";
		    repoURL = GitConstants.NOT_FOUND;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GitException(e.getMessage());
		}

		logger.info("getRepoURL method Ends");

		if (null != repoURL) {
			return repoURL;
		}

		return GitConstants.NOT_FOUND;

	}

	/**
	 * Method to get the repository list based on the URL retrieved
	 */
	@Override
	public List<GitResponseVO> getRepositoryList(String repoUrl) throws GitException {
		
		logger.info("getRepositoryList method Begins");
		//String repoUrl=null;
		List<GitResponseVO> gitResponseList =null;
		
		logger.info("Repo URL ->>> "+repoUrl);
		try {
			// Fetches the repository list post fetching the repo URL
			if(!repoUrl.equalsIgnoreCase(GitConstants.NOT_FOUND)) {
				
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

				ResponseEntity<String> result = restTemplate.exchange(repoUrl, HttpMethod.GET, entity, String.class);
				
				gitResponseList = JsonMapperUtil.getRepositoryDetails(result.getBody());

				logger.info("Repo List -> "+gitResponseList.size());

				for(GitResponseVO res: gitResponseList) {
					System.out.println(res.toString());
				}
				
			}else {
				
				logger.info("User Id not Found");
			}
						

		}catch (Exception e) {
			throw new GitException(e.getMessage());
		}
		
		
		return gitResponseList;
	}

	@Override
	public int getOpenPRCount(String pullURL) {

		// TODO Need to write the logic to get the number of open PR in a repository
		
		return 0;
	}

	@Override
	public int getClosedPRCount(String pullURL) {
		
		// TODO Need to implement logic to get the closed PR count for current date
		return 0;
	}
	
	

}
