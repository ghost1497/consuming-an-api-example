package com.cd.consumeanapitest.controller;

import com.cd.consumeanapitest.domain.NasaPicture;
import com.cd.consumeanapitest.service.NasaPictureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nasa")
public class NasaPictureController {

    private static final Logger logger = LoggerFactory.getLogger(NasaPictureController.class);

    @Autowired
    private NasaPictureService nasaPictureService;

    @GetMapping("/pic-of-the-day")
    public ResponseEntity<NasaPicture> getNasaPicOfTheDay() {
        ResponseEntity<NasaPicture> reponse = null;

        try {
            reponse = new ResponseEntity<NasaPicture>(nasaPictureService.getNasaPicOfTheDay(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Occurred: {}", e);
            reponse = new ResponseEntity<NasaPicture>(new NasaPicture(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return reponse;
    }
}
