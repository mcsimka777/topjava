package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "jdbc"})
public class HsqldbJdbcUserServiceTest extends AbstractUserServiceTest {
}