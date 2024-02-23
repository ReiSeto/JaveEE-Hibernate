package test.javademo;

import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "kiemkeServlet", value = "/kiem-ke")
public class KiemKeServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        int objectId = Integer.parseInt(request.getParameter("id"));
        int objectQuantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            transaction.begin();

            if (objectQuantity < 1) {
                Query query = entityManager.createQuery("delete from InputInfo where id = :id");
                query.setParameter("id", objectId);
                query.executeUpdate();

            } else {
                Query query = entityManager.createQuery("update InputInfo m set m.quantity = :quantity where m.id = :id");
                query.setParameter("id", objectId);
                query.setParameter("quantity", objectQuantity);
                query.executeUpdate();
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        response.sendRedirect("/kiemke");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("KiemKe.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}