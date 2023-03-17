package com.example.goodreads.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;

import com.example.goodreads.repository.BookRepository;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.model.Book;

@Service
public class BookJpaService implements BookRepository {

  @Autowired
  private BookJpaRepository bookJpaRepository;
private Book save; 

  @Override
  public ArrayList<Book> getBooks() {
    List<Book> bookList = bookJpaRepository.findAll();
    ArrayList<Book> books = new ArrayList<>(bookList);
    return books;
  }

  @Override
  public Book getBookById(int bookId) {
    try{
        Book book=bookJpaRepository.findById(bookId).get();
        return book;
    }
    catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    

    // return new Book(3, "Sample Book", "sample.jpg");
  }

    @Override
  public Book addBook(Book book) {
    // return new Book(3, "Sample Book", "sample.jpg");
   bookJpaRepository.save(book);
    return book;
  }

    @Override
  public Book updateBook(int bookId, Book book) {
    try{
        Book newBook=bookJpaRepository.findById(bookId).get();
        if(book.getName()!=null){
          newBook.setName(book.getName());
        }
        if(book.getImageUrl()!=null){
          newBook.setImageUrl(book.getImageUrl());
        }
        bookJpaRepository.save(newBook);
        return book;
    }
    catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }}
  //   return new Book(3, "Sample Book", "sample.jpg");
  // }

    @Override
  public void deleteBook(int bookId) {
      try{
        bookJpaRepository.deleteById(bookId);
    }
    catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}