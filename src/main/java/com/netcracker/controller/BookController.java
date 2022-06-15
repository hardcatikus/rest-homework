package com.netcracker.controller;

import com.netcracker.dto.BookDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Book;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<DeleteResponse> deleteBook(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new DeleteResponse("Book with id:" + id + " was deleted"));
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<UpdateResponse> updateBookName(@PathVariable(value = "id") Integer id,
                                                         @RequestParam(value = "name") String name) throws ResourceNotFoundException {
        bookService.updateBookName(id, name);
        return ResponseEntity.ok(new UpdateResponse("Book with id:" + id +
                " was updated and has a new name: " + name));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<UpdateResponse> updateBook(@PathVariable(value = "id") Integer id,
                                                     @RequestBody Book bookDescription) throws ResourceNotFoundException {
        bookService.updateBook(id, bookDescription);
        return ResponseEntity.ok(new UpdateResponse("Book with id:" + id + " was updated"));
    }

    //Вывод всех различных названий и стоимостей книг
    @GetMapping("/allUniqueBooks")
    public ResponseEntity<HashSet<BookDTO>> getUniqueBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    //вывести книги, в названиях которых встречается определенное слово, или стоящих более определенной суммы
    @GetMapping("/BooksByWordAndPrice")
    public ResponseEntity<ArrayList<BookDTO>> getBooksByWordAndPrice(@RequestParam(value = "word") String word,
                                                                     @RequestParam(value = "price") Float price) {
        return ResponseEntity.ok(bookService.getBooksByWordAndPrice(word, price));
    }
}
