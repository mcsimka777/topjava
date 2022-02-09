package ru.javawebinar.topjava.dao;

import org.jetbrains.annotations.NotNull;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMealDao implements MealDao {
    private AtomicInteger counter = new AtomicInteger();

    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        MealsUtil.meals.forEach(this::create);
    }

    @Override
    public Meal create(Meal meal) {
        int id = counter.getAndIncrement();
        meal.setId(id);
        mealMap.put(id, meal);
        return mealMap.get(id);
    }

    @Override
    public Meal get(@NotNull Integer id) {
        return mealMap.get(id);
    }

    @Override
    public void delete(@NotNull Integer id) {
        mealMap.remove(id);
    }

    @Override
    public Meal update(Meal meal) {
        mealMap.replace(meal.getId(), meal);
        return mealMap.get(meal.getId());
    }

    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }
}
