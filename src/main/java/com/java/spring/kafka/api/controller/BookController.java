package com.java.spring.kafka.api.controller;

import com.java.spring.kafka.api.model.Book;
import com.java.spring.kafka.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> template;

    private String topic = "book-topic";

    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name) {
        template.send(topic, "Hi " + name + " Welcome to kafka");
        return "Data published";
    }

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {
        repository.save(book);
        return "Added book with id : " + book.getId();
    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {
        return repository.findAll();
    }

    @GetMapping("/publishBook")
    public String publishBooks() {
        List<Book> books = repository.findAll();
        template.send(topic, books.get(0));
        return "A book published to the topic "+topic;
    }


}
