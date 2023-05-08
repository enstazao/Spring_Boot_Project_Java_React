package com.enstazao.movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {
    @Autowired
    private MongoTemplate mongoTemplate;

    private RegisterInterface registerInterface;

    @Autowired
    public RegisterController(RegisterInterface registerInterface, MongoTemplate mongoTemplate) {
        this.registerInterface = registerInterface;
        this.mongoTemplate = mongoTemplate;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register user) {
        Register existingUser = registerInterface.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email " + user.getEmail() + " already exists");
        }

        Register savedUser = mongoTemplate.save(user);
        return new ResponseEntity<Register>(savedUser, HttpStatus.CREATED);
    }
}
