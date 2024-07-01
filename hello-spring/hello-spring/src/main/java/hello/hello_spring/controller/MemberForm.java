package hello.hello_spring.controller;

public class MemberForm {
    private String name; // html에서 name이 "name"인 input박스의 데이터

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
