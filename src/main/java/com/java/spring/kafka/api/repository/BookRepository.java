package com.java.spring.kafka.api.repository;

import com.java.spring.kafka.api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {

}