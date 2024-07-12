package com.Hasindu.Online.Food.Ordering.repository;

import com.Hasindu.Online.Food.Ordering.model.Restaurent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurentRepository extends JpaRepository<Restaurent, Long> {
//    SELECT r FROM Restaurant r: This part of the query selects all records from the Restaurant entity, represented by the alias r.
//    WHERE Clause:
//
//    WHERE lower(r.name) LIKE lower(concat('%', :query, '%')):
//    lower(r.name): Converts the name field of the Restaurant entity to lowercase.
//    LIKE lower(concat('%', :query, '%')):
//    lower(concat('%', :query, '%')): Concatenates the wildcard % before and after the query parameter and converts the result to lowercase.
//    LIKE is used to match the name field with the concatenated query string.
//    This part of the query will match any restaurant name that contains the search query, case-insensitive.

    @Query("SELECT r FROM Restaurent r WHERE lower(r.name) LIKE lower(concat('%', :query , '%'))" +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<Restaurent> findBySearchQuery(String query);

    Restaurent findByOwnerId(Long userId);
}
