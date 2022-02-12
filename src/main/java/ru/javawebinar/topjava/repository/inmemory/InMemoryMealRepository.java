package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
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
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return (meal.getUserId().equals(userId)
                ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal)
                : null);
    }

    @Override
    public boolean delete(int id, int userId) {
        Predicate<Meal> toRemove = meal -> meal.getId().equals(id)
                && meal.getUserId().equals(userId);
        return repository.values().removeIf(toRemove);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.values().stream()
                .filter(meal -> meal.getId().equals(id))
                .filter(user -> user.getUserId().equals(userId))
                .findAny().orElse(null);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .sorted((d1, d2) -> d2.getDateTime().compareTo(d1.getDateTime()))
                .collect(Collectors.toList());
    }
}

