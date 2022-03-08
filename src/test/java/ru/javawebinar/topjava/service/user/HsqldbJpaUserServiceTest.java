package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "jpa"})
public class HsqldbJpaUserServiceTest extends AbstractUserServiceTest {
}