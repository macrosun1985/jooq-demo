package me.sunhong.demo.jooq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.sunhong.demo.jooq.service.BookService;
import me.sunhong.demo.jooq.service.LanguageService;

@Controller
public class IndexController {
	
	@Autowired 
	BookService bookService;
	
	@Autowired
	LanguageService languageService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
		
		try {
        	languageService.create(5, "tt", "");
        	bookService.create(5, 1, "Book 5");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
		return "index";
    }
	
}