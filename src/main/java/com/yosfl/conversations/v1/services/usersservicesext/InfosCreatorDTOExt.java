package com.yosfl.conversations.v1.services.usersservicesext;

public class InfosCreatorDTOExt {
    private String description;
    private boolean certified;
    private boolean validated;
    private String iban;

    private String location;

    private String image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
