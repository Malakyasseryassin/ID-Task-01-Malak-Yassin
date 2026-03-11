package s55_3546.t_09.malak_yassin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

import s55_3546.t_09.malak_yassin.models.Note;
import s55_3546.t_09.malak_yassin.repositories.NoteRepository;
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    public Note getNoteById(String id) {

        return noteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<Note> getNotesByUserId(String userId) {

        List<Note> notes = noteRepository.findByUserId(userId);

        if (notes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return notes;
    }
    public Note createNote(Note note) throws Exception {
        return noteRepository.save(note);
    }
    public Note updateNote(String id, Note note) throws Exception {

        return noteRepository.update(id, note)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public void deleteNote(String id) throws Exception {

        boolean deleted = noteRepository.deleteById(id);

        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
