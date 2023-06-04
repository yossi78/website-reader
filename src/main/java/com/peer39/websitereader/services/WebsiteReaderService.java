package com.peer39.websitereader.services;
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
                siteStr = readTextFromURL(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sitesMap.put(c,siteStr);
        });
        return  sitesMap;
    }


    public String readTextFromURL(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        String result =  content.toString();
        result=clearWebText(result);
        return result;
    }


    private String clearWebText(String text){
        text = text.replaceAll("<[^>]*>", "");
        text = text.replaceAll("[\r\n]+", "\n");
        text = text.replaceAll("[\n]+", "\n");
        text = text.replaceAll("\t", "");
        text = text.replaceAll("\\s+", " ");
        text = text.replaceFirst("^\\s+", "");
        return text;
    }




}
