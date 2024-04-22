package com.example.chatservice.requests;

import com.example.chatservice.enums.ChannelType;

public class JoinChannelRequest {
    /*
     * The ID of the channel to join
     * ROOM ID
     * Group ID
     * User ID to invite
     */
    private String channelID;
    private ChannelType channelType;
    private boolean hidden;
    private boolean persistence;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
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
