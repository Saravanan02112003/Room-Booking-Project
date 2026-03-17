package PG.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import PG.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/deleteBooking")
public class DeleteBookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false); // Start transaction

            // 1️⃣ Get room_id of this booking
            long roomId = -1;
            try (PreparedStatement ps1 = con.prepareStatement(
                    "SELECT room_id FROM bookings WHERE id=?")) {
                ps1.setInt(1, id);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) {
                        roomId = rs.getLong("room_id");
                    }
                }
            }

            // 2️⃣ Delete booking from bookings table
            try (PreparedStatement ps2 = con.prepareStatement(
                    "DELETE FROM bookings WHERE id=?")) {
                ps2.setInt(1, id);
                ps2.executeUpdate();
            }

            // 3️⃣ Reset that room's status in rooms table
            if (roomId != -1) {
                try (PreparedStatement ps3 = con.prepareStatement(
                        "UPDATE rooms SET is_available = TRUE, booked_by = NULL, check_in_date = NULL, check_out_date = NULL WHERE id = ?")) {
                    ps3.setLong(1, roomId);
                    ps3.executeUpdate();
                }
            }

            con.commit(); // ✅ Everything successful
            resp.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
