package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MapMealDao;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final String INSERT_OR_EDIT = "/meal.jsp";
    private final String LIST_MEAL = "/meals.jsp";
    private MealDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new MapMealDao();
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
                request.setAttribute("meal", dao.get(parseId(request)));
                log.debug("EDIT {} ", dao.get(parseId(request)));
                break;
            case "delete":
                log.debug("DELETE {}", dao.get(parseId(request)));
                dao.delete(parseId(request));
                response.sendRedirect("meals");
                return;
            default:
                log.debug("redirect to meals");
                forward = LIST_MEAL;
                List<MealTo> mealsTo = MealsUtil.unfiltered(dao.getAll(), MealsUtil.CALORIES_PER_DAY);
                request.setAttribute("mealsTo", mealsTo);
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(LocalDateTime.parse(req.getParameter("date"))
                , req.getParameter("description")
                , Integer.parseInt(req.getParameter("calories"))
        );
        String id = req.getParameter("id");
        if (id.isEmpty()) {
            Meal newMeal = dao.create(meal);
            log.debug("INSERT {}", newMeal);
        } else {
            meal.setId(parseId(req));
            dao.update(meal);
            log.debug("UPDATE {}", meal);
        }
        resp.sendRedirect("meals");
    }

    private int parseId(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("id"));
    }
}
