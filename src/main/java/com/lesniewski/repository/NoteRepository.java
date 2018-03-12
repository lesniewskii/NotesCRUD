package com.lesniewski.repository;

import com.lesniewski.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adrian on 3/7/2018.
 */
@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

}
