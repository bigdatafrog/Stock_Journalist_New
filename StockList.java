package stockjournalist;

import java.io.BufferedReader;
import stockjournalist.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StockList{
	public static void main(String args[]) throws IOException{
		BufferedReader br = null;
        String strLine = "";
        ArrayList<String> temps = new ArrayList<String>();
        try {
            br = new BufferedReader( new FileReader("/home/sekar/Auto_NSE.txt"));
            while( (strLine = br.readLine()) != null){
            	temps.add(strLine);
                //System.out.println(strLine);
            }
            String[] tempsArray = temps.toArray(new String[0]);
            for (String s : tempsArray) {
                //System.out.println(s);
                /*Pass the Stock to Real Time Quotes*/
                	RealTimeQuotes RT = new RealTimeQuotes();
                	RT.QuoteForRTQuote(s);
                	/*MovingAverage MA = new MovingAverage();
                	MA.StockReceiver(s);*/
                /*Pass the Stock to Real Time Quotes*/
            }br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
	}
}
