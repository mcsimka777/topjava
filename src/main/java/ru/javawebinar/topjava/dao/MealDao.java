package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void create(Meal meal);

    Meal get(Integer id);

    void delete(Integer id);

    void update(Integer id, Meal meal);

    List<Meal> getAll();
}
