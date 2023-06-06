package com.peer39.websitereader.services;
import com.peer39.websitereader.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Service
public class WebsiteReaderService {


    public Map<String,String> readWebSites(List<String> sites) {
        Map<String,String> sitesMap=new HashMap<>();
        sites.stream().forEach(c->{
            String siteStr = null;
            try {
                siteStr = parseTextContentFromURL(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sitesMap.put(c,siteStr);
        });
        return  sitesMap;
    }



    public String parseTextContentFromURL(String urlString) throws IOException {
        String text ="";
        try {
            // Fetch the HTML content from the URL
            Document doc = Jsoup.connect(urlString).get();


            String title = doc.getElementsByTag("title").text();
            System.out.println("");

            // Extract the plain text from the document
            text = doc.body().text();

            // Print the extracted text
            System.out.println(text);
            //text=clearWebText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;

    }




//    public String parseTextContentFromURL(String urlString) throws IOException {
//        StringBuilder content = new StringBuilder();
//        URL url = new URL(urlString);
//        URLConnection connection = url.openConnection();
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                content.append(line);
//                content.append(System.lineSeparator());
//            }
//        } finally {
//            if (reader != null) {
//                reader.close();
//            }
//        }
//        String result =  content.toString();
//        result=clearWebText(result);
//        return result;
//    }


    private String clearWebText(String text){
        text = StringUtil.removeTags(text);
        text = StringUtil.removeSpacesFromBegining(text);
//        text = StringUtil.replaceNewLinesWithSpace(text);
//        text = StringUtil.replaceMultipleSpacesWithSingleOne(text);
        return text;
    }







}
