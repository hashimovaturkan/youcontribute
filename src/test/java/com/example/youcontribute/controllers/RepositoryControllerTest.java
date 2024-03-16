package com.example.youcontribute.controllers;

import com.example.youcontribute.models.Repository;
import com.example.youcontribute.controllers.request.CreateRepositoryRequest;
import com.example.youcontribute.service.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepositoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RepositoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    public void  it_should_list_repositories() throws Exception {
        Repository repository = Repository.builder().organization("hashimovaturkan").repository("youcontribute").build();
        given(repositoryService.list()).willReturn(Collections.singletonList(repository));

        mockMvc.perform(get("/repositories")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("youcontribute"))
                .andExpect(jsonPath("$[0].organization").value("hashimovaturkan"));
    }

    @Test
    public void  it_should_create_repository() throws Exception {

        String organization = "github";
        String repository = "octocat";

        CreateRepositoryRequest request = CreateRepositoryRequest.builder().organization(organization).repository(repository).build();
        doNothing().when(repositoryService).create(organization,repository);

        mockMvc.perform(post("/repositories").content(objectMapper.writeValueAsBytes(request)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated() );

    }


}