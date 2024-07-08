package com.Hasindu.Online.Food.Ordering.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable   //that a class will be embedded by other entities.
public class RestaurentDto {

    private Long id;

    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;

}
