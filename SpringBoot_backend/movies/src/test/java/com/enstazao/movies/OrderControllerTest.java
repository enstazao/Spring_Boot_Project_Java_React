import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enstazao.movies.Order;
import com.enstazao.movies.OrderController;
import com.enstazao.movies.OrderInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderInterface orderInterface;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllOrders() {
        // setup
        Order order1 = new Order();
        order1.setId("1");
        Order order2 = new Order();
        order2.setId("2");
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        when(orderInterface.findAll()).thenReturn(orderList);

        // execution
        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        // verification
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList, response.getBody());
    }

    @Test
    void testCreateOrder() {
        // setup
        Order order = new Order();
        order.setId("1");
        when(orderInterface.save(any(Order.class))).thenReturn(order);

        // execution
        ResponseEntity<Order> response = orderController.createOrder(order);

        // verification
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    void testDeleteOrderSuccess() {
        // setup
        Order order = new Order();
        order.setId("1");
        Optional<Order> orderOptional = Optional.of(order);
        when(orderInterface.findById("1")).thenReturn(orderOptional);

        // execution
        ResponseEntity<Void> response = orderController.deleteOrder(order);

        // verification
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderInterface, times(1)).delete(order);
    }

    @Test
    void testDeleteOrderNotFound() {
        // setup
        Order order = new Order();
        order.setId("1");
        Optional<Order> orderOptional = Optional.empty();
        when(orderInterface.findById("1")).thenReturn(orderOptional);

        // execution
        ResponseEntity<Void> response = orderController.deleteOrder(order);

        // verification
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(orderInterface, never()).delete(order);
    }

}
