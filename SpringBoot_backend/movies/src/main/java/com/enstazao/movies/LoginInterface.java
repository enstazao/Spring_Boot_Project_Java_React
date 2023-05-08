package com.enstazao.movies;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface LoginInterface extends MongoRepository<Register, String> {
    Register findByEmailAndPassword(String email, String password);
}
