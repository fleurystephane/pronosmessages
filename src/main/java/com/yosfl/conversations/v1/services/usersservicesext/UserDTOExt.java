package com.yosfl.conversations.v1.services.usersservicesext;

public class UserDTOExt {
    private Long id;
    private String username;
    private String displayName;
    private String email;
    private InfosCreatorDTOExt creator;

    private UserAccountDTOExt account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InfosCreatorDTOExt getCreator() {
        return creator;
    }

    public void setCreator(InfosCreatorDTOExt creator) {
        this.creator = creator;
    }

    public UserAccountDTOExt getAccount() {
        return account;
    }

    public void setAccount(UserAccountDTOExt account) {
        this.account = account;
    }
}
