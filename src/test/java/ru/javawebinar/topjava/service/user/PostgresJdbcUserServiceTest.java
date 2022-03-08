package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "jdbc"})
public class PostgresJdbcUserServiceTest extends AbstractUserServiceTest {
}