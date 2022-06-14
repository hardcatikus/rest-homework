package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Book;
import com.netcracker.repository.BookRepository;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class BookController {

    @Autowired
    BookRepository repository;
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<DeleteResponse> deleteBook(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Book book = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        repository.delete(book);
        return ResponseEntity.ok(new DeleteResponse("Book with id:" + id + " was deleted"));
    }

    @PatchMapping("/books/{id}/{name}")
    public ResponseEntity<UpdateResponse> updateBookName(@PathVariable(value = "id") Integer id,
                                                          @RequestBody String name) throws ResourceNotFoundException{
        Book book = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        book.setName(name);
        repository.save(book);
        return ResponseEntity.ok(new UpdateResponse("Book with id:" + id +
                " was updated and has a new name: " + name));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<UpdateResponse> updateBook(@PathVariable(value = "id") Integer id,
                                                      @RequestBody Book bookDescription) throws ResourceNotFoundException{
        Book book = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));

        book.setName(bookDescription.getName());
        book.setPrice(bookDescription.getPrice());
        book.setWarehouse(bookDescription.getWarehouse());
        book.setQuantity(bookDescription.getQuantity());

        repository.save(book);
        return ResponseEntity.ok(new UpdateResponse("Book with id:" + id + " was updated"));
    }
}
