package com.acortes.hcrawler;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HoodController {
		
	public static HtmlPage hoodCrawler(String url) {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setJavaScriptEnabled(false);
	 	webClient.getOptions().setThrowExceptionOnScriptError(false);
	 	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	 	HtmlPage pageHood = null;
	 	
	 	try {
			 pageHood = webClient.getPage(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
	 	
	 	return pageHood;
	}
	
}
