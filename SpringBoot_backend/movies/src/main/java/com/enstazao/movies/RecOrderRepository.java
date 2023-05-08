package com.enstazao.movies;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecOrderRepository extends MongoRepository<RecOrder, String> {
    List<RecOrder> findByDriverEmail(String driverEmail);
}
