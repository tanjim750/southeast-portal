package seu.edu.bd.southeast_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FooterSecController {
    @GetMapping("/footer")
    public String getFooter() {
        return "footer";
    }
}
