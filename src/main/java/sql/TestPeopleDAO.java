package sql;

import beans.Person;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPeopleDAO {
    public static int MOCKED_RESULTS = 100;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;

    ResultSet resultSet;

    private Person person;

    @Test
    public void testGettingRows() throws Exception {
        PeopleDAO peopleDAO = new PeopleDAO(connection);



        when(resultSet.getObject("firstName")).thenReturn(person.getFirstName());
        when(resultSet.getObject("lastName")).thenReturn(person.getLastName());
        when(resultSet.getObject("eyeColor")).thenReturn(person.getEyeColor());
        when(resultSet.getObject("hairColor")).thenReturn(person.getHairColor());
        when(resultSet.getObject("height")).thenReturn(person.getHeight());
        when(resultSet.getObject("weight")).thenReturn(person.getWeight());
        when(resultSet.getObject("id")).thenReturn(person.getId());

        ArrayList<Person> personArrayList = new ArrayList<>();
        for(int i = 0; i < MOCKED_RESULTS; i++){
            personArrayList.add(person);
        }

        List<Person> results = peopleDAO.getRows();
        assertEquals(MOCKED_RESULTS, results.size());
        for(int i = 0 ; i< MOCKED_RESULTS; i++) {
            assertEquals(personArrayList.get(i), results.get(i));
        }
    }

    @Before
    public void beforeTests() throws Exception {
        person = new Person("Muhammad", "Tello", "Brown", "brown", 5.5, 190, 1);
        connection = mock(Connection.class);
        resultSet = mock(ResultSet.class);
        statement = mock(Statement.class);
        preparedStatement = mock(PreparedStatement.class);
        when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(statement.executeQuery(any(String.class))).thenReturn(resultSet);

    }

    @After
    public void afterTests() {

    }
}
