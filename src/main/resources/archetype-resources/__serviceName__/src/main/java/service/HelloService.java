package ${package}.service;

import ${package}.dto.HelloDTO;

public class HelloService {

    public HelloDTO hello() {
        return new HelloDTO("Hello from service!");
    }
}
