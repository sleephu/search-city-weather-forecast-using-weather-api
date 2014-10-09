package api.openweathermap.org;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class ProcessReq
 */
@WebServlet("/ProcessReq")
public class ProcessReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessReq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
	    String city = request.getParameter("cityName");
	    String APPID= "d9766c47b105e268f7a46b67722a4afa";
//	   out.println(city);

	    InputStream is = null;
//	    HttpURLConnection con = null;
//	    CloseableHttpClient httpclient = HttpClients.createDefault();
//	    HttpGet httpGet = new HttpGet("http://targethost/homepage");
//	    CloseableHttpResponse response1 = httpclient.execute(httpGet);
	    
    try {
//	    	URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q='city'&mode=xml&units=metric&cnt=7");
    	
    	CloseableHttpClient httpclient = HttpClients.createDefault();  
    	URI uri = new URIBuilder()
	        .setScheme("http")
	        .setHost("api.openweathermap.org")
	        .setPath("/data/2.5/forecast/daily")
	        .setParameter("q", city)
	        .setParameter("mode", "xml")
	        .setParameter("units", "metric")
	        .setParameter("cnt", "7")
	        .setParameter("APPID", APPID)
	        .build();
    	 
	HttpGet httpget = new HttpGet(uri);	
//	URL url = new URL(uri.toString());
//	System.out.println(httpget.getURI());
	 CloseableHttpResponse response1 = httpclient.execute(httpget);
	// System.out.println(response1.toString());
	 try {
//		 out.println("httpget:"+httpget.getURI());
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
//		    con =(HttpURLConnection)url.openConnection();
//		    con.setRequestMethod("Get");
//		    con.connect();
//		
		   
            is=entity1.getContent();
	    	DocumentBuilderFactory factory =
		        DocumentBuilderFactory.newInstance();
		      DocumentBuilder builder = factory.newDocumentBuilder();
		      Document document = builder.parse(is);
//		      Element root = document.getDocumentElement();
//		      System.out.println(root.getTagName());
//		      System.out.printf("  location: %s%n",
//		                        root.getAttribute("name"));
//		      System.out.printf("  short name: %s%n",
//		                        root.getAttribute("shortName"));
//		      System.out.printf("  mission: %s%n",
//		                        root.getAttribute("mission"));
//		      if(document.hasChildNodes()){
//		    	  printNote(document.getChildNodes());
//		      }
//		      Element root = document.getDocumentElement();
//		      root.normalize();
//		      printNode(root, 0);
//		      NodeList list = document.getElementsByTagName("time");
		      Weather weather = new Weather();
//		      int length= list.getLength();
//		      System.out.println(length);
		//      out.println("----"+document.getDocumentElement().getNodeName());
		     
		      NodeList n3 = document.getElementsByTagName("location");
//		      out.println("name"+n3.item(1));
		      for(int x=0; x<n3.getLength();x++){
		    	  Element loc = (Element)n3.item(x);
		    	  NodeList locName =loc.getElementsByTagName("name");
		    	  NodeList contryName = loc.getElementsByTagName("country");
		    	  String location = locName.item(0).getTextContent()+","+contryName.item(0).getTextContent();
//		    			  n3.item(0).getAttributes().item(0).getTextContent();
		    	  weather.setLocation(location);
//		    	  request.setAttribute("weatherLocation", weather.getLocation());
		    	  out.println("<!DOCTYPE html>");
		  		  out.println("<html>");
		  		  out.println("<head></head>");
		  		  out.println("<body>");
		    	  out.println("<p><b>"+"\nLocation:"+weather.getLocation()+"</p></b>");
		    	  
		    	  NodeList sunN = document.getElementsByTagName("sun");
		    	  for(int k =0; k<sunN.getLength();k++){
		    		  Element sunE = (Element)sunN.item(k);
		    	  String sunR =sunE.getAttribute("rise");
		    	  String SunS = sunE.getAttribute("set");
		    	  weather.setSun("Sunrise:"+sunR+",Sunset:"+SunS);
		    	  out.println("<label>"+"\nSun:</label>"+weather.getSun()+"<br>");
		    	  }
		    	  NodeList foreN = document.getElementsByTagName("forecast");
		    	  out.println("<h2>weather forecast for future 7 days:\n"+"</h2><br>");
		    	  for(int n=0; n<foreN.getLength();n++){
		    	  Element foreE = (Element)foreN.item(n);
		    	  NodeList timeN= foreE.getElementsByTagName("time");
		    	  for(int j=0; j<timeN.getLength();j++){ 
		    		  Element timeE = (Element)timeN.item(j);  
		   	    	  String time= timeE.getAttribute("day");
		    	      weather.setTime("<label>\nDay:</label>"+time);
//		    			  timeN.item(j).getAttributes().item(0).getTextContent();
		    	      out.println("\n"+weather.getTime()+"<br>");
		    	      
		    	  NodeList symbolN=timeE.getElementsByTagName("symbol");
//			      NodeList n2= document.getElementsByTagName("time");
//			      for(int j=0; j<n2.getLength();j++){
				      for(int i=0;i<symbolN.getLength();i++){
				    	  Element symbolE =(Element)symbolN.item(i);
//				    	 for(int j=0;j<3;j++){
//				    	  for(int j=0; j<nodeList.getLength();j++){
				    	 String symbol = symbolE.getAttribute("name");
				    	 weather.setSymbol(symbol);
				    	 out.println("<label>\nSymbol:</label>"+weather.getSymbol()+"<br>");
				    	
				    	 NodeList  windireN = timeE.getElementsByTagName("windDirection");
				    	 NodeList winSN = timeE.getElementsByTagName("windSpeed");
				    	 NodeList temN = timeE.getElementsByTagName("temperature");
				    	 NodeList humN = timeE.getElementsByTagName("humidity");
				    	 for(int m=0; m<windireN.getLength();m++){
				    		 Element windireE =(Element)windireN.item(m);
				    		 Element winSE = (Element)winSN.item(m);
				    		 Element temE = (Element)temN.item(m);
				    		 Element humE =(Element)humN.item(m);
				    		 String windirectionD = windireE.getAttribute("name");
				    		 String windD = windireE.getAttribute("deg");
				    		 weather.setWindDirection(windirectionD+"("+windD+"deg)");
				    		 String windSpeed = winSE.getAttribute("name")+" "+winSE.getAttribute("mps")+"m/s";
				    		 weather.setWindSpeed(windSpeed);
				    		 out.println("<label>\nWind:<label>"+weather.getWindSpeed()+"<br>\n"+weather.getWindDirection()+"<br>");
				    		 String temp = "morning:"+temE.getAttribute("morn")+" "+"day:"+ temE.getAttribute("day")
				    				 +" "
				    				 +"Evening:"+temE.getAttribute("eve")+" "+"night:"+temE.getAttribute("night")
				    				 +"\nMax:"+temE.getAttribute("max")+" "+"min:"+temE.getAttribute("min");
				    		 weather.setTemperature(temp);
				    		 out.println("<label>Temperature(Celsius):</label>"+weather.getTemperature()+"<br>");
				    		 String humidity = humE.getAttribute("value")+humE.getAttribute("unit");
				    		 weather.setHumidity(humidity);
				    		 out.println("<label>\nHumidity:"+weather.getHumidity()+"</label><br>");
				    	 }
//				    	  String cloud 
//				    	  = nodeList.item(i).getAttributes().item(0).getTextContent();
//				    	  String time= n2.item(j).getAttributes().item(0).getTextContent();
//				    	  String location = n3.item(x).getAttributes().item(0).getTextContent();
				    	 
//				    	  weather.setCloud(cloud);
//				    	  weather.setTime(time);
//				    	  weather.setLocation(location);
//				    	 out.println("\n" +
		//weather.getLocation()
//				    			 +
//				    			 weather.getTime()
		//n2.item(j).getAttributes().item(0).getTextContent()
//		+
//		weather.getCloud()
		//nodeList.item(i).getAttributes().item(0).getTextContent()
//		);
				    	  }
				    	 }
		    			  }
		    	  
	
		      }
	
//		      }
//		      for(int i=0; i<length; i++){
//		    	  Node node=list.item(i);
//		    	  Element eElement=(Element)node;
//		    	  System.out.println(list.item(i).getChildNodes().getLength());
//		    	  System.out.println("\n\n"+eElement.getAttribute("day"));
////		    	  request.setAttribute("list", list);
//		      }
		    EntityUtils.consume(entity1);
		    out.println("</body>");
			out.println("</html>");
		} finally {
		    response1.close();
		}
//	System.out.println(httpget.getURI());
//	    	con =(HttpURLConnection)uri.openConnection();
		   
		     
		    } catch(Exception e) {
		      e.printStackTrace();
		    }
//    request.setAttribute("weather", weather);
   
		  }
	

//	private static void printNode(Node node, int depth) {
//		String prefix = padChars(2*depth, " ");
//	    if (node.getNodeType() == Node.TEXT_NODE) {
//	      System.out.printf("%s%s%n", prefix, node.getNodeValue());
//	    } else {
//	      NamedNodeMap attributes = node.getAttributes();
//	      if ((attributes == null) || (attributes.getLength() == 0)) {
//	        System.out.printf("%s%s%n",
//	                          prefix, node.getNodeName());
//	      } else {
//	        System.out.printf("%s%s [", 
//	                          prefix, node.getNodeName());
//	        printAttributes(attributes);
//	        System.out.println(" ]");
//	      }
//	    }
//	    NodeList children = node.getChildNodes();
//	    for(int i=0; i<children.getLength(); i++) {
//	      Node childNode = children.item(i);
//	      printNode(childNode, depth+1);
//	    }
//	  }
//
//	  private static void printAttributes(NamedNodeMap attributes)  {
//	    for(int i=0; i<attributes.getLength(); i++) {
//	      Node attribute = attributes.item(i);
//	      System.out.printf(" %s=\"%s\"", attribute.getNodeName(),
//	                        attribute.getNodeValue());
//	    }
//	  }
//
//	  private static String padChars(int n, String orig) {
//	    StringBuilder result = new StringBuilder("");
//	    for(int i=0; i<n; i++) {
//	      result = result.append(orig);
//	    }
//	    return(result.toString());
//	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
