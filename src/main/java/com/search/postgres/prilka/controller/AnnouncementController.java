package com.search.postgres.prilka.controller;

import com.search.postgres.prilka.response.AnnouncementResponse;
import com.search.postgres.prilka.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/search")
    public ResponseEntity<List<AnnouncementResponse>> searchAnnouncements(
            @RequestParam String query) {
        List<AnnouncementResponse> results = announcementService.searchAnnouncements(query);
        return ResponseEntity.ok(results);
    }
}
