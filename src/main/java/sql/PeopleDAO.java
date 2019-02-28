package sql;

import beans.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleDAO implements GenericDAO<Person> {
    private Connection connection;

    public PeopleDAO(Connection connection) {
        this.connection = connection;
    }

    Person getPerson(ResultSet rs) throws SQLException {
        String firstName = rs.getString("firstName");
        String lastName = rs.getString();
        if( rs.wasNull()) {
            resultString = null;
        }

    }

    @Override
    public List<Person> getRows() throws SQLException {
        List<Person> people = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from People order by id desc;");
            while(rs.next()) {
                String resultString = rs.getString("firstName");
//                if( rs.wasNull()) {
//                    resultString = null;
//                }
                people.add(new Person(
                        (String) rs.getObject("firstName"),
                        (String) rs.getObject("lastName"),
                        (String) rs.getObject("eyeColor"),
                        (String) rs.getObject("hairColor"),
                        (Double) rs.getObject("height"),
                        (Integer) rs.getObject("weight"),
                        (Integer) rs.getObject("id")));
            }
        }
        return people;
    }

    @Override
    public void createTable() throws SQLException{
        try(Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists People (id int primary key AUTO_INCREMENT, firstName varchar(25), lastName varchar(25), eyeColor varchar(25), hairColor varchar(25), height double, weight double);"); }
    }

    @Override
    public void deleteRow(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("delete from People where id=?")) {
            preparedStatement.setObject(1, id);
            preparedStatement.execute();
        }
    }


    private PreparedStatement insertPerson(PreparedStatement preparedStatement, Person value) throws SQLException {
        preparedStatement.setObject(1, value.getFirstName());
        preparedStatement.setObject(2, value.getLastName());
        preparedStatement.setObject(3, value.getEyeColor());
        preparedStatement.setObject(4, value.getHairColor());
        preparedStatement.setObject(5, value.getWeight());
        preparedStatement.setObject(6, value.getHeight());
        return preparedStatement;
    }

    @Override
    public void createRow(Person value) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into People (firstName, lastName, eyeColor, hairColor, height, weight) value(?, ?, ?, ?, ?, ?)")) {
            insertPerson(preparedStatement, value).execute();
        }
    }

    @Override
    public void dropTable() throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute("drop table People;");
        }
    }

    @Override
    public void editRow(int id, Person value) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update  People set firstName=?, lastName=?, eyeColor=?, hairColor=?, weight=?, height=? where id=?;")) {
            insertPerson(preparedStatement, value).setObject(7, id);
            preparedStatement.execute();

        }
    }
}
