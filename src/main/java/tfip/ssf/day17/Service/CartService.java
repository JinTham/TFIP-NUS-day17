package tfip.ssf.day17.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfip.ssf.day17.Repository.CartRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    
    public void addToCart(String name, List<String> cart){
        //Convert list into a CSV string first
        String items = cart.stream().collect(Collectors.joining(","));
        cartRepo.addToCart(name, items);
    }

    public List<String> getFromCart(String name) {
        Optional<String> opt = cartRepo.getFromCart(name);
        List<String> cart = new LinkedList<>();

        //If the box is empty, return an empty list
        if (opt.isEmpty()){
            return cart;
        }
        //Get the value from the box
        String value = opt.get();
        String[] items = value.split(",");
        for (int i=0;i<items.length;i++){
            cart.add(items[i]);
        }
        return cart;
    }
}
