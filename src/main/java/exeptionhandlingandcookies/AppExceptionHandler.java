package exeptionhandlingandcookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("statusCode = {}", request.getAttribute("jakarta.servlet.error.status_code") == null ? "null" : request.getAttribute("jakarta.servlet.error.status_code"));
        log.info("throwable = {}", request.getAttribute("jakarta.servlet.error.exception") == null ? "null" : request.getAttribute("jakarta.servlet.error.exception"));
        Optional<Map<String, String>> cookiesMap = readCookie(request);
        if (cookiesMap.isPresent()) {
            request.setAttribute("jakarta.servlet.error.exception", cookiesMap.get());
        } else {
            request.setAttribute("jakarta.servlet.error.exception", "Not cookies");
        }
        request.getRequestDispatcher("exceptionHandler.jsp").forward(request, response);

    }

    public Optional<Map<String, String>> readCookie(HttpServletRequest req) {
        return Optional.ofNullable(Arrays.stream(req.getCookies())
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue)));
    }
}
