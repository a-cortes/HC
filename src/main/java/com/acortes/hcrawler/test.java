package com.acortes.hcrawler;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class test {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		System.out.println("start");
		
	
		 try {
			 //http://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/
			 	WebClient webClient = new WebClient();
			 	webClient = new WebClient(BrowserVersion.CHROME);
			 	webClient.getOptions().setJavaScriptEnabled(false);
			 	webClient.getOptions().setThrowExceptionOnScriptError(false);
			 	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			 	
		        HtmlPage page = webClient.getPage("https://inmuebles.mercadolibre.com.mx/casas/venta/queretaro/queretaro/");
		        System.out.println(page.asText());
		        System.out.println("fin");
		    }
		 catch(Exception e) {
			 StringWriter errors = new StringWriter();
			 e.printStackTrace(new PrintWriter(errors));
			 System.out.println(errors.toString().substring(0,500));
			 
		 }
	}
}
