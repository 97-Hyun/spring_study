package com.hyun.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

//    @Autowired
//    private AuthenticationService authenticationService;

    // ModelMap 은 View 단에 컨트롤러에서 데이터를 넘기기 위한 방법이다.
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoginUserName());
        return "welcome";
    }

    private String getLoginUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

//    @RequestMapping(value= "login", method = RequestMethod.POST)
//    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//        if (authenticationService.authenticate(name, password)) {
//            model.put("name", name);
//            model.put("password", password);
//
//            return "welcome";
//        }
//
//        return "login";
//    }
}
