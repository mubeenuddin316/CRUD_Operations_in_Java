package pack1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/FullDServlet51")
public class FullDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FullDServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

        servDAO dao = new servDAO();
        ArrayList<servDTO> dataList = new ArrayList<>();
        
        try {
			dataList = dao.getData();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("dataList", dataList);
        RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
        rd.forward(request, response);
	}

}
