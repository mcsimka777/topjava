package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "jpa"})
public class HsqldbJpaMealServiceTest extends AbstractMealServiceTest {
}