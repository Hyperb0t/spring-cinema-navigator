package ru.itis.springcinemanavigator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmRecommendation {
    private String title;
    private Integer year;
    private String imdbID;
    private String type;
    private String poster;
}
