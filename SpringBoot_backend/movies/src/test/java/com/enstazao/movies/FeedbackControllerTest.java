import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.enstazao.movies.Feedback;
import com.enstazao.movies.FeedbackController;
import com.enstazao.movies.FeedbackInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FeedbackControllerTest {

    @Mock
    private FeedbackInterface feedbackInterface;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId("1");
        feedback.setFirstName("John");
        feedback.setLastName("Doe");
        feedback.setUserType("User");
        feedback.setFeedback("Great movie!");
        feedback.setEmail("john.doe@example.com");

        Mockito.when(feedbackInterface.save(Mockito.any(Feedback.class))).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.createFeedback(feedback);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(feedback, response.getBody());
    }

    @Test
    public void testGetAllFeedbacks() {
        Feedback feedback1 = new Feedback();
        feedback1.setId("1");
        feedback1.setFirstName("John");
        feedback1.setLastName("Doe");
        feedback1.setUserType("User");
        feedback1.setFeedback("Great movie!");
        feedback1.setEmail("john.doe@example.com");

        Feedback feedback2 = new Feedback();
        feedback2.setId("2");
        feedback2.setFirstName("Jane");
        feedback2.setLastName("Doe");
        feedback2.setUserType("User");
        feedback2.setFeedback("Awesome movie!");
        feedback2.setEmail("jane.doe@example.com");

        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback1);
        feedbacks.add(feedback2);

        Mockito.when(feedbackInterface.findAll()).thenReturn(feedbacks);

        ResponseEntity<List<Feedback>> response = feedbackController.getAllFeedbacks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feedbacks, response.getBody());
        assertTrue(response.getBody().contains(feedback1));
        assertTrue(response.getBody().contains(feedback2));
    }

}
