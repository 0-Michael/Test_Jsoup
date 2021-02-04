import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import org.jsoup.*;

public class Test {

	public static void main(String[] args) {
		// the URL of the website
		String url = "https://www.zcool.com.cn/work/ZNDEwMjYwMDA=.html";
		try {
			Document document = Jsoup.parse(new URL(url), 10000);
			Element contentElement = document.getElementById("body");
			Elements imgs = contentElement.getElementsByTag("img");
			int id = 0;
			for (Element img : imgs) {
				String srcString = img.attr("src");
				// check
				if (srcString.substring(0, 4).equals("http")) {
					URL targetUrl = new URL(srcString);
					URLConnection urlConnection = targetUrl.openConnection();
					InputStream inputStream = urlConnection.getInputStream();
					// download images
					OutputStream outputStream = new FileOutputStream("D:\\111\\" + id + ".jpg");
					id++;
					int temp = 0;
					while ((temp = inputStream.read()) != -1) {
						outputStream.write(temp);
					}
					System.out.println(id + " download successfully");
					inputStream.close();
					outputStream.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
