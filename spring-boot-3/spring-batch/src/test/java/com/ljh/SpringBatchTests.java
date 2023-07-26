package com.ljh;

import com.ljh.config.BatchConfiguration;
import com.ljh.listener.JobCompletionNotificationListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * <pre>
 * <a href="https://www.baeldung.com/spring-boot-spring-batch">Spring Boot With Spring Batch</a>
 * <a href="https://www.baeldung.com/tag/spring-batch">Spring Batch | Baeldung</a>
 * <a href="https://blog.csdn.net/masson32/category_9008923.html">Spring Batch入门与实践</a>
 * </pre>
 *
 * @author ljh
 * @since 2023/7/26 9:37
 */
@SpringBatchTest
@DirtiesContext
@SpringJUnitConfig(BatchConfiguration.class)
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
public class SpringBatchTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @MockBean
    private JobCompletionNotificationListener jobCompletionNotificationListener;

    @AfterEach
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void givenCoffeeList_whenJobExecuted_thenSuccess() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();
        ExitStatus jobExitStatus = jobExecution.getExitStatus();

        Assertions.assertEquals("importUserJob", jobInstance.getJobName());
        Assertions.assertEquals("COMPLETED", jobExitStatus.getExitCode());
    }
}
