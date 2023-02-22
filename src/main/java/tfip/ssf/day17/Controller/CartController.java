package tfip.ssf.day17.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import tfip.ssf.day17.Service.CartService;

@Controller
@RequestMapping(path="/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postCart(@RequestBody MultiValueMap<String,String> form, Model model) {
        //Get newly added item
        String item = form.getFirst("item");
        String name = form.getFirst("name");
        //Get the cart from the database
        List<String> cart = cartService.getFromCart(name);
        cart.add(item);
        //Save the cart back to database after adding new item into the cart
        cartService.addToCart(name, cart);
        //Render the view
        model.addAttribute("cart", cart);
        model.addAttribute("name", name);
        return "index";
    }
}
