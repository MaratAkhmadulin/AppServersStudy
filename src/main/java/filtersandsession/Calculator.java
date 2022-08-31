package filtersandsession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/calculator")
@Slf4j
public class Calculator extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Double valueReq = Double.valueOf(request.getParameter("Value"));
        HttpSession session = request.getSession();
        String jspName;
        session.setMaxInactiveInterval(60 * 10);
        String value1 = Optional.ofNullable(session.getAttribute("value")).orElse("Not value").toString();
        if ("Not value".equals(value1)) {
            session.setAttribute("value", valueReq);
            jspName = "calculatorValueTwo.jsp";
        } else {
            Double sum = valueReq + (Double) session.getAttribute("value");
            request.setAttribute("sum", sum.toString());
            session.removeAttribute("value");
            jspName = "calculatorSum.jsp";
        }
        request.getRequestDispatcher(jspName).forward(request, response);
    }
}
