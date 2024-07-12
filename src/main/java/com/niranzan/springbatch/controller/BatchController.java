package com.niranzan.springbatch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loaders")
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;

    @Qualifier("personItemLoaderJob")
    private final Job personItemLoaderJob;

    @Qualifier("customerItemLoaderJob")
    private final Job customerItemLoaderJob;

    @PostMapping("/persons")
    public ResponseEntity<String> loadPersonData() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(personItemLoaderJob, new JobParameters());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<String> loadCustomerData() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(customerItemLoaderJob, new JobParameters());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
