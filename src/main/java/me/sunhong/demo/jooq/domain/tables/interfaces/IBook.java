/**
 * This class is generated by jOOQ
 */
package me.sunhong.demo.jooq.domain.tables.interfaces;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IBook extends Serializable {

    /**
     * Getter for <code>jooq.book.id</code>.
     */
    public Integer getId();

    /**
     * Getter for <code>jooq.book.author_id</code>.
     */
    public Integer getAuthorId();

    /**
     * Getter for <code>jooq.book.title</code>.
     */
    public String getTitle();

    /**
     * Getter for <code>jooq.book.published_in</code>.
     */
    public Integer getPublishedIn();

    /**
     * Getter for <code>jooq.book.language_id</code>.
     */
    public Integer getLanguageId();
}
