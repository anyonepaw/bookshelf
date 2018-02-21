package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookServiceJdbcTemplate implements BookService {
  private static final BeanPropertyRowMapper<Book> BOOK_MAPPER = new BeanPropertyRowMapper<>(Book.class);
  private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookServiceJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * Данные, которые должны быть в таблице:
    • id – идентификатор книги в БД;
    • title – название книги. Можно использовать тип VARCHAR(100);
    • description – краткое описание о чем книга. Можно использовать тип VARCHAR(255);
    • author – фамилия и имя автора. Можно использовать тип VARCHAR(100);
    • isbn – ISBN книги. Можно использовать тип VARCHAR(20);
    • printYear – в каком году напечатана книга (INT);
    • readAlready – читал ли кто-то эту книгу. Это булево поле.
    * */

    @Override public List<Book> list(int page) {
      return jdbcTemplate.query("SELECT * FROM book_shelf order by id LIMIT 10 OFFSET ?", BOOK_MAPPER, page * 10);
    }

    @Override public int countPages() {
        String sql = "SELECT count(1) FROM book_shelf";
        int allBooks = jdbcTemplate.queryForObject(sql, Integer.class);
        return (int) Math.ceil(allBooks / 10.0);
    }

    @Override public int countPages(String title) {
        String sql = "SELECT count(1) FROM book_shelf WHERE LOWER(title) LIKE ?";
        int allBooks = jdbcTemplate.queryForObject(sql, Integer.class, "%" + title + "%");
        return (int) Math.ceil(allBooks / 10.0);
    }

    @Override public List<Book> search(int offset, String title) {
        return jdbcTemplate.query("SELECT * FROM book_shelf WHERE LOWER(title) LIKE ? order by id LIMIT 10 OFFSET ?", BOOK_MAPPER, "%" + title.toLowerCase() + "%");
    }

    @Override public void create(String title, String description, String author, String isbn, int print_year) {
        String sql = "INSERT INTO book_shelf (id, title, description," +
                " author, isbn, print_year, read_already) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, title, description, author, isbn, print_year, false);
    }

    @Override public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM book_shelf WHERE id = ?", id);
    }

    @Override public void read(Long id) {
        jdbcTemplate.update("UPDATE book_shelf SET read_already = true WHERE id = ?", id);
    }

    @Override public Book get(Long id) {
        try {
            String sql = "SELECT * FROM book_shelf WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override public void update(Long id, String title, String description, String isbn, int print_year) {
        String sql = "UPDATE book_shelf SET title = ?, description = ?, isbn = ?, print_year = ?, read_already = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, description, isbn, print_year, false, id);
    }


}
