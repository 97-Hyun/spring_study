package com.hyun.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody // 리턴한 것 그대로 리턴하는 어노테이션
    public String sayHello() {
        // 이 코드에서 에러가 나온다 이유는 스프링 MVC는 문자열을 리턴할 때 그 이름으로 된 뷰를 검색 후 리턴하기 때문이다.
        // 문자열을 그대로 리턴하지 않는다
        // 뷰를 찾지 못해 에러 반납
        // @ResponseBody 을 사용해서 해결 가능
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        return "<html><head><title>My first HTML Page</title></head><body>My first html page with body</body></html>";
    }

    // jsp 을 사용하기 위해 ResponseBody 어노테이션 제거
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
