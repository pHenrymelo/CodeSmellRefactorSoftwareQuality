package org.example.studymaterial;

public class TextReference extends Reference{
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        this.wordCount = wordCount;
        this.format = format;
        this.setTitle(title);
        this.setLanguage(language);
        this.setAccessRights(accessRights);
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        this.setAccessRights(accessRights);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess(){
        if(getAccessRights() != "Public"){
            return false;
        } else if (this.format != "pdf"){
            return false;
        } else if (this.wordCount == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCountable() {
        return handleTextAccess();
    }


}
