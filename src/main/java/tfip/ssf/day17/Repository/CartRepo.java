package tfip.ssf.day17.Repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepo {
    @Autowired @Qualifier("my-redis")
    private RedisTemplate<String,String> template;

    public void addToCart(String name, String cart) {
        template.opsForValue().set(name,cart);
    }
    
    public Optional<String> getFromCart(String name) {
        String value = template.opsForValue().get(name);
        //If the value is empty, return an empty box
        if (null == value) {
            return Optional.empty();
        }
        //Fill the box and return the box
        return Optional.of(value);
    }
}
