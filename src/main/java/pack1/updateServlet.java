package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/updateServlet1")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public updateServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            servDAO dao = new servDAO();
            servDTO existingDto = dao.getById(id);

            if (existingDto == null) {
                out.println("<h3>User not found with ID: " + id + "</h3>");
                return;
            }

            String name = request.getParameter("name");
            if (name != null && !name.trim().isEmpty()) {
                existingDto.setName(name.trim());
            }

            String email = request.getParameter("email");
            if (email != null && !email.trim().isEmpty()) {
                existingDto.setEmail(email.trim());
            }

            String phoneParam = request.getParameter("phone");
            if (phoneParam != null && !phoneParam.trim().isEmpty()) {
                try {
                    Long phone = Long.parseLong(phoneParam.trim());
                    existingDto.setPhone(phone);
                } catch (NumberFormatException e) {
                    out.println("<h3>Invalid phone number format</h3>");
                    return;
                }
            }

            int result = dao.updateData(existingDto);

            if (result == 1) {
                out.println("<h3>Data updated successfully</h3>");
                out.println("<p>ID: " + existingDto.getId() + "</p>");
                out.println("<p>Name: " + existingDto.getName() + "</p>");
                out.println("<p>Email: " + existingDto.getEmail() + "</p>");
                out.println("<p>Phone: " + (existingDto.getPhone() != null ? existingDto.getPhone() : "N/A") + "</p>");
            } else {
                out.println("<h3>Error updating data</h3>");
            }

        } catch (NumberFormatException e) {
            out.println("<h3>Invalid phone number format</h3>");
        } catch (SQLException e) {
            out.println("<h3>Database error: " + e.getMessage() + "</h3>");
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
        	response.getWriter().println("<button onclick=\"location.href='update.jsp'\">Try Again</button>");
            response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
            response.getWriter().println("<button onclick=\"location.href='view.jsp'\">Show Dataabse</button>");
            out.close();
        }
    }

}


