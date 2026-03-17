package PG.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.Base64;

import PG.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/bookRoom")
@MultipartConfig
public class BookingServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");

int roomId = Integer.parseInt(request.getParameter("room_id"));
String customerName = request.getParameter("customer_name");
String email = request.getParameter("email");

String checkIn = request.getParameter("check_in_date");
String checkOut = request.getParameter("check_out_date");

Part filePart = request.getPart("id_proof");

String fileName = filePart.getSubmittedFileName();
String fileType = filePart.getContentType();

InputStream fileContent = filePart.getInputStream();

byte[] fileBytes = fileContent.readAllBytes();

String encodedFile = Base64.getEncoder().encodeToString(fileBytes);

try(Connection con = DBConnection.getConnection()){

String sql = "INSERT INTO bookings (room_id,customer_name,email,check_in_date,check_out_date,file_name,file_type,file_data,confirmed) VALUES (?,?,?,?,?,?,?,?,FALSE)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setInt(1,roomId);
ps.setString(2,customerName);
ps.setString(3,email);
ps.setString(4,checkIn);
ps.setString(5,checkOut);
ps.setString(6,fileName);
ps.setString(7,fileType);
ps.setString(8,encodedFile);

ps.executeUpdate();

response.getWriter().println("<script>alert('Booking Successful');window.location='index.html';</script>");

}catch(Exception e){
e.printStackTrace();
}

}
}