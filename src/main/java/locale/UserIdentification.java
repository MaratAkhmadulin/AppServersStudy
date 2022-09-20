package locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/useridentification")
@Slf4j
public class UserIdentification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("application", locale);
        request.setAttribute("inputUserNameText", rb.getString("inputUserNameText"));
        request.getRequestDispatcher("userIdentification.jsp").forward(request, response);
    }
}
