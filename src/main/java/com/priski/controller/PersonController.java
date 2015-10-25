package com.priski.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.priski.model.Person;
import com.priski.service.PersonService;

import code4goal.antony.resumeparser.ResumeParserProgram;
import gate.util.GateException;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/")
	public String listPeople(Map<String, Object> map) {

		map.put("person", new Person());
		map.put("peopleList", personService.listPeople());

		return "people";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person person, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile image) {
		JSONObject parsedJson  = null;
		try {
			InputStream stream = image.getInputStream();

			ContentHandler handler = new ToXMLContentHandler();
			// ContentHandler handler = new BodyContentHandler();
			// ContentHandler handler = new BodyContentHandler(
			// new ToXMLContentHandler());
			// InputStream stream = new FileInputStream(file);
			AutoDetectParser parser = new AutoDetectParser();
			Metadata metadata = new Metadata();
			
			try {
				try {
					parser.parse(stream, handler, metadata);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TikaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileWriter htmlFileWriter = new FileWriter(System.getProperty("user.dir")+"\temp.html");
				htmlFileWriter.write(handler.toString());
				htmlFileWriter.flush();
				htmlFileWriter.close();
				File htmlFile = new File(System.getProperty("user.dir")+"\temp.html");

				ResumeParserProgram rp = new ResumeParserProgram();
				
				try {
					 parsedJson = rp.loadGateAndAnnie(htmlFile);
				} catch (GateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} finally {
				stream.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parsedJson);
		personService.addPerson(person);


		return "redirect:/people/";
	}

	@RequestMapping("/delete/{personId}")
	public String deletePerson(@PathVariable("personId") String personId) {

		personService.removePerson(personId);

		return "redirect:/people/";
	}
}
