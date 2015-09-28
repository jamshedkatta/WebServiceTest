package com.aurionpro.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class ConfigurationLoader {
	public static Map<String, Model> loadConfiguration(InputStream is) {
		Map<String, Model> dataMap = new HashMap<String, Model>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new SAXHandler();
			saxParser.parse(is, handler);
			dataMap = ((SAXHandler) handler).getDataMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
}
