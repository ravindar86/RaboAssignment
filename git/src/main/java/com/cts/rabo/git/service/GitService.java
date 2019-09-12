package com.cts.rabo.git.service;

import java.util.List;

import com.cts.rabo.git.exception.GitException;
import com.cts.rabo.git.model.GitResponseVO;

public interface GitService {

	
	public String getRepositoryURL(String userId) throws GitException ;
	
	public List<GitResponseVO> getRepositoryList(String userId) throws GitException;
	
	public int getOpenPRCount(String pullURL);
	
	public int getClosedPRCount(String pullURL);
}
