package s55_3546.t_09.malak_yassin.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import s55_3546.t_09.malak_yassin.models.Note;

@Repository
public class NoteRepository {
    private List<Note> notes;
    private java.io.File jsonFile;

    public NoteRepository() {

        InputStream inputStream = getClass().getResourceAsStream("/notes.json");

        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read notes.json");
        }

        try {
            this.jsonFile = new java.io.File(getClass().getResource("/notes.json").toURI());
        } catch (Exception e) {
            this.jsonFile = new java.io.File("/data/notes.json");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.notes = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Note>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Note> findAll() {
        return notes;
    }
    public Optional<Note> findById(String id) {

        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }
    public List<Note> findByUserId(String userId) {

        return notes.stream()
                .filter(note -> note.getUserId().equals(userId))
                .toList();
    }
    public Note save(Note note) throws Exception {

        note.setId(java.util.UUID.randomUUID().toString());

        notes.add(note);

        new ObjectMapper().writeValue(jsonFile, notes);

        return note;
    }
    public Optional<Note> update(String id, Note updated) throws Exception {

        Optional<Note> optionalNote = findById(id);

        if (optionalNote.isPresent()) {

            Note note = optionalNote.get();

            note.setTitle(updated.getTitle());
            note.setContent(updated.getContent());
            note.setUserId(updated.getUserId());

            new ObjectMapper().writeValue(jsonFile, notes);

            return Optional.of(note);
        }

        return Optional.empty();
    }
    public boolean deleteById(String id) throws Exception {

        Optional<Note> optionalNote = findById(id);

        if (optionalNote.isPresent()) {

            notes.remove(optionalNote.get());

            new ObjectMapper().writeValue(jsonFile, notes);

            return true;
        }

        return false;
    }
}
