package com.leizhou.service;

import com.leizhou.dto.Student;
import com.leizhou.enums.Gender;
import com.leizhou.exceptions.BadRequestException;
import com.leizhou.exceptions.StudentNotFoundException;
import com.leizhou.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void shouldAddStudent() {
        Student student = new Student("lei", "lei_zhou@epam.com", Gender.MALE);

        underTest.addStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void shouldThrowException() {
        Student student = new Student("lei", "lei_zhou@epam.com", Gender.MALE);

        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);

        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldDeleteStudent() {
        Student student = new Student("lei", "lei_zhou@epam.com", Gender.MALE);
        student.setId(100L);

        given(studentRepository.existsById(student.getId())).willReturn(true);


        underTest.deleteStudent(student.getId());

        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    void shouldThrowExceptionWhenDeleteStudent() {
        Student student = new Student("lei", "lei_zhou@epam.com", Gender.MALE);
        student.setId(100L);

        given(studentRepository.existsById(student.getId())).willReturn(false);

        assertThatThrownBy(()-> underTest.deleteStudent(student.getId()))
                .isInstanceOf(StudentNotFoundException.class)
                        .hasMessageContaining("Student with id " + student.getId() + " does not exists");

        verify(studentRepository, never()).deleteById(student.getId());
    }
}