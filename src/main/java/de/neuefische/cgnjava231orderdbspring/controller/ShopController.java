package de.neuefische.cgnjava231orderdbspring.controller;

import de.neuefische.cgnjava231orderdbspring.model.Order;
import de.neuefische.cgnjava231orderdbspring.model.Product;
import de.neuefische.cgnjava231orderdbspring.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/get-products")
    public List<Product> getProductList() {
        return shopService.getProductList();
    }

    @GetMapping("/get-product-by-id/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return shopService.getProductById(id);
    }

    @GetMapping("/get-orders")
    public Map<String, List<Product>> getOrderList() {
        return shopService.getOrderList();
    }

    @GetMapping("/get-order-by-id/{id}")
    public Optional<Order> getOrderById(@PathVariable String id) {
        return shopService.getOrderById(id);
    }

    @PostMapping("/add-order")
    public Order addOrder(@RequestBody List<String> productIds) {
        return shopService.addOrder(productIds);
    }

    @DeleteMapping("/delete-order/{id}")
    public Optional<Order> deleteOrder(@PathVariable String id) {
        return shopService.deleteOrder(id);
    }

    @PutMapping("/update-order/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody List<String> productIds) {
        return shopService.updateOrder(id, productIds);
    }

}
