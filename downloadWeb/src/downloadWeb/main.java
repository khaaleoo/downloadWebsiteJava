package downloadWeb;

import java.io.*; 

import java.net.URL; 
import java.net.MalformedURLException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class main {
	public static void DownloadWebPage(String webpage,File out) throws IOException
    { 
//		Document doc = Jsoup.connect(webpage).get();
//		Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//        System.out.println("\nMedia: " + media.size());
//        System.out.println("\nImports: " + imports.size());
//        System.out.println("\nLinks: " + links.size());
//        for (Element link : links) {
//        	//System.out.println(link.attr("abs:href"));
//        	//System.out.println(link.attr("abs:href"));
//        	String url = link.attr("abs:href"); 
//        	DownloadWebPage(url,"./website downloaded/"+link.attr("abs:href").replace("https://", "")+".html");
//        	//System.out.println("./website downloaded/"+link.attr("abs:href").replace("https://", "")+".html");
////        	String path = "C:\\Users\\Kha Leo PC\\Desktop\\downloaded\\" + link.attr("abs:href").replace("https://", "")+"\\index.html";
////        	File out1 = new File(path);
////           	new Thread(new downloadUrl(url,out1)).start();
//        }
////        for (Element src : media) {
////        	System.out.println(src.tagName() + " : " + src.attr("abs:src"));
////        }
        
        Document html = Jsoup.connect(webpage).get();
        
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(out), "UTF8"));

            writer.write(html.html());

        } catch (IOException e) {
        }
        writer.close();

        System.out.print("Download Successfully");
    }

	public static void DownloadAll(String webpage, File out) throws IOException{
		String abc = out.toString();
		abc = abc + "/index.html";
		File newOut = new File(abc);
		DownloadWebPage(webpage,newOut);
		Document doc = Jsoup.connect(webpage).get();
		Elements links = doc.select("a[href]");
		
        for (Element link : links) {
        	//System.out.println(link.attr("abs:href"));
        	
        	String path = "./" + out.toString().replace(".\\", "") + "/" + link.attr("abs:href").replace("https://", "");
        	path = path.replace("http://", "");
        	
        	createDirectories a = new createDirectories();
        	try {
        		a.createDirectories(path);
        		if (path.charAt(path.length()-1) == '/')
            		path = StringUtils.chop(path);
            	path += "/index.html";
            	File File_path = new File(path);
            	
            	DownloadWebPage(link.attr("abs:href"),File_path);
            	
            	//note here
            	//link.attr("href", path);
            	
            	System.out.println(link);
            	//System.out.println(link.attr("abs:href"));
            	//System.out.println(path+"\n");
        	}
        	catch (Exception e){
        		System.out.println("Không thể download:" + link.attr("abs:href"));
        	}
        	        	
        	//String url = link.attr("abs:href"); 
        	//DownloadWebPage(link.attr("abs:href"),);
        }
        
		renameHref(abc);
	}
	
	public static void renameHref(String path) throws IOException {
		path = "./" + path.toString().replace(".\\", "");
    	//System.out.println(path);
		Document doc = Jsoup.parse(new File(path), "UTF-8", "");
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String a = link.attr("href");
			a = a.replace("http://", "");
			a = a.replace("https://", "");
			if (a.charAt(a.length()-1) == '/')
        		a = StringUtils.chop(a);
			a = "./" + a + "/index.html";
			link.attr("href",a);
			System.out.println(link);
		}
		BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path)), "UTF8"));

            writer.write(doc.html());

        } catch (IOException e) {
        }
        writer.close();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		String url = "https://bongda24h.vn/"; 
		//File out = new File("C:\\Users\\Kha Leo PC\\Desktop\\downloaded\\index.html");
		File path = new File("./website downloaded");
		//File path1 = new File(".///website downloaded");
		//System.out.println(path1.toString());
		DownloadAll(url,path);
       	//new Thread(new downloadUrl(url,out)).start();
		
	}

}