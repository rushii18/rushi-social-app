package com.rushi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
	
    @GetMapping()
   public String homecontrollerHandler()
	{
		return "rushigulve";
	}

    
    @GetMapping("/home")
    public String homecontrollerHandler2()
   	{
   		return "rushigulve";
   	}
   	
    
}
