package com.enstazao.movies;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderInterface extends MongoRepository<Order, String> {

}
