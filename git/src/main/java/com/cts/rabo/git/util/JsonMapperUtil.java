package com.cts.rabo.git.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.rabo.git.controller.GitController;
import com.cts.rabo.git.model.GitResponseVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperUtil {

	private static final Logger logger = LoggerFactory.getLogger(GitController.class);

	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertJSONToModel(String json) {

		Map<String, Object> map = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(json, Map.class);
			// System.out.println(map);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * method to convert the json string into list of objects
	 * @param jsonStr
	 */
	public static List<GitResponseVO> getRepositoryDetails(String jsonStr) {

		List<GitResponseVO> gitResponseList = null;

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			// To ignore the properties that are not to be mapped
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			// Convert the json string to the list of objects
			gitResponseList = Arrays.asList(objectMapper.readValue(jsonStr, GitResponseVO[].class));

			TypeReference<java.util.List<GitResponseVO>> tRef = new TypeReference<java.util.List<GitResponseVO>>() {
			};

			// read the value from json and map it to the objects
			gitResponseList = objectMapper.readValue(jsonStr, tRef);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gitResponseList;
	}

	
}
