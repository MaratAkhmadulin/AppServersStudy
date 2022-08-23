package fileuploadservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/fileuploadservlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100, // 100 MB
        location = "/tmp"
)
public class FileUploadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Collection<Part> parts = request.getParts();
        final PrintWriter writer = response.getWriter();
        if (parts.isEmpty()) {
            writer.print("No parts provided.");
        } else {
            for (Part part : parts) {
                final String submittedFileName = part.getSubmittedFileName();
                final String partName = part.getName();
                if (submittedFileName.isBlank()) {
                    writer.printf("File name is empty for %s.", partName);
                } else {
                    writer.printf(" getSubmittedFileName: %s, ", submittedFileName);
                    writer.printf(" getName: %s, ", partName);
                    part.write(submittedFileName);
                    writer.printf("The file %s uploaded sucessfully.", submittedFileName);
                }
            }
        }
        writer.close();
    }
}
