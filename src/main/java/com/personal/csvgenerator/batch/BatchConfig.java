package com.personal.csvgenerator.batch;

import com.personal.csvgenerator.task.FetchDataTask;
import com.personal.csvgenerator.task.GenerateCSVTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.personal.csvgenerator.util.CSVGeneratorConstants.*;

@Slf4j
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FetchDataTask fetchDataTask;

    @Autowired
    private GenerateCSVTask generateCSVTask;

    @Bean
    public Job myJob(){
        return jobBuilderFactory.get(CSV_GENERATION_JOB)
                .start(fetchDataStep()).next(generateCSVStep())
                .build();
    }

    public Step fetchDataStep(){
        return stepBuilderFactory.get(FETCH_DATA_STEP).tasklet(fetchDataTask).build();
    }

    public Step generateCSVStep(){
        return stepBuilderFactory.get(GENERATE_CSV_STEP).tasklet(generateCSVTask).build();
    }
}
