package com.example.helloworld;


import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public String home(Locale locale, Model model){
        logger.info("locale is {}", locale);

        Date date = new Date();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formatteddata = dateFormat.format(date);

        model.addAttribute("serverTime", formatteddata);

        return "welcome";
    }
}
