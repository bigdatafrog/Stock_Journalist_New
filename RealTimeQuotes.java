package stockjournalist;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RealTimeQuotes{
	public void QuoteForRTQuote(String Quote) throws IOException{
		Document RTQuoteDetail = Jsoup.connect("https://www.google.com/finance?q=NSE%3A"+Quote+"&ei=lxDuVoD8KtCvuATU_53oBA").get();
		String RTQuote = RTQuoteDetail.select("span[id]").text();
		String RTQuote_split[] = RTQuote.split("\\s+");
		String RTQuote_Clean = RTQuote_split[0].replaceAll(",", "");
		System.out.println(RTQuote_Clean);
		Float DMAprice;
		/*Read the file - 200DMAData and get 200 DMA price*/
		//BufferedReader br = new BufferedReader(new FileReader("/home/sekar/200DMAData.txt"));
		/*while ((strLine = new String (br.readLine()))!=null){
			if (strLine.contains(Quote)){
				String [] temp;
				String delimiter = "|";
				temp=strLine.split(delimiter,0);
				DMAprice = Float.parseFloat(temp[1]);*/
		
				//String strLine="";
				File f = new File("/home/sekar/200DMAData.txt");
		        Scanner sc = new Scanner(f);
		        while (sc.hasNext()){
		        	String line = sc.nextLine();
		        	if(line.contains(Quote)){
		             String[] details = line.split(",");
		             DMAprice = Float.parseFloat(details[1]);//100
		            // System.out.println("DMAprice---->"+DMAprice);
				/*Logic to get the Upper cut-off and Lower Cut-off of DMA*/
					double CutOff = (0.05*DMAprice); 
					double lowerCutOff = DMAprice - CutOff;
					double upperCutOff = DMAprice + CutOff;
					System.out.println("upperCutOff---->"+upperCutOff);
					System.out.println("lowerCutOff---->"+lowerCutOff);
				/*Logic to get the Upper cut-off and Lower Cut-off of DMA*/
				
				
				//if(Float.parseFloat(RTQuote_Clean) > DMAprice){
					if (Float.parseFloat(RTQuote_Clean) >= lowerCutOff && Float.parseFloat(RTQuote_Clean) <= upperCutOff){
					BufferedWriter bw = null;
					try{
						 bw = new BufferedWriter(new FileWriter("/home/sekar/200DMA.txt", true));		
					     bw.newLine();
						 bw.write(Quote+"      ,"+RTQuote_Clean+"   ,"+DMAprice+"   ,");
					     bw.flush();
					}
					catch(IOException ioe){
						ioe.printStackTrace();
					}
					finally{
						if (bw != null) try {
					        bw.close();
					     } catch (IOException ioe2) {
					        // just ignore it
					     }
					}
					System.out.println("STOCK TO WATCHOUT FOR"+Quote);
				}
		        }
		        }
			}
		/*Read the file - 200DMAData and get 200 DMA price*/
		//System.out.println("Real Time Quote Info from Google Finance----->"+RTQuote_split[0]);
		/*//Element span = RTQuote.select("span.pr").first();
		Element span = RTQuoteDetail.select("span.pr").first();
		System.out.println(span);*/	
	//	System.out.println(RTQuoteDetail.select("span[id]").text());
	}