package tn.poste.myship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Subscribed {
    @GetMapping(value = "subscribed")
    public String subscribed(Model model){
        return "subscribed";
    }
}
