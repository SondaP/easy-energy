package paxxa.com.ees.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.repository.seller.SellerRepository;

@RequestMapping("/a")
@Controller
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @RequestMapping("/sellers")
    public String showSellers(Model model){
        model.addAttribute("sellers", sellerRepository.findAll());
        return "a-sellers";
    }
}
