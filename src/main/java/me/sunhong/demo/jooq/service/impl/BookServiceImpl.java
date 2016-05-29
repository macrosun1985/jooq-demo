package me.sunhong.demo.jooq.service.impl;

import static me.sunhong.demo.jooq.domain.Tables.BOOK;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.sunhong.demo.jooq.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private DSLContext dsl;
	
	@Override
	public void create(int id, int authorId, String title){
		for (int i = 0; i < 2; i++){
			dsl.insertInto(BOOK).set(BOOK.ID, id).set(BOOK.AUTHOR_ID, authorId).set(BOOK.TITLE, title).set(BOOK.PUBLISHED_IN, 1).set(BOOK.LANGUAGE_ID, 1).execute();
		}
		
	}

}
