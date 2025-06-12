package com.example.library_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);
		return book.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
		return bookRepository.findById(id)
				.map(book -> {
					book.setTitle(updatedBook.getTitle());
					book.setAuthor(updatedBook.getAuthor());
					book.setIsbn(updatedBook.getIsbn());
					return ResponseEntity.ok(bookRepository.save(book));
				})
				.orElse(ResponseEntity.notFound().build());
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
