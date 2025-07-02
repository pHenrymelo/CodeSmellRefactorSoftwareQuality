package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // === Métodos de comportamento ===

    public void incrementView() {
        viewCount++;
    }

    public void download() {
        if (!isDownloadable) {
            throw new UnsupportedOperationException("This reference cannot be downloaded.");
        }
        downloadCount++;
    }

    public void share() {
        shareCount++;
    }

    public void rate(int newRating) {
        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
        rating = newRating;
    }

    // Método editBasic foi removido por incompatibilidade com os testes

    public void editCommonAttributes(int rating, String language, int viewCount, int shareCount, boolean isDownloadable) {
        this.rating = rating;
        this.language = language;
        this.viewCount = viewCount;
        this.shareCount = shareCount;
        this.isDownloadable = isDownloadable;
    }

    public boolean isCountable() {
        return true;
    }

    // === Getters ===

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public String getLicense() {
        return license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public int getRating() {
        return rating;
    }

    public String getLanguage() {
        return language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    // === Setters restaurados como protected ===

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setLink(String link) {
        this.link = link;
    }

    protected void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    protected void setLicense(String license) {
        this.license = license;
    }

    protected void setLanguage(String language) {
        this.language = language;
    }

    protected void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }
}
