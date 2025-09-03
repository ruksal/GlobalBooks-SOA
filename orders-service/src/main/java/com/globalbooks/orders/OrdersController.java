package com.globalbooks.orders.controller;

import com.globalbooks.orders.model.Order;
import com.globalbooks.orders.model.OrderCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rest/orders/v1")
public class OrdersController {

    // In-memory store for demo. Replace with DB in production.
    private final Map<String, Order> store = new HashMap<>();

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreate req) {
        String id = "ord_" + UUID.randomUUID().toString().substring(0,8);
        double total = req.getItems().stream().mapToDouble(i -> i.getQuantity() * i.getUnitPrice()).sum();
        Order o = new Order();
        o.setId(id);
        o.setStatus("PENDING_PAYMENT");
        o.setTotal(total);
        o.setCurrency(req.getItems().get(0).getCurrency());
        o.setCreatedAt(Instant.now().toString());
        store.put(id, o);
        // In real app: publish order.created to RabbitMQ (see payments module)
        return ResponseEntity.status(201).body(o);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        Order o = store.get(id);
        if (o == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(o);
    }
}
