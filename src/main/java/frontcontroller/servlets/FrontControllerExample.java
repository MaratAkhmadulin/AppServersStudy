package frontcontroller.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main")
@Slf4j
public class FrontControllerExample extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String path;
        switch (req.getParameter("stage")) {
            case ("catalog"):
                path = "/catalog";
                break;
            case ("cart"):
                path = "/cart";
                break;
            default:
                path = "error";
                break;
        }
        forwardReq(req, resp, path);
    }

    private void forwardReq(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if ("error".equals(path)) {
            out.println("select fo parameter \"stage\" value \"catalog\" or \"cart\"");
            log.info("select fo parameter \"stage\" value \"catalog\" or \"cart\"");
        } else {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(path);
            dispatcher.forward(req, resp);
        }
    }
}