package com.Hasindu.Online.Food.Ordering.controller;

import com.Hasindu.Online.Food.Ordering.dto.RestaurentDto;
import com.Hasindu.Online.Food.Ordering.model.Restaurent;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.service.RestaurentService;
import com.Hasindu.Online.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/restaurants")
public class CustomerRestaurantController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurentService restaurentService;

    @GetMapping()
    public ResponseEntity<List<Restaurent>> findAllRestaurants(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurants = restaurentService.getAllRestaurant();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurent>> SearchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurent> restaurants = restaurentService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurent> findRestaurantById(
            @RequestHeader("Authoriation") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurentService.findRestaurantById(id);
        return new ResponseEntity<>(restaurent,HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorite")
    public ResponseEntity<RestaurentDto> addFavorites(         //if u used add favorite type, should be used ResponseDto return type
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        RestaurentDto restaurentDto = restaurentService.addToFavorites(id,user);

        return new ResponseEntity<>(restaurentDto,HttpStatus.OK);
    }
}
