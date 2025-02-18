package toyProject.demo.admin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping("/adminLogin")
    public String adminLogin() {
        return "adminLogin.html";
    }

    @GetMapping("/adminMember")
    public String adminMember() {
        return "adminMember.html";
    }

    @GetMapping("/adminTeam")
    public String adminTeam() {
        return "adminTeam.html";
    }
}
