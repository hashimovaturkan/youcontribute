package com.example.youcontribute.controllers.request;

import lombok.Data;

@Data
public class CreateRepositoryRequest {
    private String organization;
    private String repository;
}
