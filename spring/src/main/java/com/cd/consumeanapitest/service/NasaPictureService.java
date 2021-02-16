package com.cd.consumeanapitest.service;


import com.cd.consumeanapitest.domain.NasaPicture;
//import com.cd.consumeanapitest.repo.NasaPictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class NasaPictureService {

    private static final Logger logger = LoggerFactory.getLogger(NasaPictureService.class);

    @Value("${nasa.api.url}")
    private String nasaApiUrl;

    @Value("${nasa.api.potd.endpoint}")
    private String nasaPicOfTheDayEndpoint;

    @Value("${nasa.api.apikey}")
    private String nasaApiKey;
//
//    @Autowired
//    private NasaPictureRepo nasaPictureRepo;

    @Autowired
    private RestTemplate restTemplate;

    public NasaPicture getNasaPicOfTheDay() {
        NasaPicture picOfTheDay = new NasaPicture();

        try{
            ResponseEntity<NasaPicture> responseFromNasa = restTemplate.getForEntity(URI.create(buildPictureOfTheDayUrl()), NasaPicture.class);
            if(!HttpStatus.OK.equals(responseFromNasa.getStatusCode())){
                picOfTheDay = responseFromNasa.getBody();
            }

        }catch (Exception e){
            logger.error("Exception Occurred: " + e);
        }

        return picOfTheDay;
    }

    private String buildPictureOfTheDayUrl() {
        StringBuilder nasaPictureApiUrl = new StringBuilder();
        nasaPictureApiUrl.append(nasaApiUrl);
        nasaPictureApiUrl.append(nasaPicOfTheDayEndpoint);
        nasaPictureApiUrl.append("?api_key=");
        nasaPictureApiUrl.append(nasaApiKey);
        return nasaPictureApiUrl.toString();
    }
}
