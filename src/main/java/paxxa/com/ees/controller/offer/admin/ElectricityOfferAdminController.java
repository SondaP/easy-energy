package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.service.offerStorage.OfferStorageService;

@Controller
public class ElectricityOfferAdminController {

    @Autowired
    private OfferStorageService offerStorageService;

    @RequestMapping(value = "/a/electricityOffer/remove/{id}")
    public String removeOffer(@PathVariable int id){
        offerStorageService.removeOffer(id);
        return "redirect:/a/offers.html";
    }

    @RequestMapping(value = "/a/electricityOffer/newOffer")
    public String createNewOffer(){

        return "a-electricityOffer-newOffer";
    }


}
