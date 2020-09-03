package com.rbc.databanqbackend.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;

public class XmlUtil {

	public static Document parseXmlStringToDoc(String input) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(input)));
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public static void parseGetBucketListRes(String input) throws Exception {
		Document doc = parseXmlStringToDoc(input);
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
		NodeList nList = doc.getElementsByTagName("Bucket");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			Element eElement = (Element) nNode;
			System.out.println("Name: " + eElement.getElementsByTagName("Name").item(0).getTextContent());
		}
			 
	}
}
