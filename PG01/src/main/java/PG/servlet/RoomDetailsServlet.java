package PG.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import org.json.JSONObject;
import PG.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/roomDetails")
public class RoomDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        String id = request.getParameter("id");

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM rooms WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JSONObject room = new JSONObject();
                room.put("id", rs.getInt("id"));
                room.put("roomNumber", rs.getString("room_number"));
                room.put("roomType", rs.getString("room_type"));
                room.put("price", rs.getDouble("price"));
                room.put("isAvailable", rs.getBoolean("is_available"));
                response.getWriter().write(room.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
