package stockjournalist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovingAverage {

	public static void main (String args[])
	{
		BufferedReader br = null;
		MovingAverage MA = new MovingAverage();
		
		try {
			String strLine="";
            br = new BufferedReader( new FileReader("/home/sekar/Yahoo.txt"));
            while( (strLine = br.readLine()) != null){
            	System.out.println(strLine);
            	float DMA50_part1 = MA.StockReceiver(strLine,0);
            	float DMA50_part2 = MA.StockReceiver(strLine,66);
            	float DMA50_part3 = MA.StockReceiver(strLine,132);
            	float DMA50 = DMA50_part1;
            	float DMA100 = (DMA50_part1 + DMA50_part2)/2;
            	float DMA200 = (DMA50_part1 + DMA50_part2+DMA50_part3)/3;
            	System.out.println("DMA50---------->"+DMA50+"DMA100---------->"+DMA100+"DMA200---------->"+DMA200);
            	/*Write to 200DMAData.txt file*/
            	BufferedWriter bw = null;
        		try{
        			bw = new BufferedWriter(new FileWriter("/home/sekar/200DMAData.txt",true));
        			bw.write(strLine+","+DMA200+","+DMA100+","+DMA50);
        			bw.newLine();
        			bw.close();
        		}
            	catch (IOException e){
            		System.out.println(e);
            	}
        		/*Write to 200DMAData.txt file*/
            	//System.out.println(DMA50_part3);
            }
            br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (NullPointerException NPE){
			//System.out.println(NPE);
			NPE.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }	
	public float StockReceiver(String quote,int range) throws IOException,HttpStatusException,ArrayIndexOutOfBoundsException{
		//Document doc = Jsoup.connect("http://nseguide.com/stock.php?"+quote).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36").timeout(1000*10).get();
		  
		Document doc = Jsoup.connect("https://in.finance.yahoo.com/q/hp?s="+quote+".NS&a=8&b=19&c=2007&d=3&e=9&f=2016&g=d&z=66&y="+range).get();
		float sumtotal=0;
		float DMA50_part = 0;		  
		  Element table = doc.select("TABLE[class = yfnc_datamodoutline1]").first();
		  Elements rows = table.select("tr");
		  int i=2;
		  
		  while(i<rows.size()){
			  Element row = rows.get(i);
			  Elements cols = row.select("td");
			  try {
				  sumtotal = sumtotal+Float.parseFloat(cols.get(6).text().replaceAll(",", ""));
				  if (i >=67 || i>=68){
					  DMA50_part = sumtotal/66;
					  //System.out.println("DMA50----->"+DMA50_part);
				  }
			  }
			  catch (IndexOutOfBoundsException IOB){
				  System.out.println("i--->"+i);
			  }
			  i++;
		  }
		  return DMA50_part;
		/*//System.out.println(doc);
		Element table = doc.select("table").get(5); //select the first table.
		
		//Elements rows = table.select("tr");
		Elements cols = table.select("td");
		System.out.println("----------->"+cols);*/
		//Elements divs = table.select("div");
		/*Map stockcmp = new HashMap();
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter("/home/sekar/200DMAData.txt",true));
			float price = Float.parseFloat(cols.get(39).text());
			bw.write(quote+","+price);
			bw.newLine();
			bw.close();
		}
		catch (HttpStatusException httpex){
			System.out.println("inside httpstatusex");
			httpex.printStackTrace();
		}
		catch(IOException ioe){
			System.out.println("inside ioe");
			ioe.printStackTrace();
		}*/
		
		}
		/*if (cols.get(38).text().equals("200 DMA")){
			float price = Float.parseFloat(cols.get(39).text());
			stockcmp.put(quote,price);
			System.out.println("From stockcmp----->"+stockcmp.get(quote));
			System.out.println("Moving Average From NSE Guide---->"+cols.get(39).text());
			System.out.println("true - Found 200 DMA");
			RealTimeQuotes RT = new RealTimeQuotes();
			RT.QuoteForRTQuote(quote, price);
		}
		else {
			System.out.println("False- Unable to find 200 DMA in the page");
		}*/
}
