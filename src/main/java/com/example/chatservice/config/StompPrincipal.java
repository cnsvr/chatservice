package com.example.chatservice.config;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class StompPrincipal implements Principal {
    String sessionId;
    String username;
    List<String> allowedGroups;

    public StompPrincipal(String sessionId) {
        this.sessionId = sessionId;
        this.allowedGroups = new ArrayList<>();
    }

    @Override
    public String getName() {
        return sessionId;
    }

    public void setName(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAllowedGroups() {
        return allowedGroups;
    }

    public void setAllowedGroups(List<String> allowedGroups) {
        this.allowedGroups = allowedGroups;
    }

    public void addAllowedGroup(String group) {
        this.allowedGroups.add(group);
    }
}
