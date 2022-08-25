package exeptionhandlingandcookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/signinservlet")
@Slf4j
public class SignInServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("Login").isBlank() && !request.getParameter("Password").isBlank()) {
            request.getRequestDispatcher("welcomePage.jsp").forward(request, response);
        } else {
            response.addCookie(new Cookie("code", "400"));
            response.sendError(400);
            throw new ServletException("Incorrect request parameters");
        }
    }
}
