package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Primary
public class BookServiceJPA implements BookService {
	@Autowired EntityManager entityManager;

	public List<Book> list(int page) {
		TypedQuery<Book> allBooks = entityManager.createQuery("select b from Book b order by b.id", Book.class);
		allBooks.setMaxResults(10);
		allBooks.setFirstResult(page * 10);
		return allBooks.getResultList();
	}

	public List<Book> search(int page, String title) {
		TypedQuery<Book> allBooks = entityManager.createQuery("select b from Book b where LOWER(b.title) like :title order by b.id", Book.class);
		allBooks.setMaxResults(10);
		allBooks.setFirstResult(page * 10);
		allBooks.setParameter("title", "%" + title.toLowerCase() + "%");
		return allBooks.getResultList();
	}

	@Transactional
	public void create(String title, String description, String author, String isbn, int printYear) {
		Book book = new Book(null, title, description, author, isbn, printYear, false);
		entityManager.persist(book);
	}

	@Transactional
	public void delete(Long id) {
		Book book = entityManager.find(Book.class, id);
		entityManager.remove(book);
	}

	public Book get(Long id) {
		return entityManager.find(Book.class, id);
	}

	@Transactional
	public void update(Long id, String title, String description, String isbn, int printYear) {
		Book book = entityManager.find(Book.class, id);
		book.setTitle(title);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrintYear(printYear);
		book.setReadAlready(false);
		entityManager.merge(book);
	}

	public int countPages() {
		Long bookCount = entityManager.createQuery("SELECT COUNT(b) from Book b", Long.class).getSingleResult();
		return (int) Math.ceil(bookCount / 10.0);
	}

	public int countPages(String title) {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) from Book b where lower(b.title) like :title ", Long.class);
		query.setParameter("title", "%" + title.toLowerCase() + "%");
		Long bookCount = query.getSingleResult();
		return (int) Math.ceil(bookCount / 10.0);
	}
}
