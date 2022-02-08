package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImplMap implements MealDao {
    private static AtomicInteger countId = new AtomicInteger(-1);

    private Map<Integer, Meal> mealList = new ConcurrentHashMap<>();

    {
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void create(Meal meal) {
        int id = countId.incrementAndGet();
        meal.setId(id);
        mealList.put(id, meal);
    }

    @Override
    public Meal get(Integer id) {
        return mealList.get(id);
    }

    @Override
    public void delete(Integer id) {
        mealList.remove(id);
    }

    @Override
    public void update(Integer id, Meal meal) {
        meal.setId(id);
        mealList.replace(id, meal);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(mealList.values());
    }
}
