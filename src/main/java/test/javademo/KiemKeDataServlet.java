package test.javademo;

import Dto.KiemKeDto;
import Mapper.KiemKeMapper;
import Model.KiemKe;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "kiemKeDataServlet", value = "/kiem-ke-data")
public class KiemKeDataServlet extends HttpServlet {

    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createQuery("SELECT s FROM KiemKe s");
            List<KiemKe> infoList = query.getResultList();
            List<KiemKeDto> dtoList = KiemKeMapper.mapToDtoList(infoList);

            String jsonString = gson.toJson(dtoList);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
