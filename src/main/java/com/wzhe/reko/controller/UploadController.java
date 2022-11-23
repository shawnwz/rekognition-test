package com.wzhe.reko.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wzhe.reko.dto.RekognitionRequestDto;
import com.wzhe.reko.service.OpencvService;
import com.wzhe.reko.service.RekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;


@RequestMapping("/upload")
@RestController
public class UploadController {

    @Autowired
    RekognitionService rekognitionService;

    @Autowired
    OpencvService opencvService;

    Region region = Region.US_WEST_2;
    RekognitionClient rekClient = RekognitionClient.builder()
            .region(region)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestBody(required = true)RekognitionRequestDto dto) throws JsonProcessingException {
        //RekognitionResponse resp = new RekognitionResponse();
        Boolean hasFace = rekognitionService.detectFacesinImage(rekClient, dto.getSourceImage());

        return new ResponseEntity<>(hasFace.toString(), HttpStatus.OK);
    }


    @RequestMapping(path = "opencv", method = RequestMethod.POST)
    public ResponseEntity<Integer> opencv(@RequestBody(required = true)RekognitionRequestDto dto) throws JsonProcessingException {
        //RekognitionResponse resp = new RekognitionResponse();
        //Boolean hasFace = rekognitionService.detectFacesinImage(rekClient, dto.getSourceImage());
        int n = opencvService.checkFaceNo(dto.getSourceImage());
        return new ResponseEntity<>(n, HttpStatus.OK);
    }
}
