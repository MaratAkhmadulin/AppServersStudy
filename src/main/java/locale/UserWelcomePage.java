package locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/userwelcomepage")
@Slf4j
public class UserWelcomePage extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("application", locale);
        if (request.getParameter("UserName").isBlank()) {
            response.addCookie(new Cookie("code", "400"));
            response.sendError(400);
        } else {
            request.setAttribute("welcomeText", rb.getString("welcomeText"));
            request.getRequestDispatcher("userWelcomePage.jsp").forward(request, response);
        }
    }
}
