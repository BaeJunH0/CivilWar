package toyProject.demo.page.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @GetMapping("/login")
    public String adminLogin() {
        return "adminLogin.html";
    }

    @GetMapping("/member")
    public String adminMember() {
        return "adminMember.html";
    }

    @GetMapping("/team")
    public String adminTeam() {
        return "adminTeam.html";
    }
}
