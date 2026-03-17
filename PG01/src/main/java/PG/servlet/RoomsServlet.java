package PG.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import PG.model.RoomModel;
import PG.DBConnection;

@SuppressWarnings({ "unused", "serial" })
@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type"); // AC / Non-AC / Deluxe
        JSONArray roomsArray = new JSONArray();

        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT * FROM rooms WHERE room_type = ? AND is_available = TRUE";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Create model object
                RoomModel room = new RoomModel();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomType(rs.getString("room_type"));
                room.setAvailable(rs.getBoolean("is_available"));
                room.setPrice(rs.getDouble("price"));
                room.setBookedBy(rs.getString("booked_by"));

                // Convert to JSON object
                JSONObject jsonRoom = new JSONObject();
                jsonRoom.put("id", room.getId());
                jsonRoom.put("roomNumber", room.getRoomNumber());
                jsonRoom.put("roomType", room.getRoomType());
                jsonRoom.put("isAvailable", room.isAvailable());
                jsonRoom.put("price", room.getPrice());
                jsonRoom.put("bookedBy", room.getBookedBy());

                roomsArray.put(jsonRoom);
            }

            // Send JSON response
            response.getWriter().write(roomsArray.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
