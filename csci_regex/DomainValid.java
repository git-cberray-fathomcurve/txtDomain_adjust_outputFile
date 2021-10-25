package csci_regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DomainValid {

//Java program to validate domain name.
//using regular expression.


	// Function to validate domain name.
	public static String domainList(String filename) throws UnsupportedEncodingException, IOException	{
		
		//Read Text File into String
		//  this pathname assumes the file is added to the IDE in the same path as this class
		String pathname = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "csci_regex" + File.separator+filename;
		System.out.println(pathname);
		Path path = Paths.get(pathname);
		String s = new String(Files.readAllBytes(path),"UTF-8");
		String outputString="WhiteList Domains \n";
		// Regex to check valid domain name.
		String regex = "^((?!-)[A-Za-z0-9-]"
					+ "{1,63}(?<!-)\\.)"
					+ "+[A-Za-z]{2,6}";
		
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		Matcher m;
		
		//Read text into lines
		List<String> lines = Files.readAllLines(path);
		for (String sl:lines ) {
			
			m=p.matcher(sl);
			if(m.matches()) {
				//it is a valid domain only, so add the *@ to the front of it
				outputString=outputString+"*@"+sl+"\n";
			}else {
				//this is one we'll ignore since it is already a valid user @ domain, or it is an IP address
				outputString=outputString+sl+"\n";
			}
		}
		return outputString;

	}
	
	public static void postList(String fileContent) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("C:\\Users\\19209\\eclipse\\java-2021-03\\PracticeJava\\src\\csci_regex\\outputGoesHere.txt");
		out.println(fileContent);
		out.close();
		
	}

	// Driver Code
	public static void main(String args[]) throws IOException, SAXException, ParserConfigurationException{
		DomainValid.postList(DomainValid.domainList("whiteListFileGoesHere.txt"));
	}

}