package connectionpool;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ConnectionPoolServlet")
@Slf4j
public class ConnectionPoolServlet extends HttpServlet {

    private static DataSource getDataSource() throws NamingException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("jdbc/TestDB");
    }

    private static List<Products> convertRSToProducts(ResultSet rs) throws SQLException {
        List<Products> products = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("ID");
            String name = rs.getString("NAME");
            String desc = rs.getString("DESC");
            products.add(new Products(id, name, desc));
        }
        return products;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<Products> products = new ArrayList<>();

        try (Connection conn = getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select id, name, desc from products")) {
            products = convertRSToProducts(rs);
        } catch (SQLException | NamingException e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("products.jsp").forward(request, resp);
    }
}
