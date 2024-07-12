package com.Hasindu.Online.Food.Ordering.service;

import com.Hasindu.Online.Food.Ordering.dto.RestaurentDto;
import com.Hasindu.Online.Food.Ordering.model.Address;
import com.Hasindu.Online.Food.Ordering.model.Restaurent;
import com.Hasindu.Online.Food.Ordering.model.User;
import com.Hasindu.Online.Food.Ordering.repository.AddressRepository;
import com.Hasindu.Online.Food.Ordering.repository.RestaurentRepository;
import com.Hasindu.Online.Food.Ordering.repository.UserRepository;
import com.Hasindu.Online.Food.Ordering.request.CreateRetaurentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurentServiceImpl implements RestaurentService{
//    @Autowired
//    private RestaurentRepository restaurentRepository;
//
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private UserRepository userRepository;

    //this is a field injection. so we can used constructor injection without this field injection

    private final RestaurentRepository restaurentRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public RestaurentServiceImpl(RestaurentRepository restaurentRepository,AddressRepository addressRepository,UserRepository userRepository){
        this.restaurentRepository = restaurentRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Restaurent createRestaurant(CreateRetaurentRequest createRetaurentRequest, User user) {

        Address address = addressRepository.save(createRetaurentRequest.getAddress());

        Restaurent restaurent = new Restaurent();
        restaurent.setName(createRetaurentRequest.getName());
        restaurent.setOwner(user);
        restaurent.setDescription(createRetaurentRequest.getDescription());
        restaurent.setCuisineType(createRetaurentRequest.getCuisineType());
        restaurent.setAddress(address);
        restaurent.setContactInformation(createRetaurentRequest.getContactInformation());
        restaurent.setOpeningHours(createRetaurentRequest.getOpeningHours());
        restaurent.setImages(createRetaurentRequest.getImages());
        restaurent.setRegistrationDate(LocalDateTime.now());

        return restaurentRepository.save(restaurent);
    }

    @Override
    public Restaurent updateRestaurant(Long restaurantId, CreateRetaurentRequest updateRestaurantRequest) throws Exception {

        Restaurent restaurent = findRestaurantById(restaurantId);

        //no need to add exception here. bcz findRestaurantById return the exception
//        if(restaurent==null){
//            throw  new Exception("Restaurant not found");
//        }

        if (restaurent.getCuisineType()!=null){
            restaurent.setCuisineType(updateRestaurantRequest.getCuisineType());
        }

        if(restaurent.getDescription()!=null){
            restaurent.setDescription(updateRestaurantRequest.getDescription());
        }

        if(restaurent.getName()!=null){
            restaurent.setName(updateRestaurantRequest.getName());
        }
        return restaurentRepository.save(restaurent);
    }

    @Override
    public Restaurent findRestaurantById(Long restaurantId) throws Exception {

        Optional<Restaurent> restaurent = restaurentRepository.findById(restaurantId);
        if(restaurent.isEmpty()){
            throw new Exception("Restaurant not found with id "+ restaurantId);
        }
        return restaurent.get();
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurent restaurent = findRestaurantById(restaurantId);
        restaurentRepository.delete(restaurent);
    }

    @Override
    public List<Restaurent> getAllRestaurant() {

        return restaurentRepository.findAll();
    }

    @Override
    public List<Restaurent> searchRestaurant(String keyword) {
        return restaurentRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurent getRestaurantByUserId(Long userId) throws Exception {
        Restaurent restaurent = restaurentRepository.findByOwnerId(userId);

        if(restaurent==null){
            throw new Exception("Restaurant not found this user id "+ userId);
        }
        return restaurent;
    }

    @Override
    public RestaurentDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurent restaurent = findRestaurantById(restaurantId);

        RestaurentDto restaurentDto = new RestaurentDto();
        restaurentDto.setId(restaurent.getId());
        restaurentDto.setTitle(restaurent.getName());
        restaurentDto.setDescription(restaurent.getDescription());
        restaurentDto.setImages(restaurent.getImages());

        boolean isFavorited = false;
        List<RestaurentDto> favorites = user.getFavorites();
        for (RestaurentDto favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }

        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(restaurentDto);
        }


        userRepository.save(user);
        return restaurentDto;
    }

    @Override
    public Restaurent updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurent restaurent = findRestaurantById(restaurantId);

        restaurent.setOpen(!restaurent.isOpen());    //restaurent.isOpen() checks the current open status of the restaurant.  ! = if true change false  if false change true
        return restaurentRepository.save(restaurent);
    }
}
