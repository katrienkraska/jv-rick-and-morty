package controller;

import model.CharacterEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repository.CharacterRepository;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterRepository repository;

    public CharacterController(CharacterRepository characterRepository) {
        this.repository = characterRepository;
    }

    @GetMapping("/random")
    public ResponseEntity<CharacterEntity> getRandomCharacter() {
        List<CharacterEntity> all = repository.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CharacterEntity random = all.get(new Random().nextInt(all.size()));
        return ResponseEntity.ok(random);
    }

    @GetMapping("/search")
    public List<CharacterEntity> searchCharacters(@RequestParam String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
