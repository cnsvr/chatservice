package com.example.chatservice.requests;

import com.example.chatservice.enums.ChannelType;

public class JoinChannelRequest {
    /*
     * The ID of the channel to join
     * Options:
     * ROOM ID
     * Group ID
     * User ID to invite
     */
    private String target;
    private ChannelType targetChannelType;
    private boolean hidden;
    private boolean persistence;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ChannelType getTargetChannelType() {
        return targetChannelType;
    }

    public void setTargetChannelType(ChannelType targetChannelType) {
        this.targetChannelType = targetChannelType;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }
}
