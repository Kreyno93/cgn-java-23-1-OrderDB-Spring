package de.neuefische.cgnjava231orderdbspring.repo;

import de.neuefische.cgnjava231orderdbspring.model.Order;
import de.neuefische.cgnjava231orderdbspring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepo {
    private final Map<String, List<Product>> orderMap = new HashMap<>(Map.of(
            "4821948021", List.of(new Product("1", "Milk"), new Product("2", "Bread"), new Product("3", "Eggs")),
            "4821948022", List.of(new Product("4", "Butter"), new Product("5", "Cheese"), new Product("6", "Yogurt"))));

    public Map<String, List<Product>> getOrderList() {
        return orderMap;
    }

    public Order addOrder(Order order) {
        orderMap.put(order.getId(), order.getProductList());
        return getOrderById(order.getId());
    }

    public Order getOrderById(String id) throws NoSuchElementException {
        return orderMap.entrySet().stream()
                .filter(order -> order.getKey().equals(id))
                .findFirst()
                .map(order -> new Order(order.getKey(), order.getValue()))
                .orElseThrow(() -> new NoSuchElementException("Order with id " + id + " not found"));
    }

    public Optional<Order> deleteOrder(String id) {
        Order deletedOrder = getOrderById(id);
        orderMap.remove(id);
        return Optional.ofNullable(deletedOrder);
    }

    public Order updateOrder(Order newOrder) {
        Optional<Order> deletedOrder = deleteOrder(newOrder.getId());
        return addOrder(newOrder);
    }

}
