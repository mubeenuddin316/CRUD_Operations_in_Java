package pack1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchServlet21")
public class searchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public searchServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ArrayList<servDTO> dataList = new ArrayList<>();
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            request.setAttribute("error", "Invalid ID! Please enter a valid number.");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                servDAO dao = new servDAO();
                dataList = dao.getUserD(id);
                
                if (dataList.isEmpty()) { // if dattalist is empty then setting attribute error and id
                    request.setAttribute("error", "No record found for ID: " + id);
                }
	            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid ID format! Please enter a number.");
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("error", "Database error: " + e.getMessage());
                e.printStackTrace();
            }
        }
	    request.setAttribute("dataList", dataList);
        RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
        rd.forward(request, response);
    }
}
