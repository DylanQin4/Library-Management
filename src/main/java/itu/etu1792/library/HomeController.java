package itu.etu1792.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // redirect to books
    @GetMapping("")
    public String goToBook() {
        return "redirect:/books";
    }
}
