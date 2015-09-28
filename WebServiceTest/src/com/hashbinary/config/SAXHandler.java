package com.hashbinary.config;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	Map<String, Model> dataMap = new HashMap<String, Model>();
	String nodeValue = "";
	Model m = null;// new Model();

	public void startDocument() throws SAXException {
		System.out.println("document start");
	}

	public void endDocument() throws SAXException {
		System.out.println("document end");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("start element    : " + qName);
		if (qName == "service") {
			m = new Model();
		}

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("end element      : " + qName);
		if (qName == "name") {
			m.setServiceName(nodeValue);
		}
		if (qName == "serial") {
			m.setSerialNo(Integer.parseInt(nodeValue));
		}
		if (qName == "type") {
			System.out.println("------>"+nodeValue);
			if (nodeValue.equals("GET")) {
				m.setRequestType(RequestType.GET);
			} else {
				m.setRequestType(RequestType.POST);
			}
		}
		if (qName == "url") {
			m.setServiceURL(nodeValue);
		}
		if (qName == "body") {
			m.setServiceBody(nodeValue);
		}
		if (qName == "result") {
			m.setServiceResult(nodeValue);
		}
		if (qName == "service") {
			dataMap.put(((int)(Math.random() * 11 + 10)) + "", m);	
		}
		// TODO: pending for headers.
		
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		System.out.println("start characters : "
				+ new String(ch, start, length));
		nodeValue = new String(ch, start, length);
	}

	public void ignorableWhitespace(char ch[], int start, int length)
			throws SAXException {
	}

	public  Map<String, Model> getDataMap() {
		return dataMap;
	}
}
