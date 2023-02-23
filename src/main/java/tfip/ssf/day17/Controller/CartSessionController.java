package tfip.ssf.day17.Controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/session/cart")
public class CartSessionController {
    
    @PostMapping(path="/checkout", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postCheckout(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession session){
        String name = form.getFirst("name");
        String item = form.getFirst("item");
        List<String> cart = (List<String>)session.getAttribute("cart");
        cart.add(item);

        //Destroy my session, the next request will get a new session
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postMapping(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession session) {
        String name = form.getFirst("name");
        String item = form.getFirst("item");
        //Treat session like a map (dictionary)
        //Below line means get value from the key 'cart' in the Session map, then cast it into a list of strings
        List<String> cart = (List<String>)session.getAttribute("cart");
        if (null == cart) {
            //If cart is null, then start new session
            cart = new LinkedList<>();
            session.setAttribute("cart",cart);
            session.setAttribute("name", name);
        }
        cart.add(item);

        model.addAttribute("cart", cart);
        model.addAttribute("name", name);
        return "index";
    }


}
