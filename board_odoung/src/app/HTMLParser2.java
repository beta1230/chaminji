package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.DBConn;

public class HTMLParser2 {
	public static void main(String[] args) throws Exception {
		Connection conn = Jsoup.connect("https://www.daangn.com/hot_articles");
		Document doc = conn.get();
		Elements elements = doc.select(".card-top");
//		System.out.println(elements);
//		System.out.println(doc);
		
		for(int i = 0 ; i < elements.size() ; i++) {
			Element el = elements.get(i);
			String title = el.selectFirst(".card-title").text();
//			System.out.println(title);
			String price = el.selectFirst(".card-price").text();
//			System.out.println(price);
			
			String imglink = el.selectFirst(".card-photo img").attr("src");
//			System.out.println(imglink);
			String link = "https://www.daangn.com" + el.selectFirst(".card-link").attr("href");
			System.out.println(link);
//			Element img = el.selectFirst(".jAyaSO img");
			
//			
			Map<String, String> map = new HashMap<>();
			map.put("title", title);
			map.put("price", price);
			map.put("imglink", imglink);
			map.put("link", link);
			
			System.out.println(map);
			
//			saveDB(map);
//			saveFile(no, img.attr("data-original"));
//			
//			System.out.println(no + "번 작업 완료");
			
		}
			
	}
	
//	static void saveFile(String no, String imgSrc) throws Exception {
//		URL url = new URL(imgSrc);
//		BufferedInputStream bis = new BufferedInputStream(url.openStream());
//		File file = new File("d:\\bun", no);
//		if(!file.exists()) {
//			file.mkdirs();
//		}
//		
//		file = new File(file, "thumb.jpg");
//		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//		
//		int b = 0;
//		while((b = bis.read()) != -1) {
//			bos.write(b);
//		}
//		bos.close();
//	}
//	
//	static void saveDB(Map<String, String> map) throws Exception {
//		PreparedStatement pstmt = DBConn.getConnection().prepareStatement(
//				"INSERT INTO TBL_MUSINSA_SAMPLE VALUES(?, ?, ? ,?, ?)");
//		
//		pstmt.setString(1, map.get("no"));
//		pstmt.setString(2, map.get("title"));
//		pstmt.setString(3, map.get("info"));
//		pstmt.setString(4, map.get("price"));
//		pstmt.setString(5, map.get("link"));
//		
//		pstmt.executeUpdate();
//		pstmt.close();
//		
//	}
//	
//	static void doOldParsing() throws Exception {
//		String urlStr = "https://www.musinsa.com/category/014001";
//		URL url = new URL(urlStr);
//		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//		BufferedWriter bw = new BufferedWriter(new FileWriter("무신사.html"));
//		
//		String s = null;
//		while((s = br.readLine()) != null) {
//			System.out.println(s);
//			bw.write(s);
//			bw.newLine();
//		}
//		
//		bw.close();		
//	}

}
