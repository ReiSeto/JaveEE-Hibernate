package test.javademo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "phieuKiemKeServlet", value = "/phieu-kiem-ke")
public class PhieuKiemKeServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PhieuKiemKe.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}