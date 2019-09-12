package com.cts.rabo.git.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Git;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.rabo.git.constants.GitConstants;
import com.cts.rabo.git.exception.GitException;
import com.cts.rabo.git.model.GitResponseVO;
import com.cts.rabo.git.service.GitService;

@Controller
public class GitController {

	@Autowired
	private GitService gitService;
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(GitController.class);

	/**
	 * Method to get the repository list based on the userId
	 * @param userId
	 * @return
	 * @throws GitException
	 */
	@RequestMapping(value="/searchUser", method=RequestMethod.GET)
	public ModelAndView getRepoList(@RequestParam String userId) throws GitException {
		
		logger.info(this.getClass()+" getRepoURL method begins");
		
		List<GitResponseVO> responseList = null;
		
		ModelAndView mv = new ModelAndView();
		String repoUrl = null;
		
		// Step1: To get the repository URL by providing the user id
		repoUrl = gitService.getRepositoryURL(userId);
		
		logger.info("Response from service "+repoUrl);
		
		// Step 2: If Repo URL is not returned, then the user id not exists
		if(repoUrl.equals(GitConstants.NOT_FOUND)) {
			
			mv.addObject("message", env.getProperty("git.welcome.msg"));
			mv.addObject("err_message",env.getProperty("git.user.not.found"));
			mv.setViewName("index");
		}else {
			
			// Step 3: Based on the returned repo url, fetch the repository list
			responseList = gitService.getRepositoryList(repoUrl);
			
			logger.info("Repo List in Controller -> "+responseList.size());

			for(GitResponseVO res: responseList) {
				System.out.println(res.toString());
			}
			
			// Step 4: Populate the response to the view object
			mv.addObject("repoList",responseList);
			mv.setViewName("repoList");
		}
		
		return mv;
	}
	
	/**
	 * Home Screen configuration
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
    public String index(Map<String, Object> model) {
		model.put("message", env.getProperty("git.welcome.msg"));
		model.put("err_message", "");
        return "index";
    }
	
	
}
