package com.enstazao.movies;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterInterface extends MongoRepository<Register, String> {
    Register findByEmail(String email);
}
