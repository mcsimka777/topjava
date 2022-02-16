package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return (repository.get(meal.getId()).getUserId() == userId
                ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal)
                : null);
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = repository.get(id);
        return (meal.getUserId() == userId && repository.remove(id).equals(meal));
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        return (meal.getUserId() == userId ? meal : null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFilterList(userId, meal -> true);
    }

    public List<Meal> getFiltered(int userId, LocalDate startDate, LocalDate endDate) {
        return getFilterList(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), startDate, endDate));
    }

    private List<Meal> getFilterList(int userId, Predicate<Meal> filter) {
        return repository.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .filter(filter)
                .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime)))
                .collect(Collectors.toList());
    }
}

