package seu.edu.bd.southeast_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/footer")
public class FooterSecController {
    @GetMapping
    public String getFooter() {
        return "get footer";
    }

    @PostMapping
    public String postFooter() {
        return "post footer";
    }

    @PutMapping
    public String putFooter() {
        return "put footer";
    }

    @DeleteMapping
    public String deleteFooter() {
        return "delete footer";
    }
}
