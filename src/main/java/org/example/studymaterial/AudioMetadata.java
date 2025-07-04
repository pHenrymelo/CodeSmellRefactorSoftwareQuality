package org.example.studymaterial;

public class AudioMetadata {
    private AudioReference.AudioQuality audioQuality;
    private boolean isDownloadable;
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private String language;
    private int rating;
    private int viewCount;
    private int shareCount;

    private AudioMetadata(Builder builder) {
        applyBuilder(builder);
    }

    private void applyBuilder(Builder builder) {
        assignBasicMetadata(builder);
        assignRightsAndStats(builder);
    }

    private void assignBasicMetadata(Builder builder) {
        this.audioQuality = builder.audioQuality;
        this.isDownloadable = builder.isDownloadable;
        this.title = builder.title;
        this.description = builder.description;
        this.link = builder.link;
    }

    private void assignRightsAndStats(Builder builder) {
        this.accessRights = builder.accessRights;
        this.license = builder.license;
        this.language = builder.language;
        this.rating = builder.rating;
        this.viewCount = builder.viewCount;
        this.shareCount = builder.shareCount;
    }

    public void applyTo(AudioReference ref) {
        ref.editBasic(title, description, link);
        ref.setAccessRights(accessRights);
        ref.setLicense(license);
        ref.setAudioQuality(audioQuality);
        ref.editCommonAttributes(rating, language, viewCount, shareCount, isDownloadable);
    }

    public static class Builder {
        private AudioReference.AudioQuality audioQuality;
        private boolean isDownloadable;
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private String language;
        private int rating;
        private int viewCount;
        private int shareCount;

        public Builder audioQuality(AudioReference.AudioQuality quality) {
            this.audioQuality = quality;
            return this;
        }

        public Builder isDownloadable(boolean isDownloadable) {
            this.isDownloadable = isDownloadable;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder accessRights(String accessRights) {
            this.accessRights = accessRights;
            return this;
        }

        public Builder license(String license) {
            this.license = license;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder viewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }

        public Builder shareCount(int shareCount) {
            this.shareCount = shareCount;
            return this;
        }

        public AudioMetadata build() {
            return new AudioMetadata(this);
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
