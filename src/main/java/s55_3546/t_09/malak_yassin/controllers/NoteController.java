package s55_3546.t_09.malak_yassin.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import s55_3546.t_09.malak_yassin.models.Note;
import s55_3546.t_09.malak_yassin.services.NoteService;
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }
    @PostMapping
    public Note createNote(@RequestBody Note note) throws Exception {
        return noteService.createNote(note);
    }
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String title) {
        return noteService.searchNotesByTitle(title);
    }
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id,
                           @RequestBody Note note) throws Exception {

        return noteService.updateNote(id, note);
    }
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id) throws Exception {
        noteService.deleteNote(id);
    }
}
