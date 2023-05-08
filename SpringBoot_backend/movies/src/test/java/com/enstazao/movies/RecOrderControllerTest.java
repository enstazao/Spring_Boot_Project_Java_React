import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enstazao.movies.RecOrder;
import com.enstazao.movies.RecOrderController;
import com.enstazao.movies.RecOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RecOrderControllerTest {

    private RecOrderRepository orderRepository;
    private RecOrderController controller;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(RecOrderRepository.class);
        controller = new RecOrderController(orderRepository);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        RecOrder order1 = new RecOrder();
        order1.setId("1");
        RecOrder order2 = new RecOrder();
        order2.setId("2");
        List<RecOrder> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        ResponseEntity<List<RecOrder>> response = controller.getAllOrders();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(orders);
    }

    @Test
    public void testCreateOrder() {
        // Arrange
        RecOrder order = new RecOrder();
        order.setId("1");
        when(orderRepository.save(any(RecOrder.class))).thenReturn(order);

        // Act
        ResponseEntity<RecOrder> response = controller.createOrder(order);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(order);
    }

    @Test
    public void testGetOrdersByDriverEmail() {
        // Arrange
        RecOrder order1 = new RecOrder();
        order1.setId("1");
        order1.setDriverEmail("driver1@example.com");
        RecOrder order2 = new RecOrder();
        order2.setId("2");
        order2.setDriverEmail("driver1@example.com");
        List<RecOrder> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        when(orderRepository.findByDriverEmail("driver1@example.com")).thenReturn(orders);
        RecOrder requestOrder = new RecOrder();
        requestOrder.setDriverEmail("driver1@example.com");

        // Act
        ResponseEntity<List<RecOrder>> response = controller.getOrdersByDriverEmail(requestOrder);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(orders);
    }

    @Test
    public void testDeleteOrder() {
        // Arrange
        RecOrder order = new RecOrder();
        order.setId("1");
        Optional<RecOrder> optionalOrder = Optional.of(order);
        when(orderRepository.findById("1")).thenReturn(optionalOrder);

        // Act
        ResponseEntity<String> response = controller.deleteOrder(order);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Order with ID: 1 deleted");
    }

    @Test
    public void testDeleteOrderNotFound() {
        // Arrange
        RecOrder order = new RecOrder();
        order.setId("1");
        Optional<RecOrder> optionalOrder = Optional.empty();
        when(orderRepository.findById("1")).thenReturn(optionalOrder);

        // Act
        ResponseEntity<String> response = controller.deleteOrder(order);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Order with ID: 1 not found");
    }

}
