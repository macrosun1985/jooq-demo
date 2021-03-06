package me.sunhong.demo.jooq.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface BookService {

	@Transactional(propagation=Propagation.NESTED, readOnly = false)
    public void create(int id, int authorId, String title);
}
