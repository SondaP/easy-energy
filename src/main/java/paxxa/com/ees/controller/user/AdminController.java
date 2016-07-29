package paxxa.com.ees.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {


    @RequestMapping("/a/account")
    public String account(Model model, Principal principal){
        String name = principal.getName();
        return "a-account";
    }

    @RequestMapping("/a/settings")
    public String settings(Model model, Principal principal){
        String name = principal.getName();
        return "a-settings";
    }


}
