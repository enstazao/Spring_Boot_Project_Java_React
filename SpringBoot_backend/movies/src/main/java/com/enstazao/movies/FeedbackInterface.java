package com.enstazao.movies;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackInterface extends MongoRepository<Feedback, String> {
}
