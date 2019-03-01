package com.muhammadtello.jh6.sql;

class PersonContract {
    static final String TABLE_NAME = "People";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_FIRST_NAME = "firstName";
    static final String COLUMN_NAME_LAST_NAME = "lastName";
    static final String COLUMN_NAME_EYE_COLOR = "eyeColor";
    static final String COLUMN_NAME_HAIR_COLOR = "hairColor";
    static final String COLUMN_NAME_HEIGHT = "height";
    static final String COLUMN_NAME_WEIGHT = "weight";
    static final String CREATE_TABLE = String.format("create table if not exists %s ( %s int primary key auto_increment, %s varchar(25), %s varchar(25), %s varchar(25) %s varshar(25), %s int, %s double);", TABLE_NAME, COLUMN_NAME_ID, COLUMN_NAME_FIRST_NAME, COLUMN_NAME_LAST_NAME, COLUMN_NAME_EYE_COLOR, COLUMN_NAME_HAIR_COLOR, COLUMN_NAME_HEIGHT, COLUMN_NAME_WEIGHT);

}
