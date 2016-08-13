package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paxxa.com.ees.dto.PersonalData.PersonalDataDTO;
import paxxa.com.ees.service.personalData.PersonalDataService;
import paxxa.com.ees.service.user.UserService;

import java.security.Principal;

@RequestMapping("/t")
@Controller
public class TraderController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonalDataService personalDataService;

    @ModelAttribute("personalDataDTO")
    public PersonalDataDTO constructPersonalDataDTO() {
        return new PersonalDataDTO();
    }

    @RequestMapping("/account")
    public String account(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findPersonalDataByUserName(userName));
        return "t-account";
    }

    @RequestMapping("/accountEdit")
    public String showAccountEditPersonalData(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findPersonalDataByUserName(userName));
        return "t-accountEdit";
    }

    @RequestMapping(value = "/accountEdit", method = RequestMethod.POST)
    public String updatePersonalData(@ModelAttribute("personalDataDTO") PersonalDataDTO personalDataDTO, Principal principal){
        personalDataService.addOrUpdatePersonalData(personalDataDTO, principal.getName());
        return "redirect:/t/account.html?success=true";
    }
}
