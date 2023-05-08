import com.enstazao.movies.Register;
import com.enstazao.movies.RegisterController;
import com.enstazao.movies.RegisterInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @Mock
    private RegisterInterface registerInterface;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private RegisterController registerController;

    @Test
    public void testRegisterSuccess() {
        Register user = new Register();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserType("customer");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");

        when(registerInterface.findByEmail(ArgumentMatchers.anyString())).thenReturn(null);
        when(mongoTemplate.save(ArgumentMatchers.any(Register.class))).thenReturn(user);

        ResponseEntity<?> responseEntity = registerController.register(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());

        verify(registerInterface, times(1)).findByEmail(ArgumentMatchers.anyString());
        verify(mongoTemplate, times(1)).save(ArgumentMatchers.any(Register.class));
    }

    @Test
    public void testRegisterConflict() {
        Register user = new Register();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserType("customer");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");

        when(registerInterface.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);

        ResponseEntity<?> responseEntity = registerController.register(user);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("User with email " + user.getEmail() + " already exists", responseEntity.getBody());

        verify(registerInterface, times(1)).findByEmail(ArgumentMatchers.anyString());
        verify(mongoTemplate, times(0)).save(ArgumentMatchers.any(Register.class));
    }
}
