/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo.service;

import com.mycompany.lambdaspringbootdemo.model.Product;
import com.mycompany.lambdaspringbootdemo.model.Student;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 *
 * @author 20113
 */
@Service
public class DemoService {

    // Dữ liệu mẫu
    public List<Product> seedProducts() {
        return List.of(
                new Product(1, "MacBook Air", "Laptop", new BigDecimal("999.99"), true),
                new Product(2, "ThinkPad X1", "Laptop", new BigDecimal("1399.00"), false),
                new Product(3, "iPhone 15", "Phone", new BigDecimal("1099.00"), true),
                new Product(4, "Galaxy S24", "Phone", new BigDecimal("999.00"), true),
                new Product(5, "iPad", "Tablet", new BigDecimal("599.00"), false),
                new Product(6, "Kindle", "Tablet", new BigDecimal("129.00"), true)
        );
    }

    public List<Student> seedStudents() {
        return List.of(
                new Student(1, "An", 3.2, "CS", 3),
                new Student(2, "Bình", 3.9, "DS", 4),
                new Student(3, "Chi", 2.8, "CS", 2),
                new Student(4, "Dung", 3.6, "SE", 3),
                new Student(5, "Hà", 3.95, "DS", 4)
        );
    }

    // Predicate, Function, Consumer, Supplier
    public Map<String, Object> basicFunctionalInterfaces() {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> strLen = String::length; // method reference
        Consumer<String> logger = s -> {}; // ví dụ: System.out::println (giữ UI sạch)
        Supplier<String> idSupplier = () -> UUID.randomUUID().toString();

        Map<String, Object> demo = new LinkedHashMap<>();
        demo.put("isEven(10)", isEven.test(10));
        demo.put("length('lambda')", strLen.apply("lambda"));
        logger.accept("demo log");
        demo.put("newId", idSupplier.get());
        return demo;
    }

    // Stream map/filter/reduce
    public Map<String, Object> productStreamDemos() {
        var products = seedProducts();

        var laptopsSortedByPrice = products.stream()
                .filter(p -> p.category().equals("Laptop"))
                .sorted(Comparator.comparing(Product::price))
                .toList();

        var totalInStock = products.stream()
                .filter(Product::inStock)
                .count();

        var sumPhonePrice = products.stream()
                .filter(p -> p.category().equals("Phone"))
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var namesUpper = products.stream()
                .map(Product::name)
                .map(String::toUpperCase)
                .toList();

        Map<String, Object> demo = new LinkedHashMap<>();
        demo.put("laptopsSortedByPrice", laptopsSortedByPrice);
        demo.put("totalInStock", totalInStock);
        demo.put("sumPhonePrice", sumPhonePrice);
        demo.put("namesUpper", namesUpper);
        return demo;
    }

    // Collectors groupingBy/partitioningBy
    public Map<String, Object> collectorsDemo() {
        var products = seedProducts();

        var byCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category));

        var partitionByStock = products.stream()
                .collect(Collectors.partitioningBy(Product::inStock));

        var avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.mapping(Product::price,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .mapToDouble(p -> p.doubleValue())
                                                .average().orElse(0)))));

        Map<String, Object> demo = new LinkedHashMap<>();
        demo.put("groupingByCategory", byCategory);
        demo.put("partitioningByInStock", partitionByStock);
        demo.put("avgPriceByCategory", avgPriceByCategory);
        return demo;
    }

    // Optional + xử lý null an toàn
    public String optionalDemo(String maybeName) {
        return Optional.ofNullable(maybeName)
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(s -> "Hello, " + s)
                .orElse("Hello, Anonymous");
    }

    // Composition & higher-order
    public String compositionDemo(String input) {
        Function<String, String> trim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> exclaim = s -> s + "!";
        return trim.andThen(toUpper).andThen(exclaim).apply(input);
    }

    // Lambda + ngoại lệ (bọc checked -> unchecked)
    public List<Integer> parseIntsSafe(List<String> raw) {
        return raw.stream()
                .map(LambdaUtils.rethrow(Integer::parseInt))
                .toList();
    }

    // Parallel stream (demo, thận trọng hiệu năng)
    public long countHonorStudentsParallel() {
        return seedStudents().parallelStream()
                .filter(s -> s.gpa() >= 3.6)
                .count();
    }

    // CompletableFuture + lambda
    public Map<String, Object> asyncDemo() {
        var start = System.nanoTime();
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> simulateCompute(200));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> simulateCompute(300));

        Integer total = f1.thenCombine(f2, Integer::sum).join();
        var durationMs = Duration.ofNanos(System.nanoTime() - start).toMillis();

        Map<String, Object> demo = new LinkedHashMap<>();
        demo.put("f1+f2", total);
        demo.put("durationMs", durationMs);
        return demo;
    }

    private int simulateCompute(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return 1;
    }
}
