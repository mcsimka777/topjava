package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "jdbc"})
public class HsqldbJdbcMealServiceTest extends AbstractMealServiceTest {
}