package com.personal.csvgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts implements Serializable {
    private String userId;
    private String id;
    private String title;
    private String body;}
