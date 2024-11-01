package com.leadtech.lookbooks.repository;

import com.leadtech.lookbooks.model.Lookbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILookbookRepository extends JpaRepository<Lookbook, Long> {
}
