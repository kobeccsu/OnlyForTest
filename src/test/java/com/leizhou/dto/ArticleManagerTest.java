package com.leizhou.dto;

import javafx.beans.binding.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleManagerTest {

    @InjectMocks
    private ArticleManager manager;

    @Mock
    ArticleDatabase database;

    @Mock
    User1 user;

    @Test
    void ensureInjectMockWorks() {

        // calls addListener with an instance of ArticleListener
        manager.initialize();
        verify(database).addListener(any(ArticleListener.class));
    }
}