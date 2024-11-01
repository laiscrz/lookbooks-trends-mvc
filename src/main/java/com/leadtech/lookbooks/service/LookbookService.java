package com.leadtech.lookbooks.service;

import com.leadtech.lookbooks.model.Lookbook;
import com.leadtech.lookbooks.repository.ILookbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookbookService {
    @Autowired
    private ILookbookRepository lookbookRepository;

    // Retorna todos
    public List<Lookbook> findAllLookbooks() {
        return lookbookRepository.findAll();
    }

    // Busca pelo ID
    public Lookbook findByIdLookbook(Long id) {
        return lookbookRepository.findById(id).orElse(null);
    }

    // Salva ou atualiza
    public void saveLookbook(Lookbook lookbook) {
        lookbookRepository.save(lookbook);
    }

    // Remove pelo ID
    public void deleteByIdILookbook(Long id) {
        lookbookRepository.deleteById(id);
    }
}
