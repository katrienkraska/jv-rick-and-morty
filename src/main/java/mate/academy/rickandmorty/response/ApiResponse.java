package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.ApiCharacter;
import java.util.List;

public class ApiResponse {
    private List<ApiCharacter> results;

    public List<ApiCharacter> getResults() {
        return results;
    }

    public void setResults(List<ApiCharacter> results) {
        this.results = results;
    }
}
