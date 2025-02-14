package pack1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class servDAO {
	
    // public static Connection getConnection() throws ClassNotFoundException, SQLException {
    //     Class.forName("com.mysql.cj.jdbc.Driver");  // method to get connection
    //     return DriverManager.getConnection("jdbc:mysql://localhost:3306/srdb5", "root", "root");
    // }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
   	 	Class.forName("org.sqlite.JDBC");  // Load SQLite driver
    		return DriverManager.getConnection("jdbc:sqlite:database.db"); // Path to SQLite DB file
	}



    public ArrayList<servDTO> getData() throws ClassNotFoundException, SQLException {
        ArrayList<servDTO> dataList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection(); //connection method call
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students"); // query to get full data

            while (rs.next()) {
                servDTO dto = new servDTO(); //dto class object to set data using setters
                dto.setId(rs.getInt("id")); // getInt, getString, getLong for getting data from database
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setPhone(rs.getLong("phone"));
                dataList.add(dto);
            }
        } finally {
            if (rs != null) rs.close(); //closing statemts and connection
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        
        return dataList;
    }
    
    public ArrayList<servDTO> getUserD(int id) throws ClassNotFoundException, SQLException { //Full Data method (index.jsp / FullDServlet.java)
        ArrayList<servDTO> dataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM students WHERE id=?"); // query to retrieve data from db of id
            pstmt.setInt(1, id); // id searching in column 1         
            rs = pstmt.executeQuery();

            while (rs.next()) {
                servDTO dt = new servDTO();
                dt.setId(rs.getInt("id"));
                dt.setName(rs.getString("name"));
                dt.setEmail(rs.getString("email"));
                dt.setPhone(rs.getLong("phone"));
                dataList.add(dt);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return dataList;
    }

	public int insertData(servDTO dto) { //insert data method (insert.jsp / insertServlet.java)
		int status = 0;
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO students VALUES(?,?,?,?)"); // insert data query
            pstmt.setInt(1, dto.getId());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getEmail());
            pstmt.setLong(4, dto.getPhone());
            status = pstmt.executeUpdate(); // setting the execute udate log value in status
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
		
	}

}
		return status;
        
}
	public int deleteUser(int id) { //delete record method by id (index.jsp / DelRecServlet.java)
	    int status = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement("DELETE FROM students WHERE id=?"); // delete record of id from students table
	        pstmt.setInt(1, id);
	        status = pstmt.executeUpdate();
	    } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }
	    return status;
	}

	public servDTO getById(int id) throws SQLException, ClassNotFoundException { // this method gets data from db of given id from ui and 
        Connection conn = null;													// sets it into dto using setters and returrns dto object
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        servDTO dto = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM students WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto = new servDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                long phoneValue = rs.getLong("phone");
                dto.setPhone(rs.wasNull() ? null : phoneValue);
            }
        } finally {
        	try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        }
        return dto;
    }

    public int updateData(servDTO dto) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int status = 0;
        try {
            conn = getConnection();
            String sql = "UPDATE students SET name=?, email=?, phone=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            if (dto.getPhone() != null) {
                pstmt.setLong(3, dto.getPhone());
            } else {
                pstmt.setNull(3, Types.BIGINT);
            }
            pstmt.setInt(4, dto.getId());
            status = pstmt.executeUpdate();
        } finally {
        	try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        }
        return status;
    }

}
