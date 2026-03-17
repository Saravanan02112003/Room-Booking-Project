package PG.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import PG.dao.UserDAO;
import PG.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings({ "serial" })
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, email, password);
        UserDAO dao = new UserDAO();

        boolean isRegistered = dao.registerUser(user);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (isRegistered) {
            out.println("<script>alert('Registration Successful!'); window.location='login.html';</script>");
        } else {
            out.println("<script>alert('Registration Failed! Try again.'); window.location='register.html';</script>");
        }
    }
}
