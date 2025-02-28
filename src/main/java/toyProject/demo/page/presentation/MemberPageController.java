package toyProject.demo.page.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberPageController {
    @GetMapping("/login")
    public String memberLogin() {
        return "memberLogin.html";
    }

    @GetMapping("/register")
    public String memberRegister() {
        return "memberRegister.html";
    }

    @GetMapping("/main")
    public String memberMain() {
        return "memberMain.html";
    }
}
