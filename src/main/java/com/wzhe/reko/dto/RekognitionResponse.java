package com.wzhe.reko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.services.rekognition.model.FaceDetail;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RekognitionResponse {

    private List<FaceDetail> faceDetails = new ArrayList<>();
}
