package com.enstazao.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/save/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {
    @Autowired
    private FeedbackInterface feedbackInterface;

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackInterface.save(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
       List<Feedback> feedbacks = feedbackInterface.findAll();
       if (feedbacks.isEmpty()) {
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok().body(feedbacks);
    }
}
