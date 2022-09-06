package filtersandsession;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

@WebFilter("/calculator")
public class CalculatorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!NumberUtils.isParsable(request.getParameter("Value"))) {
            request.getRequestDispatcher("calculatorErrorPage.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
