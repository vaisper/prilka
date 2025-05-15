package com.search.postgres.prilka.service;

import com.search.postgres.prilka.persistence.AnnouncementEntity;
import com.search.postgres.prilka.persistence.AnnouncementRepository;
import com.search.postgres.prilka.response.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<AnnouncementResponse> searchAnnouncements(String query) {
        return announcementRepository.searchByTrigram(query).stream()
                .map(this::mapToAnnouncementResponse)
                .collect(Collectors.toList());
    }

    private AnnouncementResponse mapToAnnouncementResponse(Object[] result) {
        AnnouncementEntity entity = (AnnouncementEntity) result[0];
        Double similarityScore = (Double) result[1];

        return AnnouncementResponse.builder()
            .announcementId(entity.getAnnouncementId())
            .situationDescription(entity.getSituationDescription())
            .similarityScore(similarityScore)
            .build();
    }
}
