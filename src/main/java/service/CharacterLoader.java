package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.model.ApiCharacter;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import mate.academy.rickandmorty.repository.ApiResponse;
import mate.academy.rickandmorty.repository.CharacterRepository;

@Service
public class CharacterLoader {
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${external.api.url}")
    private String externalApiUrl;

    public CharacterLoader(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @PostConstruct
    public void loadCharacters() {
        if (characterRepository.count() == 0) {
            String url = externalApiUrl + "/character";
            ResponseEntity<ApiResponse> response = restTemplate
                    .getForEntity(url, ApiResponse.class);
            for (ApiCharacter apiChar : response.getBody().getResults()) {
                CharacterEntity character = new CharacterEntity();
                character.setExternalId(String.valueOf(apiChar.getId()));
                character.setName(apiChar.getName());
                character.setStatus(apiChar.getStatus());
                character.setGender(apiChar.getGender());
                characterRepository.save(character);
            }
        }
    }
}
