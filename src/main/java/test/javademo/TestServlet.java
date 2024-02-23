package test.javademo;

import Dto.InputInfoDto;
import Mapper.InputInfoMapper;
import Model.InputInfo;
import Model.KiemKe;
import Model.KiemKeInfo;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "testServlet", value = "/test")
public class TestServlet extends HttpServlet {

    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createQuery("SELECT s FROM InputInfo s");
            List<InputInfo> infoList = query.getResultList();
            List<InputInfoDto> dtoList = InputInfoMapper.mapToDtoList(infoList);

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String data = sb.toString();
        JSONObject jsonObj = new JSONObject(data);

        try {
            transaction.begin();
            String name = jsonObj.getString("name");
            JSONArray values = jsonObj.getJSONArray("values");
            KiemKe kiemKe = new KiemKe(name);

            for (int i = 0; i < values.length(); i++) {
                int objectId = Integer.parseInt(values.getJSONObject(i).getString("id"));
                int input_id = Integer.parseInt(values.getJSONObject(i).getString("input"));
                int quantity = Integer.parseInt(values.getJSONObject(i).getString("quantity"));
                int newQuantity = Integer.parseInt(values.getJSONObject(i).getString("newQuantity"));

                InputInfo inputInfo = entityManager.find(InputInfo.class, input_id);
                if (inputInfo == null) {
                    continue;
                }

                KiemKeInfo kiemKeInfo = KiemKeInfo.builder()
                        .input(inputInfo)
                        .quantity(quantity)
                        .trueQuantity(newQuantity)
                        .build();
                kiemKe.addInfo(kiemKeInfo);

                if (quantity != newQuantity) {
                    Query query = entityManager.createQuery("update InputInfo m set m.quantity = :quantity where m.id = :id");
                    query.setParameter("id", objectId);
                    query.setParameter("quantity", newQuantity);
                    query.executeUpdate();
                }
            }

            entityManager.persist(kiemKe);
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

    public void destroy() {
    }
}