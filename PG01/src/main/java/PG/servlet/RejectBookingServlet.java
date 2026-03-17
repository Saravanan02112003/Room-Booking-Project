package PG.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

import PG.DBConnection;
import PG.util.EmailUtil;

@WebServlet("/rejectBooking")
public class RejectBookingServlet extends HttpServlet {

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws IOException{

int id=Integer.parseInt(request.getParameter("id"));

try(Connection con=DBConnection.getConnection()){

String sql="SELECT email,customer_name,room_id FROM bookings WHERE id=?";

PreparedStatement ps=con.prepareStatement(sql);

ps.setInt(1,id);

ResultSet rs=ps.executeQuery();

if(rs.next()){

String email=rs.getString("email");
String name=rs.getString("customer_name");
int roomId=rs.getInt("room_id");

EmailUtil.sendRejectEmail(email,name,roomId);

}

}catch(Exception e){
e.printStackTrace();
}

}
}