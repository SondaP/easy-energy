package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paxxa.com.ees.dto.PersonalData.PersonalDataDTO;
import paxxa.com.ees.service.personalData.PersonalDataService;
import paxxa.com.ees.service.user.UserService;

import java.security.Principal;

@RequestMapping("/a")
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

    @RequestMapping("/settings")
    public String settings(Model model, Principal principal){
        String name = principal.getName();
        return "a-settings";
    }


    @RequestMapping("/account")
    public String account(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findPersonalDataByUserName(userName));
        return "a-account";
    }

    @RequestMapping("/accountEdit")
    public String showAccountEditPersonalData(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("personalData", userService.findPersonalDataByUserName(userName));
        return "a-accountEdit";
    }

    @RequestMapping(value = "/accountEdit", method = RequestMethod.POST)
    public String updatePersonalData(@ModelAttribute("personalDataDTO") PersonalDataDTO personalDataDTO, Principal principal){
        personalDataService.addOrUpdatePersonalData(personalDataDTO, principal.getName());
        return "redirect:/a/account.html?success=true";
    }

    @RequestMapping("/users")
    public String getHierarchyUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.findAllHierarchyUsersForUser(principal.getName()));
        return "a-users";
    }

    @RequestMapping(value = "/users/remove/{id}")
    public String removeUser(@PathVariable int id) {
        userService.removeUser(id);
        return "redirect:/a/users.html";
    }





}
