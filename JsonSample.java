package stockjournalist;
import java.io.IOException;

import org.json.simple.JSONObject;

import org.apache.solr.*;
import org.apache.solr.client.solrj.request.DirectXmlRequest;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.request.SolrPing;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.XML;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.*;
import org.apache.solr.client.solrj.beans.Field;



public class JsonSample {
	
	public static void main(String args[]) throws IOException, SolrServerException{
		JsonSample js = new JsonSample();
		js.insertOneDoc();
	}
	private SolrServer getSolrServer(){ 
			String server = "http://localhost:8983/solr";
		    SolrServer solrServer = null;
            solrServer = new HttpSolrServer(server); 
            return solrServer;  
    } 
	public void insertOneDoc() throws SolrServerException, IOException {
		  Item item = new Item();
	      item.id = "5137158701";
		  //String urlString = "http://localhost:8983/solr";
		  //HttpSolrServer solr = new HttpSolrServer(urlString);
		  SolrServer server = getSolrServer();
		  server.addBean(item);
		  server.commit();
		  //SolrInputDocument doc = new SolrInputDocument();
		  //doc.addField("id", "552199");
		  //doc.addField("name", "See if 513715870 is available in the index. Saikrishnan Sekar has added this from Java Program");
		  //solr.add(doc);
		  //solr.commit();
		}
}
