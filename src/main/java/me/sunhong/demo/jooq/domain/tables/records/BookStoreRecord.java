/**
 * This class is generated by jOOQ
 */
package me.sunhong.demo.jooq.domain.tables.records;


import javax.annotation.Generated;

import me.sunhong.demo.jooq.domain.tables.BookStore;
import me.sunhong.demo.jooq.domain.tables.interfaces.IBookStore;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.TableRecordImpl;


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
public class BookStoreRecord extends TableRecordImpl<BookStoreRecord> implements Record1<String>, IBookStore {

    private static final long serialVersionUID = -1303166982;

    /**
     * Setter for <code>jooq.book_store.name</code>.
     */
    public BookStoreRecord setName(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jooq.book_store.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<String> valuesRow() {
        return (Row1) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return BookStore.BOOK_STORE.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookStoreRecord value1(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookStoreRecord values(String value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookStoreRecord
     */
    public BookStoreRecord() {
        super(BookStore.BOOK_STORE);
    }

    /**
     * Create a detached, initialised BookStoreRecord
     */
    public BookStoreRecord(String name) {
        super(BookStore.BOOK_STORE);

        set(0, name);
    }
}
