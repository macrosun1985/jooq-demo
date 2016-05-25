package me.sunhong.demo.jooq;

import static me.sunhong.demo.jooq.domain.Tables.BOOK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import junit.framework.Assert;
import me.sunhong.demo.jooq.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jooq-spring.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
public class TransactionTest {

	@Autowired
	DSLContext dsl;
	@Autowired
	DataSourceTransactionManager txMgr;
	@Autowired
	BookService books;

	@After
	public void teardown() {

		// Delete all books that were created in any test
		dsl.delete(BOOK).where(BOOK.ID.gt(4)).execute();
	}

	@Test
	@Ignore
	public void testExplicitTransactions() {
		boolean rollback = false;
		TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition());
		try {

			// This is a "bug". The same book is created twice, resulting in a
			// constraint violation exception
			for (int i = 0; i < 2; i++)
				dsl.insertInto(BOOK).set(BOOK.ID, 5).set(BOOK.AUTHOR_ID, 1).set(BOOK.TITLE, "Book 5").execute();

			Assert.fail();
		}

		// Upon the constraint violation, we explicitly roll back the
		// transaction.
		catch (DataAccessException e) {
			txMgr.rollback(tx);
			rollback = true;
		}

		assertEquals(4, dsl.fetchCount(BOOK));
		assertTrue(rollback);
	}
}