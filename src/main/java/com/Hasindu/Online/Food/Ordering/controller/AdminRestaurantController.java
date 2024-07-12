package com.Hasindu.Online.Food.Ordering.controller;

import com.Hasindu.Online.Food.Ordering.model.Restaurent;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.reponse.MessageResponse;
import com.Hasindu.Online.Food.Ordering.request.CreateRetaurentRequest;
import com.Hasindu.Online.Food.Ordering.service.RestaurentService;
import com.Hasindu.Online.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurentService restaurentService;

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<Restaurent> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Restaurent restaurent = restaurentService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurent, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Restaurent> createRestaurant(
            @RequestBody CreateRetaurentRequest createRetaurentRequest,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurent restaurent = restaurentService.createRestaurant(createRetaurentRequest,user);

        return new ResponseEntity<>(restaurent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurent> updateRestaurant(
            @RequestBody CreateRetaurentRequest createRetaurentRequest,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Restaurent restaurent = restaurentService.updateRestaurant(id,createRetaurentRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        restaurentService.deleteRestaurant(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfully...");

        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurent> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurent restaurent = restaurentService.updateRestaurantStatus(id);

        return new ResponseEntity<Restaurent>(restaurent, HttpStatus.OK);
    }
}
