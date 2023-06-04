package com.peer39.websitereader.services;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Service
public class WebsiteReaderService {


    public Map<String,String> readWebSites(List<String> sites) {
        Map<String,String> sitesMap=new HashMap<>();
        sites.stream().forEach(c->{
            String siteStr = null;
            try {
                siteStr = readSite(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sitesMap.put(c,siteStr);
        });
        return  sitesMap;
    }



    private String readSite(String originUrl) throws IOException {
        URL url = new URL(originUrl);
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()) {
            String nextLine= sc.next();
            sb.append(nextLine);
            //System.out.println(sc.next());
        }
        //Retrieving the String from the String Buffer object
        String result = sb.toString();
        System.out.println(result);
        //Removing the HTML tags
        result = result.replaceAll("<[^>]*>", "");
        System.out.println("Contents of the web page: "+result);
        return result;
    }


}
