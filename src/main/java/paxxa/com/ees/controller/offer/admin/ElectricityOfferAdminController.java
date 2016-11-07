package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.service.offerStorage.OfferStorageService;

import java.security.Principal;

@RequestMapping("/a")
@Controller
public class ElectricityOfferAdminController {

    public static final String REQUEST_SOURCE_TYPE = "requestSourceType";
    @Autowired
    private OfferStorageService offerStorageService;

    @RequestMapping("/offers")
    public String showOffers(Model model, Principal principal){
        model.addAttribute("offers", offerStorageService.getUserOffers(principal.getName()));
        model.addAttribute("countedOffers", offerStorageService.countUserOffers(principal.getName()));
        return "a-offers";
    }

    @RequestMapping(value = "/electricityOffer/remove/{id}")
    public String removeOffer(@PathVariable int id){
        offerStorageService.removeOffer(id);
        return "redirect:/a/offers.html";
    }

    @RequestMapping(value = "/electricityOffer/newOffer")
    public String createNewOffer(Model model){
        model.addAttribute(REQUEST_SOURCE_TYPE, "newOffer");
        return "a-electricityOffer-newOffer";
    }

    @RequestMapping(value = "/electricityOffer/editOffer/{id}")
    public String editOffer(Model model, @PathVariable int id){
        model.addAttribute(REQUEST_SOURCE_TYPE, "editOffer");
        model.addAttribute("offerIdForEdition", id);
        return "a-electricityOffer-editOffer";
    }




}
