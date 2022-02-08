package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImplMap;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.GetFilterAll;
import ru.javawebinar.topjava.util.GetFilterByTime;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.SearchMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final String INSERT_OR_EDIT = "/meal.jsp";
    private final String LIST_USER = "/meals.jsp";
    private final SearchMethod SEARCH_ALL = new GetFilterAll();
    private final SearchMethod SEARCH_BY_TIME = new GetFilterByTime();
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private final MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDaoImplMap();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        action = (action == null ? "main" : action);
        switch (action.toLowerCase()) {
            case "insert":
                forward = INSERT_OR_EDIT;
                break;
            case "edit":
                forward = INSERT_OR_EDIT;
                request.setAttribute("meal", dao.get(Integer.valueOf(request.getParameter("id"))));
                log.debug("EDIT: " + dao.get(Integer.valueOf(request.getParameter("id"))));
                break;
            case "delete":
                log.debug("DELETE: " + dao.get(Integer.valueOf(request.getParameter("id"))));
                dao.delete(Integer.valueOf(request.getParameter("id")));
                response.sendRedirect("meals");
                return;
            default:
                log.debug("redirect to meals");
                forward = LIST_USER;
                MealsUtil.setSearchMethod(SEARCH_ALL);
                List<MealTo> mealsTo = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.of(7, 0), LocalTime.of(12, 0), MealsUtil.CALORIES_PER_DAY);
                request.setAttribute("mealsTo", mealsTo);
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        try {
            meal.setDateTime(LocalDateTime.parse(req.getParameter("date"), formatter));
            meal.setCalories(Integer.parseInt(req.getParameter("calories")));
            meal.setDescription(req.getParameter("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String id = req.getParameter("id");
        if (id != null) {
            if (id.equals("")) {
                dao.create(meal);
                log.debug("INSERT: " + meal);
            } else {
                dao.update(Integer.parseInt(id), meal);
                log.debug("UPDATE: " + meal);
            }
        } else {
            dao.create(meal);
            log.debug("INSERT: " + meal);
        }
        resp.sendRedirect("meals");
    }
}
