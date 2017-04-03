package com.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface StaticService {
	Map<Integer, Integer> getHistory(Integer userId) throws JsonParseException, JsonMappingException, IOException;
	Map<String, Integer> hotSku() throws JsonParseException, JsonMappingException, IOException;
	Map<Integer, Integer> hotCatalog() throws JsonParseException, JsonMappingException, IOException;;
}
