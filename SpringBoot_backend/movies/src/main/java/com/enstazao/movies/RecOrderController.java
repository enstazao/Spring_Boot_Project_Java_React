package com.enstazao.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rec/order")
@CrossOrigin(origins = "http://localhost:3000")
public class RecOrderController {
    @Autowired
    private RecOrderRepository orderRepository;

    @Autowired
    public RecOrderController(RecOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<List<RecOrder>> getAllOrders() {
        List<RecOrder> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecOrder> createOrder(@RequestBody RecOrder order) {
        RecOrder savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/driverEmail")
    public ResponseEntity<List<RecOrder>> getOrdersByDriverEmail(@RequestBody RecOrder order) {

        List<RecOrder> orders = orderRepository.findByDriverEmail(order.getDriverEmail());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestBody RecOrder order) {
        Optional<RecOrder> optionalOrder = orderRepository.findById(order.getId());
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return new ResponseEntity<>("Order with ID: " + order.getId() + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order with ID: " + order.getId() + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
