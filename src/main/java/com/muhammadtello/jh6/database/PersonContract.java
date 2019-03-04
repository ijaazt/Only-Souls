package com.muhammadtello.jh6.database;

class PersonContract {
    static final String TABLE_NAME = "People";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_FIRST_NAME = "firstName";
    static final String COLUMN_NAME_LAST_NAME = "lastName";
    static final String COLUMN_NAME_EYE_COLOR = "eyeColor";
    static final String COLUMN_NAME_HAIR_COLOR = "hairColor";
    static final String COLUMN_NAME_HEIGHT = "height";
    static final String COLUMN_NAME_WEIGHT = "weight";
    static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + " ( " + COLUMN_NAME_ID + " int primary key auto_increment, " + COLUMN_NAME_FIRST_NAME + " varchar(25), " + COLUMN_NAME_LAST_NAME + " varchar(25), " + COLUMN_NAME_EYE_COLOR + " varchar(25), " + COLUMN_NAME_HAIR_COLOR + " varchar(25), " + COLUMN_NAME_HEIGHT + " int, " + COLUMN_NAME_WEIGHT + " float);";

}
