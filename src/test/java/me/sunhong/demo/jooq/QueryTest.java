package me.sunhong.demo.jooq;

import static me.sunhong.demo.jooq.domain.Tables.AUTHOR;
import static me.sunhong.demo.jooq.domain.Tables.BOOK;
import static me.sunhong.demo.jooq.domain.Tables.BOOK_STORE;
import static me.sunhong.demo.jooq.domain.Tables.BOOK_TO_BOOK_STORE;
import static org.jooq.impl.DSL.countDistinct;
import static org.junit.Assert.assertEquals;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.sunhong.demo.jooq.domain.tables.Author;
import me.sunhong.demo.jooq.domain.tables.Book;
import me.sunhong.demo.jooq.domain.tables.BookStore;
import me.sunhong.demo.jooq.domain.tables.BookToBookStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class QueryTest {

	@Autowired
    DSLContext create;
	
	@Test
	@Ignore
    public void testJoin() throws Exception {
		
        Book b = BOOK.as("b");
        Author a = AUTHOR.as("a");
        BookStore s = BOOK_STORE.as("s");
        BookToBookStore t = BOOK_TO_BOOK_STORE.as("t");

        Result<Record3<String, String, Integer>> result =
        create.select(a.FIRST_NAME, a.LAST_NAME, countDistinct(s.NAME))
              .from(a)
              .join(b).on(b.AUTHOR_ID.equal(a.ID))
              .join(t).on(t.BOOK_ID.equal(b.ID))
              .join(s).on(t.NAME.equal(s.NAME))
              .groupBy(a.FIRST_NAME, a.LAST_NAME)
              .orderBy(countDistinct(s.NAME).desc())
              .fetch();

        assertEquals(2, result.size());
        assertEquals("Paulo", result.getValue(0, a.FIRST_NAME));
        assertEquals("George", result.getValue(1, a.FIRST_NAME));

        assertEquals("Coelho", result.getValue(0, a.LAST_NAME));
        assertEquals("Orwell", result.getValue(1, a.LAST_NAME));

        assertEquals(Integer.valueOf(3), result.getValue(0, countDistinct(s.NAME)));
        assertEquals(Integer.valueOf(2), result.getValue(1, countDistinct(s.NAME)));
    }
}
