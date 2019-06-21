package lk.ijse.absd.servlet;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (PrintWriter out = resp.getWriter()) {

            resp.setContentType("application/json");

            try {
                Connection connection = ds.getConnection();

                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM Item");

                JsonArrayBuilder items = Json.createArrayBuilder();

                while (rst.next()) {
                    String code = rst.getString("code");
                    String description = rst.getString("description");
                    int qtyOnHand = rst.getInt("qtyOnHand");
                    double unitPrice = rst.getDouble("unitPrice");

                    JsonObject item = Json.createObjectBuilder()
                            .add("code", code)
                            .add("description", description)
                            .add("qtyOnHand", qtyOnHand)
                            .add("unitPrice", unitPrice)
                            .build();
                    items.add(item);
                }

                out.println(items.build().toString());

                connection.close();
            } catch (Exception ex) {
                resp.sendError(500, ex.getMessage());
                ex.printStackTrace();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        Connection connection = null;
        try {
            reader = Json.createReader(req.getReader());
            JsonObject item = reader.readObject();

            String code = item.getString("code");
            String description = item.getString("description");
            String unitPrice = item.getString("unitPrice");
            String qtyOnHand = item.getString("qtyOnHand");

            System.out.println(" " + code + " " + description + ' ' + unitPrice + " " + qtyOnHand);

            connection = ds.getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(3, unitPrice);
            pstm.setObject(4, qtyOnHand);
            boolean value = pstm.executeUpdate() > 0;

            System.out.println("value : " + value);

            if (value) {
                out.println("true");
            } else {
                out.println("false");
            }

        } catch (JsonParsingException | NullPointerException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception ex) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }

    }

}
