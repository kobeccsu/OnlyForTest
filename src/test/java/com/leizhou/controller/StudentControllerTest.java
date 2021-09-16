package com.leizhou.controller;

import com.leizhou.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void getAllStudents() throws Exception {
        when(studentService.getAllStudents())
                .thenReturn(null);
        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk());

        //verify(studentService).getAllStudents();
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }
}