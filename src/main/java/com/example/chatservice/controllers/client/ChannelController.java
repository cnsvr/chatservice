package com.example.chatservice.controllers.client;

import com.example.chatservice.requests.JoinChannelRequest;
import com.example.chatservice.requests.LeaveChannelRequest;
import com.example.chatservice.responses.JoinChannelResponse;
import com.example.chatservice.services.ChannelService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import java.security.Principal;

@Controller
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @MessageMapping("/join-channel")
    @SendToUser("/topic/join-channel")
    public JoinChannelResponse joinChat(Principal principal, @Payload JoinChannelRequest joinChannelRequest) {
        // If the channel does not exist, create it, else find it.
        // open topic for the channel if it does not exist
        // return response
        return channelService.joinChannel(principal, joinChannelRequest);
    }

    @MessageMapping("/leave-channel")
    public void leaveChat(Principal principal, @Payload LeaveChannelRequest leaveChannelRequest) {
        channelService.leaveChannel(principal, leaveChannelRequest);
    }

    // error handling for
    @MessageMapping("/error")
    public void error(Principal principal, @Payload String error) {
        // log error
        System.out.println("Error: " + error);
    }
}
