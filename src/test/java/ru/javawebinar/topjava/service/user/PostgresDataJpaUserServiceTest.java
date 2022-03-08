package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "datajpa"})
public class PostgresDataJpaUserServiceTest extends AbstractUserServiceTest {
}