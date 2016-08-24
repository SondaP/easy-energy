package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.service.offerStorage.OfferStorageService;

import java.security.Principal;

@RequestMapping("/a")
@Controller
public class OfferAdminController {

    @Autowired
    private OfferStorageService offerStorageService;

    @RequestMapping("/offers")
    public String showOffers(Model model, Principal principal){
        model.addAttribute("offers", offerStorageService.getUserOffers(principal.getName()));
        return "a-offers";
    }
}
