package stockjournalist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class splittest {
	public static void main (String args[]) throws IOException{
		 String strLine="";
		 System.out.println("-------------------->");
		 File f = new File("/home/sekar/200DMAData.txt");
         Scanner sc = new Scanner(f);
         while (sc.hasNext()){
        	 String line = sc.nextLine();
             String[] details = line.split(",");
             System.out.println(details[1]);
         }
		/*BufferedReader br = new BufferedReader(new FileReader("/home/sekar/200DMAData.txt"));
		while ((strLine = new String (br.readLine()))!=null){
			if (strLine.contains("AMARAJABAT")){
				String [] temp = strLine.split(",");
				for (int x=0; x<temp.length; x++) {
					  System.out.println(temp[x]);
					}
				}
		}*/
		//br.close();
	}
}
