package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


// 컨트롤러에서 리턴값으로 문자를 반환하면 뷰리졸버(ViewResolver)가 화면을 찾아서 처리한다.
// 뷰리졸버는 기본적으로 templates/에서 .html 파일을 찾도록 설정되어있다.
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ // model( data : "Hello World" )
        model.addAttribute("data", "Hello World");
        return "hello"; // templates의 같은 이름으로 넘어가라는 의미
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 이 어노테이션이 있으면 뷰리졸버가 아닌 바로 리턴하는 것.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // 만약 객체가 온다면 json형식(default)으로 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello; // 객체를 반환

    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
