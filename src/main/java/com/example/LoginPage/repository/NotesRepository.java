package com.example.LoginPage.repository;

import com.example.LoginPage.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    Notes findByTitle( final String title);
}
