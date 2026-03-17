package PG.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Base64;

import PG.DBConnection;

@SuppressWarnings("serial")
@WebServlet("/viewFile")
public class ViewFileServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

int id = Integer.parseInt(request.getParameter("id"));

try(Connection con = DBConnection.getConnection()){

String sql = "SELECT file_type,file_data FROM bookings WHERE id=?";

PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1,id);

ResultSet rs = ps.executeQuery();

if(rs.next()){

String fileType = rs.getString("file_type");
String encodedFile = rs.getString("file_data");

byte[] decodedBytes = Base64.getDecoder().decode(encodedFile);

response.setContentType(fileType);

OutputStream os = response.getOutputStream();
os.write(decodedBytes);
os.flush();

}

}catch(Exception e){
e.printStackTrace();
}

}

}