package app;

import java.util.List;

public interface BookService {
	List<Book> list(int page);

	int countPages();

	int countPages(String title);

	List<Book> search(int offset, String title);

	void create(String title, String description, String author, String isbn, int print_year);

	void delete(Long id);

	void read(Long id);

	Book get(Long id);

	void update(Long id, String title, String description, String isbn, int print_year);
}
