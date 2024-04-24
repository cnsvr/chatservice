package com.example.chatservice.controllers.admin;

import com.example.chatservice.models.channel.RoomChannel;
import com.example.chatservice.services.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminChannelController {
    private final ChannelService channelService;

    public AdminChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/admin/channels/room")
    public String getAdminChannels(Model model) {
        List<RoomChannel> allRoomChannels = channelService.findAllRoomChannels();
        model.addAttribute("rooms", allRoomChannels);
        return "channels/room";
    }

}
