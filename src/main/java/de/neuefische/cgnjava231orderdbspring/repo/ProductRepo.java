package de.neuefische.cgnjava231orderdbspring.repo;

import de.neuefische.cgnjava231orderdbspring.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepo {
    private final List<Product> productList = new ArrayList<>(
            List.of(
                    new Product("1","Milk"),
                    new Product("2","Bread"),
                    new Product("3","Eggs"),
                    new Product("4","Butter"),
                    new Product("5","Cheese"),
                    new Product("6","Yogurt"),
                    new Product("7","Sausage"),
                    new Product("8","Ham"),
                    new Product("9","Chicken")
            )
    );

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Optional<Product> getProductById(String id) throws NoSuchElementException {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
}


