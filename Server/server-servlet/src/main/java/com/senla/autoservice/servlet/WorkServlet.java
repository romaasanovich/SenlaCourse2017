package com.senla.autoservice.servlet;

import com.google.gson.Gson;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.facade.Autoservice;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/work")

public class WorkServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Work> works = Autoservice.getInstance().getWorks();
        if (works != null && !works.isEmpty()) {
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            String s = new Gson().toJson(works, ArrayList.class);
            JSONArray jsonObject = new JSONArray(s);
            out.print(jsonObject);
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        } else {
            resp.getWriter().println("Empty base");
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                Work work = Autoservice.getInstance().getWorkById(Integer.parseInt(id));
                String s = new Gson().toJson(work, Work.class);
                JSONObject jsonObject = new JSONObject(s);
                out.print(jsonObject);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (Exception ignored) {
            }
        }
        resp.getWriter().println("Wrong request");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }


    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String idMaster = req.getParameter("idMaster");
        String name = req.getParameter("nameService");
        String price = req.getParameter("price");

        if (idMaster != null && !idMaster.isEmpty() &&
                name!= null && !name.isEmpty() &&
                price != null && !price.isEmpty()) {
            try {
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                Autoservice.getInstance().addWorkToMaster(name,Double.parseDouble(price),Integer.parseInt(idMaster));
                out.print("OK");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                return;
            } catch (Exception ignored) {
            }
        }
        resp.getWriter().println("Wrong request");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}
