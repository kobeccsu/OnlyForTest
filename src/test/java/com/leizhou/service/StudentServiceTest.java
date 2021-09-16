package com.leizhou.service;

import com.leizhou.dto.MyFinal;
import com.leizhou.dto.Student;
import com.leizhou.enums.Gender;
import com.leizhou.exceptions.BadRequestException;
import com.leizhou.exceptions.StudentNotFoundException;
import com.leizhou.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

//        assertTimeout(Duration.ofMinutes(1));
    }

    @Mock
    Iterator<String> i;

    @Test
    void testMoreThanOneReturnValue(){
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");

        assertEquals("Mockito rocks", i.next() + " " + i.next());
    }

    @Test
    void testComparable(@Mock Comparable<String> para){
        when(para.compareTo("Mockito")).thenReturn(1);

        assertEquals(1, para.compareTo("Mockito"));


    }

    @Test
    void testComparableInteger(@Mock Comparable<Integer> para){
        when(para.compareTo(isA(Integer.class))).thenReturn(1);

        assertEquals(1, para.compareTo(4));
    }

    @Test
    void testProperties(){
        Properties properties = mock(Properties.class);
        when(properties.get(Mockito.anyString())).thenThrow(new IllegalArgumentException("Error"));

        Throwable a = assertThrows(IllegalArgumentException.class, () -> {
            properties.get("A");
        });

        assertEquals("Error", a.getMessage());
    }

    @Test
    void testSpyList(){
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Integer> spyList = spy(integers);

        doReturn(11).when(spyList).get(88);

        assertEquals(11, spyList.get(88));
    }

    @Spy
    List<String> spy = new LinkedList<>();

    @Test
    void testLinkedList(){
        doReturn("aaa").when(spy).get(0);

        assertEquals("aaa", spy.get(0));
    }

    @Test
    void testDoThrow9() throws IOException {
        OutputStream mockStream = mock(OutputStream.class);

        doThrow(new IOException()).when(mockStream).close();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mockStream);

        assertThrows(IOException.class, () -> outputStreamWriter.close());
    }

    @Captor
    ArgumentCaptor<List<String>> captor;

    @Test
    void shouldContainCaptureArg(@Mock List<String> listParam){
        List<String> strings = Arrays.asList("item1", "item2");
        listParam.addAll(strings);

        verify(listParam).addAll(captor.capture());

        List<String> value = captor.getValue();
        assertThat(value).contains("item2");

    }

    @Test
    void shouldGetFinalClass(){
        MockedStatic<MyFinal> myFinalMockedStatic = Mockito.mockStatic(MyFinal.class);

        myFinalMockedStatic.when(()-> MyFinal.getDatabaseConnection("abc")).thenReturn("123");

        assertNotNull(myFinalMockedStatic);

        assertEquals("123", MyFinal.getDatabaseConnection("abc"));
        myFinalMockedStatic.verifyNoMoreInteractions();
    }
}