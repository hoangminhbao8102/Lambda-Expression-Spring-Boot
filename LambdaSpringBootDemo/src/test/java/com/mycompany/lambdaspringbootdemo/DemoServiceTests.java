/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo;

import com.mycompany.lambdaspringbootdemo.service.DemoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 *
 * @author 20113
 */
public class DemoServiceTests {

    private final DemoService svc = new DemoService();

    @Test
    void testParseIntsSafe() {
        var out = svc.parseIntsSafe(List.of("1","2","3"));
        assertEquals(List.of(1,2,3), out);
    }

    @Test
    void testOptionalDemo() {
        assertEquals("Hello, Anonymous", svc.optionalDemo(null));
        assertEquals("Hello, Minh", svc.optionalDemo("  Minh "));
    }
}
