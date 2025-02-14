package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/InsertServlet51")
public class insertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public insertServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            long phone = Long.parseLong(request.getParameter("phone"));
            
            servDTO dto = new servDTO();
            dto.setId(id);
            dto.setName(name);
            dto.setEmail(email);
            dto.setPhone(phone);
            
            servDAO dao = new servDAO();
            int result = dao.insertData(dto);

            if (result == 1) {
                out.println("<h3>Data inserted successfully</h3>");
                out.println("<pre>" + id + "   " + name + "    " + email + "     " + phone + "</pre>");
                response.getWriter().println("<button onclick=\"location.href='insert.jsp'\">INSERT MORE</button><br><br>");
                response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
                response.getWriter().println("<button onclick=\"location.href='view.jsp'\">See Full Data</button>");

            } else {
                out.println("<h3>Error inserting data</h3>");
                out.println("<button onclick=\"location.href='insert.jsp'\">Try Again</button>");
                response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
                response.getWriter().println("<button onclick=\"location.href='view.jsp'\">See Database</button>");
                
            }
        } catch (NumberFormatException e) {
            out.println("<h3>Invalid input format. Please enter valid values.</h3>");
            out.println("<h4>"+e.getMessage()+"</h4>");
            out.println("<button onclick=\"location.href='insert.jsp'\">Try Again</button>");
            response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
            response.getWriter().println("<button onclick=\"location.href='view.jsp'\">See Database</button>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<h3>Internal Server Error</h3>");
            out.println("<h4>"+e.getMessage()+"</h4>");
            out.println("<button onclick=\"location.href='insert.jsp'\">Try Again</button>");
            response.getWriter().println("<button onclick=\"location.href='index.jsp'\">HOME</button>");
            response.getWriter().println("<button onclick=\"location.href='view.jsp'\">See Database</button>");
            e.printStackTrace();
        }
    }
}
