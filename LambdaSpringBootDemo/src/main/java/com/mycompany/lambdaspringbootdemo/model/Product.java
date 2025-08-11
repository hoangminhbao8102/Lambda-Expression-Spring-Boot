/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo.model;

import java.math.BigDecimal;

/**
 *
 * @author 20113
 */
public record Product (
        long id,
        String name,
        String category,
        BigDecimal price,
        boolean inStock
) {}
