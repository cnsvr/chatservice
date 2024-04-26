package com.example.chatservice.config;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class StompPrincipal implements Principal {
    String name;
    List<String> allowedGroups;

    public StompPrincipal(String name) {
        this.name = name;
        this.allowedGroups = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
