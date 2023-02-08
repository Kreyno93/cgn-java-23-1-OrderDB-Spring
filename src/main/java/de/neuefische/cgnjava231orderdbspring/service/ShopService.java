package de.neuefische.cgnjava231orderdbspring.service;

import de.neuefische.cgnjava231orderdbspring.exceptions.NoOrderWithIdException;
import de.neuefische.cgnjava231orderdbspring.exceptions.NoProductInOrderListException;
import de.neuefische.cgnjava231orderdbspring.model.Order;
import de.neuefische.cgnjava231orderdbspring.model.Product;
import de.neuefische.cgnjava231orderdbspring.repo.OrderRepo;
import de.neuefische.cgnjava231orderdbspring.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final GenerateUUID generateUUID;

    public Optional<Product> getProductById(String id) {
        return productRepo.getProductById(id);
    }

    public List<Product> getProductList() {
        return productRepo.getProductList();
    }

    public Map<String, List<Product>> getOrderList() {
        return orderRepo.getOrderList();
    }

    public Order addOrder(List<String> productIds) {
        List<Product> productList = new ArrayList<>();
        productIds.stream()
                .map(this::getProductById)
                .forEach(product -> product.ifPresent(productList::add));
        if (!productList.isEmpty()) {
            Order newOrder = new Order(generateUUID.generateUUID(), productList);
            return orderRepo.addOrder(newOrder);
        }
        throw new NoProductInOrderListException("No products in Order");
    }

    public Optional<Order> getOrderById(String id) {
        return Optional.ofNullable(orderRepo.getOrderById(id));
    }

    public Optional<Order> deleteOrder(String id) {
        return orderRepo.deleteOrder(id);
    }

    public Order updateOrder(String id, List<String> productIds) {
        if (orderRepo.getOrderById(id) != null) {
            Order orderToUpdate = orderRepo.getOrderById(id);
            List<Product> productList = new ArrayList<>(orderRepo.getOrderById(id).getProductList());
            productIds.stream()
                    .map(this::getProductById)
                    .forEach(product -> product.ifPresent(productList::add));
            orderToUpdate.setProductList(productList);
            return orderRepo.updateOrder(orderToUpdate);
        }throw new NoOrderWithIdException("No order with id: " + id);
    }
}
