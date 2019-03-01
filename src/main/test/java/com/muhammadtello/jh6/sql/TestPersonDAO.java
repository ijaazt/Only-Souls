package com.muhammadtello.jh6.sql;

import com.muhammadtello.jh6.beans.Person;
import com.muhammadtello.jh6.beans.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPersonDAO {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;

    private ResultSet resultSet;

    @Test
    public void testGettingRows() throws Exception {
        Person person = new Person("Muhammad", "Tello", "Brown", "brown", new Size(30), 190.0, 1);
        PersonDAO personDAO = new PersonDAO(connection);
        when(resultSet.getString("firstName")).thenReturn(person.getFirstName());
        when(resultSet.getString("lastName")).thenReturn(person.getLastName());
        when(resultSet.getString("eyeColor")).thenReturn(person.getEyeColor());
        when(resultSet.getString("hairColor")).thenReturn(person.getHairColor());
        when(resultSet.getInt("height")).thenReturn(person.getHeight().getTotalInches());
        when(resultSet.getDouble("weight")).thenReturn(person.getWeight());
        when(resultSet.getInt("id")).thenReturn(person.getId());
        when(resultSet.wasNull()).thenReturn(false);

        int MOCKED_RESULTS = 10;
        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {
            int iteration = MOCKED_RESULTS;
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) {
                return iteration-- > 0;
            }
        });

        ArrayList<Person> personArrayList = new ArrayList<>();
        for(int i = 0; i < MOCKED_RESULTS; i++){
            personArrayList.add(person);
        }

        List<Person> results = personDAO.getRows();
        assertEquals(MOCKED_RESULTS, results.size());
        for(int i = 0; i< MOCKED_RESULTS; i++) {
            assertEquals(personArrayList.get(i), results.get(i));
        }
    }

    @Before
    public void beforeTests() throws Exception {
        connection = mock(Connection.class);
        resultSet = mock(ResultSet.class);
        statement = mock(Statement.class);
        preparedStatement = mock(PreparedStatement.class);
        when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery(any(String.class))).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testGetPersonNullValues() throws SQLException {
        PersonDAO personDAO = new PersonDAO(connection);
        when(resultSet.wasNull()).thenReturn(true);
        assertEquals(personDAO.getPerson(resultSet), new Person(null, null, null, null, null, null, 1));
    }

    @Test
    public void testGetPersonNonNull() throws SQLException {
        Person person = new Person("Jesse", "James", "brown", "red", new Size(14), 160.0, 2);
        PersonDAO personDAO = new PersonDAO(connection);
        when(resultSet.getString("firstName")).thenReturn(person.getFirstName());
        when(resultSet.getString("lastName")).thenReturn(person.getLastName());
        when(resultSet.getString("eyeColor")).thenReturn(person.getEyeColor());
        when(resultSet.getString("hairColor")).thenReturn(person.getHairColor());
        when(resultSet.getInt("height")).thenReturn(person.getHeight().getTotalInches());
        when(resultSet.getDouble("weight")).thenReturn(person.getWeight());
        when(resultSet.getInt("id")).thenReturn(person.getId());
        when(resultSet.wasNull()).thenReturn(false);
        assertEquals(personDAO.getPerson(resultSet), person);
    }
}
