package me.sunhong.demo.jooq;

import static me.sunhong.demo.jooq.domain.Tables.BOOK;
import static me.sunhong.demo.jooq.domain.Tables.LANGUAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import me.sunhong.demo.jooq.service.BookService;
import me.sunhong.demo.jooq.service.LanguageService;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
public class TransactionTest{

	@Autowired
	DSLContext dsl;
	
	@Autowired
	DataSourceTransactionManager txMgr;
	
	@Autowired 
	BookService bookService;
	
	@Autowired
	LanguageService languageService;
	
	@Test
	@Ignore
	public void testExplicitTransactions() {
		boolean rollback = false;
		TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition());
		try {
			// This is a "bug". The same book is created twice, resulting in a
			// constraint violation exception
			
			dsl.insertInto(LANGUAGE).set(LANGUAGE.ID, 5).set(LANGUAGE.CD, "tt").set(LANGUAGE.DESCRIPTION, "").execute();

			for (int i = 0; i < 2; i++) {
				dsl.insertInto(BOOK).set(BOOK.ID, 5).set(BOOK.AUTHOR_ID, 1).set(BOOK.TITLE, "Book 5").set(BOOK.PUBLISHED_IN, 1).set(BOOK.LANGUAGE_ID, 1).execute();
			}
		}
		// Upon the constraint violation, we explicitly roll back the
		// transaction.
		
		 catch (DataAccessException e) {
			txMgr.rollback(tx);
			rollback = true;
		}

		 assertEquals(4, dsl.fetchCount(LANGUAGE));
		assertEquals(4, dsl.fetchCount(BOOK));
		assertTrue(rollback);
	}
	
	@Test
//	@Ignore
    public void testDeclarativeTransactions() {
        boolean rollback = false;

        try {
        	languageService.create(5, "tt", "");
        	bookService.create(5, 1, "Book 5");
        }
        catch (DataAccessException e) {
//        catch(Exception e){
            rollback = true;
            e.printStackTrace();
            System.out.println("=====================");
        }

        assertEquals(4, dsl.fetchCount(LANGUAGE));
        assertEquals(4, dsl.fetchCount(BOOK));
        assertTrue(rollback);
    }
	
	@After
	public void teardown() {
		// Delete all books that were created in any test
		dsl.delete(BOOK).where(BOOK.ID.gt(4)).execute();
		dsl.delete(LANGUAGE).where(LANGUAGE.ID.gt(4)).execute();
	}

}