package com.Hasindu.Online.Food.Ordering.request;

import com.Hasindu.Online.Food.Ordering.model.Address;
import com.Hasindu.Online.Food.Ordering.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRetaurentRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
