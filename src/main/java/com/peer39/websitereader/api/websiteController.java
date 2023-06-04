package com.peer39.websitereader.api;


import com.peer39.websitereader.services.WebsiteReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/websites")
@Slf4j
public class websiteController {


    @Autowired
    private WebsiteReaderService websiteReaderService;


    @PostMapping(value = "/readingSites")
    public ResponseEntity readWebSites(@RequestBody List<String> sites) {
        try {
            Map<String,String> sitesMap =  websiteReaderService.readWebSites(sites);
            return new ResponseEntity(sitesMap,HttpStatus.OK);
        }catch (Exception e){
            log.error("Failed to add new Human",e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
