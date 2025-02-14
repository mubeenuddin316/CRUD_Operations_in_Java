package pack1;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/DelRecServlet21")
public class DelRecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DelRecServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idParam = request.getParameter("id");
        
        try {
            int id = Integer.parseInt(idParam);
            servDAO dao = new servDAO();
            int result = dao.deleteUser(id);

            if (result > 0) {
                response.getWriter().println("<h3 style='color: green;'>Record deleted successfully.</h3>");
                response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
                response.getWriter().println("<button onclick=\"location.href='view.jsp'\">Show Database</button>");
            } else {
                response.getWriter().println("<h3 style='color: red;'>Error: ID not found!</h3>");
                response.getWriter().println("<button onclick=\"location.href='index.jsp'\">Try Again</button>");
                response.getWriter().println("<button onclick=\"location.href='view.jsp'\">Show Database</button>");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("<h3 style='color: red;'>Invalid ID format! Please enter a number.</h3>");
        } catch (Exception e) {
            response.getWriter().println("<h3 style='color: red;'>An unexpected error occurred: " + e.getMessage() + "</h3>");
        }
    }

}
