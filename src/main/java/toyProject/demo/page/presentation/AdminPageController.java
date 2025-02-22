package toyProject.demo.page.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @GetMapping("/login")
    public String adminLogin() {
        return "/admin/adminLogin.html";
    }

    @GetMapping("/member")
    public String adminMember() {
        return "/admin/adminMember.html";
    }

    @GetMapping("/team")
    public String adminTeam() {
        return "/admin/adminTeam.html";
    }
}
