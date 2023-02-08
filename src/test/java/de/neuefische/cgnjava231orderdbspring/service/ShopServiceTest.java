package de.neuefische.cgnjava231orderdbspring.service;

import de.neuefische.cgnjava231orderdbspring.exceptions.NoOrderWithIdException;
import de.neuefische.cgnjava231orderdbspring.model.Order;
import de.neuefische.cgnjava231orderdbspring.model.Product;
import de.neuefische.cgnjava231orderdbspring.repo.OrderRepo;
import de.neuefische.cgnjava231orderdbspring.repo.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    @Test
    void WhenGetProductList_ThenReturnList() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(productRepo.getProductList()).thenReturn(List.of(
                new Product("1", "Brot")
        ));

        List<Product> actual = shopService.getProductList();

        List<Product> expected = List.of(
                new Product("1", "Brot")
        );

        assertEquals(expected, actual);
    }


    @Test
    void whenGettingProductById_ThenReturnProduct() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(productRepo.getProductById("1")).thenReturn(Optional.of(new Product("1", "Brot")));

        Product actual = shopService.getProductById("1").get();

        Product expected = new Product("1", "Brot");

        assertEquals(expected, actual);
    }

//    @Test
//    void whenGettingProductByIdDoesNotExist_ThenReturnEmptyOptional() {
//        OrderRepo orderRepo = mock(OrderRepo.class);
//        ProductRepo productRepo = mock(ProductRepo.class);
//        GenerateUUID generateUUID = mock(GenerateUUID.class);
//
//        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);
//
//        when(productRepo.getProductById("1")).thenReturn(null);
//
//        Product actual = shopService.getProductById("1").orElse(null);
//
//        Product expected = null;
//
//        assertEquals(expected, actual);
//    }

    @Test
    void whenGetOrderList_ThenReturnList() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(orderRepo.getOrderList()).thenReturn(Map.of(
                "1", List.of(
                        new Product("1", "Brot"),
                        new Product("2", "Milch")
                )));

        Map<String, List<Product>> actual = shopService.getOrderList();

        Map<String, List<Product>> expected = Map.of(
                "1", List.of(
                        new Product("1", "Brot"),
                        new Product("2", "Milch")
                ));

        assertEquals(expected, actual);
    }

    @Test
    void WhenAddOrder_ThenReturnAddedOrder() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(generateUUID.generateUUID()).thenReturn("1");
        when(productRepo.getProductById("1")).thenReturn(Optional.of(new Product("1", "Brot")));
        when(orderRepo.addOrder(new Order("1", List.of(new Product("1", "Brot")))))
                .thenReturn(new Order("1", List.of(new Product("1", "Brot"))));

        Order actual = shopService.addOrder(List.of("1"));
        Order expected = new Order("1", List.of(new Product("1", "Brot")));

        assertEquals(expected, actual);
    }

//    @Test
//    void WhenAddOrderWithNonExistingProduct_ThenThrowException() {
//        OrderRepo orderRepo = mock(OrderRepo.class);
//        ProductRepo productRepo = mock(ProductRepo.class);
//        GenerateUUID generateUUID = mock(GenerateUUID.class);
//
//        when(productRepo.getProductById("11")).thenReturn(null);
//
//        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);
//
//        assertThrows(NoProductInOrderListException.class, () -> shopService.addOrder(List.of("11")));
//    }

    @Test
    void WhenGetOrderById_ThenReturnOrder() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        productRepo.addProduct(new Product("1", "Brot"));

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(orderRepo.getOrderById("1")).thenReturn(new Order("1", List.of(new Product("1", "Brot"))));

        Order actual = shopService.getOrderById("1").get();
        Order expected = new Order("1", List.of(new Product("1", "Brot")));

        assertEquals(expected, actual);
    }

    @Test
    void WhenGetOrderByIdDoesNotExist_ThenReturnException() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        assertEquals(Optional.empty(), shopService.getOrderById("1"));
    }

    @Test
    void WhenDeletingOrder_ReturnDeletedOrder() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(orderRepo.deleteOrder("1")).thenReturn(Optional.of(new Order("1", List.of(new Product("1", "Brot")))));

        Order actual = shopService.deleteOrder("1").get();
        Order expected = new Order("1", List.of(new Product("1", "Brot")));

        assertEquals(expected, actual);
    }

    @Test
    void WhenDeletingOrderWithInvalidId_ThenReturnEmptyOptional() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        when(orderRepo.deleteOrder("1")).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), shopService.deleteOrder("1"));
    }

//    @Test
//    void WhenUpdateOrder_ThenGetUpdatedOrder() {
//
//        OrderRepo orderRepo = mock(OrderRepo.class);
//        ProductRepo productRepo = mock(ProductRepo.class);
//        GenerateUUID generateUUID = mock(GenerateUUID.class);
//
//        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);
//
//        when(orderRepo.getOrderById("1")).thenReturn(new Order("1", List.of(new Product("1", "Brot"))));
//        when(productRepo.getProductById("1")).thenReturn(Optional.of(new Product("1", "Brot")));
//        when(productRepo.getProductById("2")).thenReturn(Optional.of(new Product("2", "Milch")));
//        when(orderRepo.updateOrder(new Order("1", List.of(new Product("1", "Brot"), new Product("2", "Milch")))))
//                .thenReturn(new Order("1", List.of(new Product("1", "Brot"), new Product("2", "Milch"))));
//
//        Order actual = shopService.updateOrder("1", List.of("1", "2"));
//        Order expected = new Order("1", List.of(new Product("1", "Brot"), new Product("2", "Milch")));
//
//        assertEquals(expected, actual);
//    }

    @Test
    void WhenUpdateOrderWithInvalidId_ThenReturnException() {
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        GenerateUUID generateUUID = mock(GenerateUUID.class);

        ShopService shopService = new ShopService(productRepo, orderRepo, generateUUID);

        assertThrows(NoOrderWithIdException.class, () -> shopService.updateOrder("2", List.of("1", "2")));
        verify(orderRepo, times(1)).getOrderById("2");
    }
}