package com.example.chatservice.requests;

import com.example.chatservice.enums.ChannelType;

public class LeaveChannelRequest {
    private String target;
    private ChannelType targetChannelType;

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
}
