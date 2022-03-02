package ru.javawebinar.topjava.web.user;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TestTiming {
    private static final Logger log = LoggerFactory.getLogger("");

    private static final StringBuilder results = new StringBuilder();

    public static final Stopwatch STOPWATCH = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%s - %d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append("ms").append('\n');
            log.info(result + " ms\n");
        }
    };

    public static final ExternalResource SUMMARY = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            results.setLength(0);
        }

        @Override
        protected void after() {
            log.info("Timing summary:\n" + results + "\n");
        }
    };
}
