package PG.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import PG.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/updateBooking")
public class UpdateBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String newDate = req.getParameter("check_out_date");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE bookings SET check_out_date=? WHERE id=?")) {
            ps.setString(1, newDate);
            ps.setInt(2, id);
            ps.executeUpdate();
            resp.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
