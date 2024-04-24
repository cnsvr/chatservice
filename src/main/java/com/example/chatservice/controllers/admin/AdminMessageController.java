package com.example.chatservice.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminMessageController {
    @GetMapping("/admin/messages/room/{roomId}")
    public String getAdminChannel(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "messages/room";
    }
}
