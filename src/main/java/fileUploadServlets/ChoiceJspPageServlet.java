package fileUploadServlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/choicejsppageservlet")
@Slf4j
public class ChoiceJspPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadType = req.getParameter("Upload");
        String jspName;
        switch (uploadType) {
            case ("one file"):
                jspName = "oneFileUpload.jsp";
                break;
            case ("two file"):
                jspName = "twoFileUpload.jsp";
                break;
            default:
                jspName = "error";
                break;
        }
        forwardJspPage(req, resp, jspName);
    }

    private void forwardJspPage(HttpServletRequest req, HttpServletResponse resp, String jspName) throws ServletException, IOException {
        if ("error".equals(jspName)) {
            resp.getWriter().println("Ошибка! Выберите другой вариант");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspName);
            requestDispatcher.forward(req, resp);
        }
    }
}
