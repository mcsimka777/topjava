package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.AbstractMealServiceTest;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(value = Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
}