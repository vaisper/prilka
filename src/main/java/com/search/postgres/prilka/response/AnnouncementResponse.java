package com.search.postgres.prilka.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AnnouncementResponse {

    private UUID announcementId;
    private String situationDescription;
    private Double similarityScore;


}
