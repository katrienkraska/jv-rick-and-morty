package repository;

import model.ApiCharacter;
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
