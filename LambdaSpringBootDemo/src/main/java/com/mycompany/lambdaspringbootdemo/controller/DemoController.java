/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo.controller;

import com.mycompany.lambdaspringbootdemo.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author 20113
 */
@Controller
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Lambda Expression trong Java");
        model.addAttribute("basic", demoService.basicFunctionalInterfaces());
        model.addAttribute("optionalHello", demoService.optionalDemo("  Bảo Minh  "));
        model.addAttribute("composition", demoService.compositionDemo("  lambda rocks "));
        model.addAttribute("honorCount", demoService.countHonorStudentsParallel());
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("data", demoService.productStreamDemos());
        return "products";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", demoService.seedStudents());
        return "students";
    }

    @GetMapping("/collectors")
    public String collectors(Model model) {
        model.addAttribute("data", demoService.collectorsDemo());
        return "collectors";
    }

    @GetMapping("/async")
    public String async(Model model) {
        model.addAttribute("async", demoService.asyncDemo());
        return "async";
    }

    // Demo Optional từ input người dùng
    @PostMapping("/hello")
    public String hello(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("greeting", demoService.optionalDemo(name));
        return "index";
    }
}
