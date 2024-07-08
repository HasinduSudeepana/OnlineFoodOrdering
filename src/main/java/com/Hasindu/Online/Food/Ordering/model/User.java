package com.Hasindu.Online.Food.Ordering.model;

import com.Hasindu.Online.Food.Ordering.dto.RestaurentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String password;

    private String email;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany( mappedBy = "customer",cascade = CascadeType.ALL)    //this customer should be same name in Order entity's User type name
    @JsonIgnore   // i dont need fetch order details, when i fetch user details
    private List<Order> orders = new ArrayList<>();

    @ElementCollection    //This annotation will be applied when there is a relation with non-entity
    private List<RestaurentDto> favorites = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses =  new ArrayList<>();

}
