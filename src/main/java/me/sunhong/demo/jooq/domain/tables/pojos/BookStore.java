/**
 * This class is generated by jOOQ
 */
package me.sunhong.demo.jooq.domain.tables.pojos;


import javax.annotation.Generated;

import me.sunhong.demo.jooq.domain.tables.interfaces.IBookStore;


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
public class BookStore implements IBookStore {

    private static final long serialVersionUID = -1562937982;

    private final String name;

    public BookStore(BookStore value) {
        this.name = value.name;
    }

    public BookStore(
        String name
    ) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BookStore (");

        sb.append(name);

        sb.append(")");
        return sb.toString();
    }
}
