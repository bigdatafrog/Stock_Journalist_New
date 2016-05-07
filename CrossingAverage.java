package stockjournalist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrossingAverage  {
	public static void main(String args[]){
		BufferedReader br = null;
		try {
			String strLine="";
	        br = new BufferedReader( new FileReader("/home/sekar/200DMAData.txt"));
	        while( (strLine = br.readLine()) != null){
	        	String[] details = strLine.split(",");
	        	if(Float.parseFloat(details[3])>=Float.parseFloat(details[1])){
	        		double cutoffDMA_200 = (0.04*Float.parseFloat(details[1]));
	        		double lowerCutOff = Float.parseFloat(details[1]) - cutoffDMA_200;//96
					double upperCutOff = Float.parseFloat(details[1]) + cutoffDMA_200;//104
					double cutoffDMA_50 = (0.04*Float.parseFloat(details[1]));
	        		double lowerCutOff_50 = Float.parseFloat(details[1]) - cutoffDMA_50;//96
					double upperCutOff_50 = Float.parseFloat(details[1]) + cutoffDMA_50;//104
	        		if (Float.parseFloat(details[3])>=lowerCutOff && Float.parseFloat(details[3])<=upperCutOff){
	        			/*Get the Real Time Quote and make sure its above 200DMA*/
	        			Document RTQuoteDetail = Jsoup.connect("https://www.google.com/finance?q=NSE%3A"+details[0]+"&ei=lxDuVoD8KtCvuATU_53oBA").get();
	        			String RTQuote = RTQuoteDetail.select("span[id]").text();
	        			String RTQuote_split[] = RTQuote.split("\\s+");
	        			String RTQuote_Clean = RTQuote_split[0].replaceAll(",", "");
	        			if(Float.parseFloat(RTQuote_Clean) >= lowerCutOff_50 && Float.parseFloat(RTQuote_Clean) <= upperCutOff_50 ){
	        				System.out.println(details[0]+","+details[3]+","+details[1]);
	        			}
	        			/*Get the Real Time Quote and make sure its above 200DMA*/
	        			
	        		}
	        	}
	        }		
		}
	    catch (IOException IOE){
	        	System.out.println(IOE);
	    }
    }
}
