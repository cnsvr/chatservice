package com.example.chatservice.services;

import com.example.chatservice.config.StompPrincipal;
import com.example.chatservice.enums.ChannelType;
import com.example.chatservice.models.channel.RoomChannel;
import com.example.chatservice.models.message.UserPresence;
import com.example.chatservice.repositories.channel.RoomChannelRepository;
import com.example.chatservice.requests.JoinChannelRequest;
import com.example.chatservice.requests.LeaveChannelRequest;
import com.example.chatservice.responses.JoinChannelResponse;
import com.example.chatservice.util.EventPublishManager;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
public class ChannelService {
    private final RoomChannelRepository roomChannelRepository;
    private final EventPublishManager eventPublishManager;

    public ChannelService(RoomChannelRepository roomChannelRepository, EventPublishManager eventPublishManager) {
        this.roomChannelRepository = roomChannelRepository;
        this.eventPublishManager = eventPublishManager;
    }

    public JoinChannelResponse joinChannel(Principal principal, JoinChannelRequest joinChannelRequest) {
        JoinChannelResponse joinChannelResponse = new JoinChannelResponse();
        switch (joinChannelRequest.getTargetChannelType()) {
            case ChannelType.ROOM:
                // Find or create room channel and return the response
                RoomChannel roomChannel = joinRoomChannel(principal, joinChannelRequest);
                joinChannelResponse.setChannelID(roomChannel.getChannelId());
                break;
            case GROUP:
                break;
            case DIRECT:
                break;
            default:
                throw new IllegalArgumentException("Invalid channel type");
        }

        return joinChannelResponse;
    }

    public void leaveChannel(Principal principal, LeaveChannelRequest leaveChannelRequest) {
        switch (leaveChannelRequest.getTargetChannelType()) {
            case ChannelType.ROOM:
                // Remove user from room channel
                RoomChannel roomChannel = getRoomChannel(leaveChannelRequest.getTarget());
                if (roomChannel != null) {
                    var userPresence = new UserPresence(); // TODO: fix this.
                    userPresence.setUsername(((StompPrincipal)principal).getUsername()); // TODO: fix this.
                    userPresence.setSessionId(((StompPrincipal)principal).getSessionId()); // TODO: fix this.
                    eventPublishManager.publishUserLeftRoomEvent(roomChannel.getChannelId(), userPresence);
                    removeUserPresence(roomChannel.getChannelId(), principal.getName());
                }
                break;
            case GROUP:
                break;
            case DIRECT:
                break;
            default:
                throw new IllegalArgumentException("Invalid channel type");
        }

    }

    private RoomChannel joinRoomChannel(Principal principal, JoinChannelRequest joinChannelRequest) {
        RoomChannel roomChannel = getRoomChannel(joinChannelRequest.getTarget());
        if (roomChannel == null) {
            roomChannel = createRoomChannel(joinChannelRequest.getTarget());
        }

        var userPresence = new UserPresence(); // TODO: fix this.
        userPresence.setUsername(((StompPrincipal)principal).getUsername()); // TODO: fix this.
        userPresence.setSessionId(((StompPrincipal)principal).getSessionId()); // TODO: fix this.
        eventPublishManager.publishUserJoinedToRoomEvent(roomChannel.getChannelId(), userPresence);
        return roomChannel;
    }

    public RoomChannel createRoomChannel(String roomID) {
        RoomChannel roomChannel = new RoomChannel(roomID);
        return roomChannelRepository.save(roomChannel);
    }

    public RoomChannel getRoomChannel(String roomID) {
        return roomChannelRepository.findByRoomID(roomID);
    }

    public void deleteRoomChannel(String roomID) {
        roomChannelRepository.deleteByRoomID(roomID);
    }

    public void addUserPresence(String roomID, UserPresence userPresence) {
        RoomChannel roomChannel = getRoomChannel(roomID);
        roomChannel.addPresence(userPresence);
        roomChannelRepository.save(roomChannel);
    }

    public void removeUserPresence(String roomID, String sessionId) {
        RoomChannel roomChannel = getRoomChannel(roomID);
        roomChannel.removePresence(u -> u.getSessionId().equals(sessionId));
        roomChannelRepository.save(roomChannel);
    }

    public List<RoomChannel> findAllRoomChannels() {
        return roomChannelRepository.findAll();
    }

    public RoomChannel saveRoomChannel(RoomChannel roomChannel) {
        return roomChannelRepository.save(roomChannel);
    }

}
