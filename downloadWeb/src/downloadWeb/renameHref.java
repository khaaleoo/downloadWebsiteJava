package downloadWeb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class renameHref {
	public static void renameHref(String path) throws IOException {
		path = "./" + path.toString().replace(".\\", "");
    	//System.out.println(path);
		Document doc = Jsoup.parse(new File(path), "UTF-8", "");
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String a = link.attr("abs:href");
			
//			//nếu href là : "/" thì gán trang chủ vào 
//			if (a.length() == 1) {
//				a = "../website downloaded/" + "/index.html";
//				continue;
//			}
			a = a.replace("./", "");
			a = a.replace("../", "");
			a = a.replace("http://", "");
			a = a.replace("https://", "");
			if(a.length()==0) {
				a = "../website downloaded/" + "/index.html";
				continue;
			}
			if (a.charAt(a.length()-1) == '/'){
				a = StringUtils.chop(a);
			}
//			if (a.charAt(0) == '/')
//				a = a.substring(1);
			a = "../website downloaded/" + a + "/index.html";
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
}
