package com.example.kopring4.kopring

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/ko")
class KoController{

    @GetMapping("/index")
    fun index(model: Model) : String {
        model.addAttribute("text", "강민승")

        return "index"
    }
}