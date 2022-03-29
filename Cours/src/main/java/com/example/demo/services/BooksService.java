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
	
	
	public BooksService(IBooksRepository bookRepo, IBookMapper mapper, IAuthorRepository authorRepo, IAuthorMapper authorMapper) {
		super();
		this.bookRepo = bookRepo;
		this.mapper = mapper;
		this.authorRepo = authorRepo;
		this.authorMapper = authorMapper;
	}


	public ResponseDTO<BookDTO> saveBook(BookForm bookForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			String erreurs = checkErreur(bindingResult);
			return new ResponseDTO<BookDTO>(null,409,erreurs);
		}
		Book book = mapper.BookFormToBook(bookForm);
		authorRepo.save(book.getAuthor());
		BookDTO bookDTO = mapper.BookToBookDTO(bookRepo.save(book));
		bookDTO.setAuthorDTO(authorMapper.AuthorToAuthorDTO(book.getAuthor()));
		return new ResponseDTO<BookDTO>(bookDTO,201,"Created");
	}

	public List<BookDTO> getAllBooks(){
		return bookRepo.findAll().stream().map(book->mapper.BookToBookDTO(book)).collect(Collectors.toList());

	}


	public BookDTO getBookDTOById(long id){
		return mapper.BookToBookDTO(bookRepo.findById(id).get());

	}
	public String getBooks(String userId,String search,String postcode) {
		WebClient webClient = WebClient.create();
		String responseJson = webClient.get()
				.uri("https://api-adresse.data.gouv.fr/search?q="+search+"&postcode="+postcode+"&limit=1")
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
