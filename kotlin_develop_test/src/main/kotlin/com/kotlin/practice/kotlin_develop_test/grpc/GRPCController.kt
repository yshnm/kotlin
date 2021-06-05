package com.kotlin.practice.kotlin_develop_test.grpc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GRPCController {

    @GetMapping("/grpccontroller")
    fun grpc () : String {

        return "grpc"
    }
}