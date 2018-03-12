package com.lesniewski.controller;


import com.lesniewski.exception.ResourceNotFoundException;
import com.lesniewski.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lesniewski.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Notes> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Notes createNote(@Valid @RequestBody Notes note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Notes getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Notes updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Notes noteDetails) {

        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Notes updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Notes note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
