package com.leadtech.lookbooks.service;

import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.repository.ILookbookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LookbookServiceTest {

    @InjectMocks
    private LookbookService lookbookService;

    @Mock
    private ILookbookRepository lookbookRepository;

    @Test
    void testFindAllLookbooks_Success() {
        // Arrange
        List<Lookbook> lookbooks = new ArrayList<>();
        lookbooks.add(new Lookbook());
        when(lookbookRepository.findAll()).thenReturn(lookbooks);

        // Act
        List<Lookbook> result = lookbookService.findAllLookbooks();

        // Assert
        assertEquals(1, result.size());
        verify(lookbookRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdLookbook_Success() {
        // Arrange
        Lookbook lookbook = new Lookbook();
        when(lookbookRepository.findById(anyLong())).thenReturn(Optional.of(lookbook));

        // Act
        Lookbook result = lookbookService.findByIdLookbook(1L);

        // Assert
        assertNotNull(result);
        verify(lookbookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdLookbook_Failure() {
        // Arrange
        when(lookbookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Lookbook result = lookbookService.findByIdLookbook(1L);

        // Assert
        assertNull(result);
        verify(lookbookRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveLookbook_Success() {
        // Arrange
        Lookbook lookbook = new Lookbook();

        // Act
        lookbookService.saveLookbook(lookbook);

        // Assert
        verify(lookbookRepository, times(1)).save(lookbook);
    }

    @Test
    void testDeleteByIdLookbook_Success() {
        // Arrange
        Lookbook lookbook = new Lookbook();
        lenient().when(lookbookRepository.findById(anyLong())).thenReturn(Optional.of(lookbook));

        // Act
        lookbookService.deleteByIdILookbook(1L);

        // Assert
        verify(lookbookRepository, times(1)).deleteById(1L);
    }

}
