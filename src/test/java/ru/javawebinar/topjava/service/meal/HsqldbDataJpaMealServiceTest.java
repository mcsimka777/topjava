package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "datajpa"})
public class HsqldbDataJpaMealServiceTest extends AbstractMealServiceTest {
}