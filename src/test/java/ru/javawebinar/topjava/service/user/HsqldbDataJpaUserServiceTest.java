package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"hsqldb", "datajpa"})
public class HsqldbDataJpaUserServiceTest extends AbstractUserServiceTest {
}