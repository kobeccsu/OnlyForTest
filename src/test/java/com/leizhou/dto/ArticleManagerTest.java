package com.leizhou.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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