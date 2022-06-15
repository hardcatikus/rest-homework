package com.netcracker.service;

import com.netcracker.dto.BookDTO;
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

    public ArrayList<BookDTO> getBooksByWordAndPrice(String word, Float price){
        List <Book> books = bookRepository.findAllByNameContainingOrPriceIsGreaterThan(word, price);
        ArrayList<BookDTO> result = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book.getName(), book.getPrice());
            result.add(bookDTO);
        }
        return result;
    }
}
