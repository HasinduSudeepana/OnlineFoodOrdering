package com.Hasindu.Online.Food.Ordering.service;

import com.Hasindu.Online.Food.Ordering.dto.RestaurentDto;
import com.Hasindu.Online.Food.Ordering.model.Restaurent;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.repository.CartRepository;
import com.Hasindu.Online.Food.Ordering.request.CreateRetaurentRequest;

import java.util.List;

public interface RestaurentService {

    Restaurent createRestaurant(CreateRetaurentRequest createRetaurentRequest, User user);

    Restaurent updateRestaurant(Long restaurantId, CreateRetaurentRequest updateRestaurantRequest) throws Exception;

    Restaurent findRestaurantById(Long restaurantId) throws Exception;

    void deleteRestaurant(Long restaurantId) throws Exception;

    List<Restaurent> getAllRestaurant();

    List<Restaurent> searchRestaurant(String keyword);

    Restaurent getRestaurantByUserId(Long userId) throws Exception;

    RestaurentDto addToFavorites(Long restaurantId, User user) throws Exception;

    Restaurent updateRestaurantStatus(Long restaurantId) throws Exception;


}
