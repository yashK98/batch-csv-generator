package com.personal.csvgenerator.task;

import com.personal.csvgenerator.model.Posts;
import com.personal.csvgenerator.service.CSVGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.personal.csvgenerator.util.CSVGeneratorConstants.POSTS_RESPONSE;

@Slf4j
@Service
public class FetchDataTask implements Tasklet {

    @Autowired
    private CSVGeneratorService csvGeneratorService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("Fetching Data for CSV");
        List<Posts> response = csvGeneratorService.fetchURIData();
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put(POSTS_RESPONSE, response);
        return RepeatStatus.FINISHED;
    }
}
