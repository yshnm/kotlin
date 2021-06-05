package com.kotlin.practice.kotlin_develop_test.hello

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/")
    fun hello () : String{
        return "hello"
    }

}

