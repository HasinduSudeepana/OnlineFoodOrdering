package com.Hasindu.Online.Food.Ordering.model;

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
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Restaurent restaurent;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<IngredientsItem> ingredients = new ArrayList<>();
}
