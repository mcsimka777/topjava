package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "jpa"})
public class PostgresJpaMealServiceTest extends AbstractMealServiceTest {
}