/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo.model;

/**
 *
 * @author 20113
 */
public record Student(
        long id,
        String name,
        double gpa,
        String major,
        int year
) {}
