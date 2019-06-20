package lk.ijse.absd.servlet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//
//            JsonReader reader = Json.createReader(req.getReader());
//            JsonObject order = reader.readObject();
//
//            Connection connection = ds.getConnection();
//            connection.setAutoCommit(false);
//
//            String sql = "INSERT INTO Orders VALUES (?,?,?)";
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setObject(2, order.getString("date"));
//            pstm.setObject(3, order.getString("customerID"));
//            pstm.setObject(1, order.getString("orederID"));
//            int affectedRows = pstm.executeUpdate();
//
//            if (affectedRows == 0) {
//                connection.rollback();
//                return;
//            }
//
//            sql = "INSERT INTO  ItemDetail VALUES (?,?,?,?)";
//            pstm = connection.prepareStatement(sql);
//
//            PreparedStatement pstm2 = connection.prepareStatement("UPDATE material SET MQTY=? WHERE MID=?");
//
//            for (int i=0;i<) {
//
//                pstm.setObject(1, orderid.getText());
//                pstm.setObject(2, orderDetail.getPID());
//                pstm.setObject(3, orderDetail.getQty());
//                pstm.setObject(4, orderDetail.getUnitPrice());
//
//                affectedRows = pstm.executeUpdate();
//
//                if (affectedRows == 0) {
//                    connection.rollback();
//                    return;
//                }
//
//                int qtyOnHand = 0;
//
//                Statement stm = connection.createStatement();
//                ResultSet rst = stm.executeQuery("SELECT * FROM material WHERE MID='" + orderDetail.getPID() + "'");
//                if (rst.next()) {
//                    qtyOnHand = rst.getInt(5);
//                }
//
//                pstm2.setObject(1, qtyOnHand - orderDetail.getQty());
//                pstm2.setObject(2, orderDetail.getPID());
//
//                affectedRows = pstm2.executeUpdate();
//
//                if (affectedRows == 0) {
//                    connection.rollback();
//                    return;
//                }
//
//            }
//
//            connection.commit();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, " Order Sucess ", ButtonType.OK);
//            alert.show();
//            try {
//                orderid.setText(genarateID());
//            } catch (Exception ex) {
//                Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (SQLException ex) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//            Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException ex) {
//                Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//
    }
}
