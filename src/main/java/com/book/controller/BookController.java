package com.book.controller;

import com.book.model.Books;
import com.book.service.BookService;
import com.book.service.GoogleBooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

   
    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        Books createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();

        books.forEach(book -> {
            if (book.getIsbn() != null) {

                List<String> isbnList = Arrays.asList(book.getIsbn().split(",\\s*"));
                List<Object> reviewsList = new ArrayList<>();

                isbnList.forEach(isbn -> {
                    try {

                    	Object reviews = GoogleBooksService.fetchReviewsForBook((book.getIsbn().trim()));
                        reviewsList.add(reviews);
                    } catch (Exception e) {
                        reviewsList.add("Error fetching reviews for ISBN: " + isbn);
                    }
                });


                book.setReviews(reviewsList);
            }
        });

        return new ResponseEntity<>(books, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable String id) {
        Optional<Books> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable String id, @RequestBody Books bookDetails) {
        Books updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Optional<Books> book = bookService.getBookById(id);
        if (book.isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
