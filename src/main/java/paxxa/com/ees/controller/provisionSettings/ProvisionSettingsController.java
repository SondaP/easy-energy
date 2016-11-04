package paxxa.com.ees.controller.provisionSettings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.service.user.UserService;

import java.security.Principal;

@RequestMapping("/a/provision")
@Controller
public class ProvisionSettingsController {


    @Autowired
    private UserService userService;


    @RequestMapping("/users")
    public String getHierarchyUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.findAllHierarchyUsersForUser(principal.getName()));
        return "a-provision-users";
    }


}
