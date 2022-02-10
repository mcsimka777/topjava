package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealDao implements MealDao {
    private AtomicInteger counter = new AtomicInteger();

    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counter.getAndIncrement());
        }
        return mealMap.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return mealMap.get(id);
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }
}
