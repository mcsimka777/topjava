package ru.javawebinar.topjava.dao;

import org.jetbrains.annotations.NotNull;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal create(Meal meal);

    Meal get(@NotNull Integer id);

    void delete(@NotNull Integer id);

    Meal update(Meal meal);

    List<Meal> getAll();
}
