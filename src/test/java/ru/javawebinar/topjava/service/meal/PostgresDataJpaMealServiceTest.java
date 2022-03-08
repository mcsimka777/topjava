package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "datajpa"})
public class PostgresDataJpaMealServiceTest extends AbstractMealServiceTest {
}