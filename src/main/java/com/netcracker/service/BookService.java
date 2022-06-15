package com.netcracker.service;

import com.netcracker.dto.BookDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Book;
import com.netcracker.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public HashSet<BookDTO> getBooks() {
        List <Book> books = bookRepository.findAll();
        HashSet<BookDTO> result = new HashSet<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book.getName(), book.getPrice());
            result.add(bookDTO);
        }
        return result;
    }

    public ArrayList<BookDTO> getBooksByWordAndPrice(String word, Float price) {
        List<Book> books = bookRepository.findAllByNameContainingOrPriceIsGreaterThanOrderByPriceDesc(word, price);
        ArrayList<BookDTO> result = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book.getName(), book.getPrice());
            result.add(bookDTO);
        }
        return result;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        return book;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        bookRepository.delete(book);
    }

    public void updateBookName(Integer id, String name) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));
        book.setName(name);
        bookRepository.save(book);
    }

    public void updateBook(Integer id, Book bookDescription) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book was not found for id:" + id));

        book.setName(bookDescription.getName());
        book.setPrice(bookDescription.getPrice());
        book.setWarehouse(bookDescription.getWarehouse());
        book.setQuantity(bookDescription.getQuantity());

        bookRepository.save(book);
    }
}
