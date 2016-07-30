package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paxxa.com.ees.dto.PersonalDataDTO;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.service.personalData.PersonalDataService;
import paxxa.com.ees.service.user.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonalDataService personalDataService;

    @ModelAttribute("personalDataDTO")
    public PersonalDataDTO constructPersonalDataDTO() {
        return new PersonalDataDTO();
    }

    @RequestMapping("/a/settings")
    public String settings(Model model, Principal principal){
        String name = principal.getName();
        return "a-settings";
    }


    @RequestMapping("/a/account")
    public String account(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findByUserName(userName));
        return "a-account";
    }

    @RequestMapping("/a/accountEdit")
    public String showAccountEditPersonalData(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findByUserName(userName));
        return "a-accountEdit";
    }

    @RequestMapping(value = "/a/accountEdit", method = RequestMethod.POST)
    public String updatePersonalData(@ModelAttribute("personalDataDTO") PersonalDataDTO personalDataDTO, Principal principal){
        personalDataService.addOrUpdatePersonalData(personalDataDTO, principal.getName());
        return "redirect:/a/account.html?success=true";
    }





}
