package com.example.chatservice.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminMessageController {
    @GetMapping("/admin/messages/room/{channelId}")
    public String getAdminChannel(Model model, @PathVariable String channelId) {
        model.addAttribute("channelId", channelId);
        return "messages/room";
    }
}
