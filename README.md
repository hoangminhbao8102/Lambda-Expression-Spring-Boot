# Lambda-Expression-Spring-Boot

Dự án web minh họa Lambda/Stream/Optional/Collectors/CompletableFuture trong **Java 17** với **Spring Boot 3.3.x** và **Thymeleaf**.

## Nội dung chính
- Functional interfaces: `Predicate`, `Function`, `Consumer`, `Supplier`
- Method reference, Comparator, map/filter/reduce
- `Collectors`: `groupingBy`, `partitioningBy`, tính trung bình
- `Optional` & xử lý null an toàn
- Composition (andThen), Higher-order function
- Xử lý ngoại lệ checked trong lambda
- Parallel stream (minh họa) & `CompletableFuture` (async)

## Yêu cầu
- JDK 17+
- Maven 3.9+
- (Tuỳ chọn) IDE: IntelliJ / VS Code / NetBeans

## Cách chạy
```bash
mvn clean spring-boot:run
```
hoặc
```bash
mvn -q package && java -jar target/lambda-spring-boot-demo-1.0.0.jar
```

## Truy cập:
- Trang chủ: http://localhost:8080/
- Products: http://localhost:8080/products
- Collectors: http://localhost:8080/collectors
- Students: http://localhost:8080/students
- Async: http://localhost:8080/async

## Ghi chú sư phạm
- Bắt đầu từ `/` để xem ví dụ căn bản.
- Mở `DemoService` để đọc từng nhóm ví dụ kèm chú thích.
- Nhấn mạnh method reference vs lambda và immutability khi dùng `stream`.
- Cảnh báo về hiệu năng: chỉ dùng `parallelStream` khi workload phù hợp.

## License
MIT

---
