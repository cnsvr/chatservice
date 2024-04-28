package com.example.chatservice.controllers.client;

import com.example.chatservice.requests.JoinChannelRequest;
import com.example.chatservice.responses.JoinChannelResponse;
import com.example.chatservice.services.ChannelService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @MessageMapping("/join-channel")
    @SendToUser("/topic/join-channel")
    public JoinChannelResponse joinChannel(JoinChannelRequest joinChannelRequest) {
        // If the channel does not exist, create it, else find it.
        // open topic for the channel if it does not exist
        // return response
        return channelService.joinChannel(joinChannelRequest);
    }

}
