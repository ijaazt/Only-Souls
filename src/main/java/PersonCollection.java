import beans.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "PersonCollection", urlPatterns = "/personCollection")
public class PersonCollection extends javax.servlet.http.HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connection = MyConnection.getConnection();
            connection.prepareStatement("create table if not exists People (id int primary key AUTO_INCREMENT, firstName varchar(25), lastName varchar(25), eyeColor varchar(25), hairColor varchar(25), height double, weight double);").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Person> getAllPeople() throws SQLException {
        ArrayList<Person> people = new ArrayList<>();
        Statement statement = connection.createStatement();
        statement.execute("select * from People order by id desc;");
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            people.add(new Person(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("eyeColor"),
                    rs.getString("hairColor"),
                    rs.getDouble("height"),
                    rs.getDouble("weight"),
                    rs.getInt("id")));
        }
        return people;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String method = request.getParameter("_method");
        if (method.equals("PUT")) {
            doPut(request, response);
        } else if (method.equals("DELETE")) {
            doDelete(request, response);
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement("insert People (firstName, lastName, eyeColor, hairColor, height, weight) values (?, ?, ?, ?, ?, ?)");
                fillStatement(request, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            doGet(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            request.setAttribute("people", getAllPeople());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/PersonCollection.jsp");
        requestDispatcher.forward(request, response);
    }

    private void fillStatement(HttpServletRequest request, PreparedStatement statement) throws SQLException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String eyeColor = request.getParameter("eyeColor");
        String hairColor = request.getParameter("hairColor");
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));
        statement.setObject(1, firstName);
        statement.setObject(2, lastName);
        statement.setObject(3, eyeColor);
        statement.setObject(4, hairColor);
        statement.setObject(5, weight);
        statement.setObject(6, height);
        statement.execute();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from People where id=?");
            statement.setObject(1, request.getParameter("id"));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(request, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            PreparedStatement statement = connection.prepareStatement("update  People set firstName=?, lastName=?, eyeColor=?, hairColor=?, weight=?, height=? where id=?;");
            statement.setObject(7, req.getParameter("id"));
            fillStatement(req, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
