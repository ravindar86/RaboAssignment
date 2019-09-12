package com.cts.rabo.git.model;

public class GitResponseVO {

	private String name;
	private String description;
	private String commits_url;
	private String url;
	
	private String state;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the commits_url
	 */
	public String getCommits_url() {
		return commits_url;
	}
	/**
	 * @param commits_url the commits_url to set
	 */
	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}
	
	
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GitResponseVO [name=" + name + ", description=" + description + ", commits_url=" + commits_url + "]";
	}
	
	
}
