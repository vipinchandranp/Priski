package com.util;

import code4goal.antony.resumeparser.ResumeParserProgram;

public class Parser {

	public static void main(String[] args) {
		
		ResumeParserProgram parser = new ResumeParserProgram();
		String[] str = new String[2];
		str[0] = "/home/vipin/Downloads/VipinChandranP.docx";
		str[1] = "/home/vipin/Downloads/VipinChandranP.json";
		parser.main(str);
	}
}
