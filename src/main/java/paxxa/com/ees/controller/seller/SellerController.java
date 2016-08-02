package paxxa.com.ees.controller.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/a")
@Controller
public class SellerController {

    @RequestMapping("/sellers")
    public String showSellers(){
        
        return "a-sellers";
    }
}
