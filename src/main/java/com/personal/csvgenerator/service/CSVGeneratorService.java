package com.personal.csvgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.personal.csvgenerator.model.Posts;
import com.personal.csvgenerator.util.CSVGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.personal.csvgenerator.util.CSVGeneratorConstants.*;

@Slf4j
@Service
public class CSVGeneratorService {

    private RestTemplate restTemplate = new RestTemplate();

    public List<Posts> fetchURIData(){
        String URI = "https://jsonplaceholder.typicode.com/posts";
        Posts[] response = restTemplate.getForEntity(URI, Posts[].class).getBody();
        log.info("Response :: {}", response);
        return Arrays.asList(response);
    }

    public void generateCSVFile(List<Posts> posts) throws IOException {
        log.info("Posts List :: {}", posts.size());
        ArrayList<String> headers = getHeaders();
        ArrayList<ArrayList<String>> postContent = getPostsStr(posts);
        CSVGeneratorUtil.generateFile(POST_CSV_FILE, headers, postContent);
    }

    public ArrayList<ArrayList<String>> getPostsStr(List<Posts> postList){
        ArrayList<ArrayList<String>> postsStr = new ArrayList<>();
        for(Posts post : postList){
            postsStr.add(getPostsStringArray(post));
        }
        return postsStr;
    }

    public ArrayList<String> getPostsStringArray(Posts posts){
        ArrayList<String> list = new ArrayList<>();
        list.add(posts.getUserId());
        list.add(posts.getId());
        list.add(posts.getTitle());
        list.add(posts.getBody());
        return list;
    }

    public ArrayList<String> getHeaders(){
        ArrayList<String> headers = new ArrayList<>();
        headers.add(USER_ID);
        headers.add(ID);
        headers.add(TITLE);
        headers.add(BODY);
        return headers;
    }
}
