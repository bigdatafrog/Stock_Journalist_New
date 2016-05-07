package stockjournalist;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Stock_Journalist {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main (String args[]) throws IOException{
		String Domain = "http://www.moneycontrol.com";
		Document RTNews = Jsoup.connect(Domain+"/news/channel/stocks-6.html").get();
		//Element Reco_Link = RTNews.select("div.colL").first();
		String Reco_Link = RTNews.select("div[class]").text();
		//System.out.println("=====>"+Reco_Link);
//	}
//}
/*Get HTML Links*/
Elements links = RTNews.select("a[href]");
/*for (Element link : links){
	String News_Link = link.attr("href");
	System.out.println(News_Link);
	System.out.println("\nlink: " + link.attr("href"));
	System.out.println("text: "+ link.text());
	if(link.text().contains("Infosys")){
		//System.out.println("link: "+ link.attr("href"));
		Document RTSPLNews = Jsoup.connect(Domain+News_Link).get();
		String News = RTSPLNews.text();
		//if(News.contains("Tata Steel"))
		System.out.println(News);
	}
}*/
/*Read the output of refined stock list and check if there is any news available for it*/

String DMA_String = "";
for (Element link : links){
	BufferedReader br = new BufferedReader(new FileReader("/home/sekar/200DMA.txt"));
while ((DMA_String = br.readLine())!=null){
	//System.out.println(DMA_String);
	String[] DMA_Stock= DMA_String.split(",");
	/*
	 * DMA_Stock[0] ====> Stock Name
	 * DMA_Stock[1] ====> Real Time Quote
	 * DMA_Stock[2] ====>  200 DMA price
	 * 
	 * */
	String cs1 = DMA_Stock[0].toLowerCase().trim();
	if(!DMA_Stock[0].trim().isEmpty()){
String link_text = link.text().toLowerCase();
			String News_Link = link.attr("href");
			//if (link.text().contains(DMA_Stock[0].trim())){
			System.out.println("------------->"+link.text().toLowerCase()+"------------------"+DMA_Stock[0].toLowerCase());
			if(link_text.contains(cs1)){
			//if((link.text().toLowerCase()).indexOf(DMA_Stock[0].toLowerCase()) !=-1){
				try{
					Document RTSPLNews = Jsoup.connect(Domain+News_Link).get();
					System.out.println(RTSPLNews.text());
				}
				catch(UnknownHostException e){
					System.out.println(e);
				}
				catch (IllegalArgumentException iae){
					System.out.println(iae);
				}
				catch (SocketTimeoutException stoe){
					System.out.println(stoe);
				}
			}
		}
	}
br.close();
}

/*Read the output of refined stock list and check if there is any news available for it*/
}
}
/*Get HTML Links*/
/*Element RTQuote = doc.select("div.company").first();
System.out.println(RTQuote.select("font").text());*/
// get all links
/*			Elements links = doc.select("a[href]");
		for (Element link : links) {

			// get the value from href attribute
			System.out.println("\nlink : " + link.attr("href"));
			System.out.println("text : " + link.text());

		}
*/