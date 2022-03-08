package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "jpa"})
public class PostgresJpaUserServiceTest extends AbstractUserServiceTest {
}