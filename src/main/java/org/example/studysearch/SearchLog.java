package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // ✅ API moderna e usada internamente pelas classes de busca
    public void logSearch(String term) {
        if (isLocked) return;

        searchHistory.add(term);
        searchCount.put(term, searchCount.getOrDefault(term, 0) + 1);
        numUsages++;
    }

    // ✅ Método legado restaurado para compatibilidade com testes
    public void addSearchHistory(String searchHistory) {
        this.searchHistory.add(searchHistory);
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public Map<String, Integer> getSearchCount() {
        return searchCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
