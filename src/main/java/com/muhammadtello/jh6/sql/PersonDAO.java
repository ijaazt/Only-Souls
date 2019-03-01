package com.muhammadtello.jh6.sql;

import com.muhammadtello.jh6.beans.Person;
import com.muhammadtello.jh6.beans.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements GenericDAO<Person> {
    private Connection connection;

    public PersonDAO(Connection connection) {
        this.connection = connection;
    }

    Person getPerson(ResultSet rs) throws SQLException {
        String firstName = rs.getString(PersonContract.COLUMN_NAME_FIRST_NAME);
        firstName = rs.wasNull() ? null : firstName;
        String lastName = rs.getString(PersonContract.COLUMN_NAME_LAST_NAME);
        lastName = rs.wasNull() ? null : lastName;
        String eyeColor = rs.getString(PersonContract.COLUMN_NAME_EYE_COLOR);
        eyeColor = rs.wasNull() ? null : eyeColor;
        String hairColor =  rs.getString(PersonContract.COLUMN_NAME_HAIR_COLOR);
        hairColor = rs.wasNull() ? null : hairColor;
        Size height = new Size(rs.getInt(PersonContract.COLUMN_NAME_HEIGHT));
        height = rs.wasNull() ? null : height;
        Double weight = rs.getDouble(PersonContract.COLUMN_NAME_WEIGHT);
        weight = rs.wasNull() ? null : weight;
        int id = rs.getInt(PersonContract.COLUMN_NAME_ID);
        return new Person(firstName, lastName, eyeColor, hairColor, height, weight, id);
    }

    @Override
    public List<Person> getRows() throws SQLException {
        List<Person> people = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from " + PersonContract.TABLE_NAME + " order by id desc;");
            while(rs.next()) {
                people.add(getPerson(rs));
            }
        }
        return people;
    }

    @Override
    public boolean createTable() throws SQLException{
        try(Statement statement = connection.createStatement()) {
            return statement.execute(PersonContract.CREATE_TABLE); }
    }

    @Override
    public boolean deleteRow(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("delete from People where id=?")) {
            preparedStatement.setObject(1, id);
            return preparedStatement.execute();
        }
    }


    private PreparedStatement insertPerson(PreparedStatement preparedStatement, Person value) throws SQLException {
        preparedStatement.setObject(1, value.getFirstName());
        preparedStatement.setObject(2, value.getLastName());
        preparedStatement.setObject(3, value.getEyeColor());
        preparedStatement.setObject(4, value.getHairColor());
        preparedStatement.setObject(5, value.getHeight().getTotalInches());
        preparedStatement.setObject(6, value.getHeight());
        return preparedStatement;
    }

    @Override
    public boolean createRow(Person value) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into People (firstName, lastName, eyeColor, hairColor, height, weight) value(?, ?, ?, ?, ?, ?)")) {
            return insertPerson(preparedStatement, value).execute();
        }
    }

    @Override
    public boolean dropTable() throws SQLException {
        try(Statement statement = connection.createStatement()) {
            return statement.execute("drop table People;");
        }
    }

    @Override
    public boolean editRow(int id, Person value) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update  People set firstName=?, lastName=?, eyeColor=?, hairColor=?, weight=?, height=? where id=?;")) {
            insertPerson(preparedStatement, value).setObject(7, id);
            return preparedStatement.execute();

        }
    }
}
