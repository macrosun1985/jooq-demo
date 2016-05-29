package me.sunhong.demo.jooq.service.impl;

import static me.sunhong.demo.jooq.domain.Tables.LANGUAGE;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.sunhong.demo.jooq.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService{

	@Autowired
	private DSLContext dsl;
	
	@Override
	
	public void create(int id, String cd, String description) {
		dsl.insertInto(LANGUAGE).set(LANGUAGE.ID, id).set(LANGUAGE.CD, cd).set(LANGUAGE.DESCRIPTION, description).execute();
	}

}
