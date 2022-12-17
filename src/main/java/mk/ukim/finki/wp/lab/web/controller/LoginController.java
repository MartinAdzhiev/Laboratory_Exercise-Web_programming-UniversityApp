package mk.ukim.finki.wp.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("login")
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @GetMapping("access_denied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }

}


