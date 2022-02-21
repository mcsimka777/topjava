package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 3;
    public static final int MEAL2_ID = START_SEQ + 4;
    public static final int MEAL3_ID = START_SEQ + 5;
    public static final int MEAL4_ID = START_SEQ + 6;
    public static final int MEAL5_ID = START_SEQ + 7;
    public static final int MEAL6_ID = START_SEQ + 8;
    public static final int MEAL7_ID = START_SEQ + 9;

    public static final Meal meal1 = new Meal(MEAL1_ID, LocalDateTime.parse("2020-01-30 10:00"), "Завтрак", 500);
    public static final Meal meal2 = new Meal(MEAL2_ID, LocalDateTime.parse("2020-01-30 13:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), "'Обед'", 1000);
    public static final Meal meal3 = new Meal(MEAL3_ID, LocalDateTime.parse("2020-01-30 20:00"), "'Ужин", 500);
    public static final Meal meal4 = new Meal(MEAL4_ID, LocalDateTime.parse("2020-01-31 00:00"), "Еда на граничное значение", 100);
    public static final Meal meal5 = new Meal(MEAL5_ID, LocalDateTime.parse("2020-01-31 10:00"), "Завтрак", 1000);
    public static final Meal meal6 = new Meal(MEAL6_ID, LocalDateTime.parse("2020-01-31 13:00"), "Обед", 500);
    public static final Meal meal7 = new Meal(MEAL7_ID, LocalDateTime.parse("2020-01-31 20:00"), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "Новая еда", 100);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal2);
        updated.setDateTime(LocalDateTime.parse("2020-01-29 12:00"));
        updated.setCalories(99);
        updated.setDescription("Апдейт еды");
        return updated;
    }
}
