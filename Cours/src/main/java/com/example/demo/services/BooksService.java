package com.example.demo.services;

import com.example.demo.mapper.IAuthorMapper;
import com.example.demo.mapper.IBookMapper;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repositories.IAuthorRepository;
import com.example.demo.repositories.IBooksRepository;
import com.example.demo.utilities.DTO.BookDTO;
import com.example.demo.utilities.DTO.ResponseDTO;
import com.example.demo.utilities.formulaires.BookForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.reactive.function.client.WebClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BooksService implements IBooksInterface {
    private IBooksRepository bookRepo;
    private IAuthorRepository authorRepo;
    private IBookMapper mapper;
    private IAuthorMapper authorMapper;
    private IAuthorInterface authorService;


    public BooksService(IBooksRepository bookRepo, IBookMapper mapper, IAuthorRepository authorRepo, IAuthorMapper authorMapper, IAuthorInterface authorService) {
        super();
        this.bookRepo = bookRepo;
        this.mapper = mapper;
        this.authorRepo = authorRepo;
        this.authorMapper = authorMapper;
        this.authorService = authorService;
    }


    public ResponseDTO<BookDTO> saveBook(BookForm bookForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erreurs = checkErreur(bindingResult);
            return new ResponseDTO<BookDTO>(null, 409, erreurs);
        }
        Book book = mapper.BookFormToBook(bookForm);
        book.setAuthor(authorService.getAuthorById(bookForm.getId_auteur()));
        BookDTO bookDTO = mapper.BookToBookDTO(bookRepo.save(book));
        bookDTO.setId_auteur((int) book.getAuthor().getId());
        return new ResponseDTO<BookDTO>(bookDTO, 201, "Created");
    }

    public ResponseDTO<BookDTO> updateBook(BookForm bookModified, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erreurs = checkErreur(bindingResult);
            return new ResponseDTO<BookDTO>(null, 409, erreurs);
        }
        Book book = bookRepo.getById(bookModified.getId());
        book.setNom(bookModified.getNom());
        book.setNb_exemplaires(bookModified.getNb_exemplaires());
        book.setAuthor(authorService.getAuthorById(bookModified.getId_auteur()));
        BookDTO bookDTO = mapper.BookToBookDTO(bookRepo.save(book));
        bookDTO.setId_auteur((int) book.getAuthor().getId());
        return new ResponseDTO<BookDTO>(bookDTO, 201, "Updated");
    }

    public List<BookDTO> getAllBooks() {

        return bookRepo.findAll().stream().map(book -> {
			BookDTO bookDTO = mapper.BookToBookDTO(book);
            bookDTO.setId_auteur((int) book.getAuthor().getId());
			return bookDTO;
        }).collect(Collectors.toList());

    }


    public BookDTO getBookDTOById(long id) {
        Book book = bookRepo.findById(id).get();
        BookDTO bookDTO = mapper.BookToBookDTO(book);
        bookDTO.setId_auteur((int) book.getAuthor().getId());
        return bookDTO;

    }
    public ResponseDTO<String> deleteBookById(long id){
        bookRepo.delete(bookRepo.getById(id));
        return new ResponseDTO<String>("",201,"L'auteur a bien été supprimé.");

    }

    public String getBooks(String userId, String search, String postcode) {
        WebClient webClient = WebClient.create();
        String responseJson = webClient.get()
                .uri("https://api-adresse.data.gouv.fr/search?q=" + search + "&postcode=" + postcode + "&limit=1")
                .exchange()
                .block()
                .bodyToMono(String.class)
                .block();

        return responseJson;
    }

    public String checkErreur(BindingResult bindingResult) {

        String retour = "";
        for (ObjectError error : bindingResult.getAllErrors()) {
            retour += error.getDefaultMessage();
            retour += "\n";
        }


        return retour;
    }

}
