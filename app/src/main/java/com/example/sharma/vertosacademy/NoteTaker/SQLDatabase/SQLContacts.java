package com.example.sharma.vertosacademy.NoteTaker.SQLDatabase;

/**
 * Created by Ashish Kumar Satyam on 4/5/2017.
 */

public final class SQLContacts {
    /**
     * Constructor should be a private to prevent being initiated accidentally.
     */
    private SQLContacts(){}

    /**
     * Inner NoteTable that define the table contents and column names
     *
     */
    // columns and table names
    public static final String TABLE_NAME = "note";
    public static final String COLUMN_ID = "nId";
    public static final String COLUMN_TITLE = "nTitle";
    public static final String COLUMN_TEXT = "nText";

    // database operations

    public static final String COMMA_SEP = " , ";
    public static final String CREATE_TABLE=" create table ";
    public static final String UPDATE_TABLE=" update table ";
    public static final String SELECT_TABLE=" select ";
    public static final String SELECT_ALL=" * ";
    public static final String FROM=" from ";
    public static final String BRACKET_OPEN=" ( " ;
    public static final String BRACKET_CLOSED=" ) ";
    public static final String DELETE = " delete ";
    public static final String WHERE=" where ";
    public static final String EQUAL= " = ";

    // data
    public static final String DATA_INTEGER=" integer ";
    public static final String DATA_TEXT=" text ";
    public static final String PRIMARY_KEY_AUTO=" primary key autoincrement ";

    // SQL statements
    public static final String STATEMENT_CREATE_APPLICATION_DATABASE=CREATE_TABLE + TABLE_NAME
            + " (" + COLUMN_ID + DATA_INTEGER + PRIMARY_KEY_AUTO+", "+
            COLUMN_TITLE + DATA_TEXT+ " , "+
            COLUMN_TEXT + DATA_TEXT+" ) ; " ;

}

