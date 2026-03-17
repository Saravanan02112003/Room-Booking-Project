package PG.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

import PG.DBConnection;

@WebServlet("/adminBookings")
public class AdminBookingServlet extends HttpServlet {

protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{

resp.setContentType("application/json");

JSONArray arr = new JSONArray();

try(Connection con = DBConnection.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM bookings")){

while(rs.next()){

JSONObject obj = new JSONObject();

obj.put("id",rs.getInt("id"));
obj.put("roomId",rs.getInt("room_id"));
obj.put("customerName",rs.getString("customer_name"));
obj.put("checkInDate",rs.getString("check_in_date"));
obj.put("checkOutDate",rs.getString("check_out_date"));

arr.put(obj);

}

}catch(Exception e){
e.printStackTrace();
}

resp.getWriter().print(arr);

}

}