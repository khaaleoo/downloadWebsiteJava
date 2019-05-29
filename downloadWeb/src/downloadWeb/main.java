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
	
	public static void DownloadImages(String webpage, File out) throws IOException {
		Document document = Jsoup
                .connect(webpage)
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .get();
		//select all img tags
        Elements imageElements = document.select("img");
        
        //iterate over each image
        for(Element imageElement : imageElements){
            
            //make sure to get the absolute URL using abs: prefix
            String strImageURL = imageElement.attr("abs:src");
            
            //download image one by one
            downloadImage(strImageURL, out);
            
        }
	}
	
private static void downloadImage(String strImageURL, File out){
        
        //get file name from image path
        String strImageName = 
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );
        
        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
        
        try {
            
            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            
            byte[] buffer = new byte[4096];
            int n = -1;
            
            OutputStream os = 
                new FileOutputStream( out.toString() + "/" + strImageName );
            
            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            
            //close the stream
            os.close();
            
            System.out.println("Image saved");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	 

	public static void DownloadAll(String webpage, File out) throws IOException{
//		String abc = out.toString();
//		abc = abc + "/index.html";
//		File newOut = new File(abc);
//		DownloadWebPage(webpage,newOut);
//		Document doc = Jsoup.connect(webpage).get();
//		Elements links = doc.select("a[href]");
//		//download html href
//        for (Element link : links) {   			
//        	try {
//        		String checkURL = new URL(link.attr("abs:href")).getHost();
//        		if(!checkURL.equals(getMainHost.mainHost))
//        			continue;
//        	}
//        	catch(Exception e) {
//        		continue;
//        	}
//        	String path = "./" + out.toString().replace(".\\", "") + "/" + link.attr("abs:href").replace("https://", "");
//        	path = path.replace("http://", "");
//        	if (listHrefDownloaded.listHref.contains(path)) {
//        		continue;
//        	}
//        	else {
//        		
//	    		//thêm path vào listHref
//	    		listHrefDownloaded.listHref.add(path);
//	        	createDirectories a = new createDirectories();
//	        	try {
//	        		//tạo thư mục chứa html
//	        		a.createDirectories(path);
//	        		
//	        		
//	        		if (path.charAt(path.length()-1) == '/')
//	            		path = StringUtils.chop(path);
//	            	path += "/index.html";
//	            	File File_path = new File(path);
//	            	
//	            	DownloadWebPage(link.attr("abs:href"),File_path);	            
//	            	
//	            	//System.out.println(link);
//
//	        	}
//	        	catch (Exception e){
//	        		//System.out.println("Cannot download:" + link.attr("abs:href"));
//	         }
//        	}
//        }
//        renameHref rename = new renameHref();
//		rename.renameHref(abc);
//		//System.out.println(listHrefDownloaded.listHref.size());
		
		DownloadImages(webpage, out);
		
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		String url = "https://www.24h.com.vn/"; 
		File path = new File("./website downloaded");
		getMainHost.mainHost += new URL(url).getHost();
		System.out.println(getMainHost.mainHost);
		DownloadAll(url,path);		
	}

}