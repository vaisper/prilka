package com.search.postgres.prilka.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {

    /**
     * Триграммный поиск по полю situation_description с использованием similarity.
     *
     * @param query Искомый текст
     * @return Список массивов объектов: [AnnouncementEntity, Double (similarity_score)]
     */
@Query(value = "SET pg_trgm.similarity_threshold = 0; " +
        "SELECT a.*, similarity(lower(a.situation_description), lower(:query)) AS similarity_score " +
        "FROM announcement a " +
        "WHERE lower(a.situation_description) % lower(:query) " +
        "ORDER BY similarity_score DESC",
        nativeQuery = true)
List<Object[]> searchByTrigram(@Param("query") String query);

    @Query(value = """
            SET pg_trgm.similarity_threshold = 0;
            SELECT situation_description, similarity(situation_description, 'по') AS similarity_score
            FROM public.announcement
            WHERE situation_description % 'по'
            ORDER BY similarity_score DESC;
            """,
            nativeQuery = true)
    List<Object[]> search();

        @Query(value = """
            Select * FROM public.announcement
            """,
            nativeQuery = true)
    List<Object[]> searchAll();

}
