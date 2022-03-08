package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "jdbc"})
public class PostgresJdbcMealServiceTest extends AbstractMealServiceTest {
}