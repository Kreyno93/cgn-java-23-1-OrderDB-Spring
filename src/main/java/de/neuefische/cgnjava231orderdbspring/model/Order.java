package de.neuefische.cgnjava231orderdbspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private List<Product> productList;
}
