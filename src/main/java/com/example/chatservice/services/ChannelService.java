package com.example.chatservice.services;

import com.example.chatservice.enums.ChannelType;
import com.example.chatservice.models.channel.RoomChannel;
import com.example.chatservice.models.message.UserPresence;
import com.example.chatservice.repositories.channel.RoomChannelRepository;
import com.example.chatservice.requests.JoinChannelRequest;
import com.example.chatservice.responses.JoinChannelResponse;
import com.example.chatservice.util.EventPublishManager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChannelService {
    private final RoomChannelRepository roomChannelRepository;
    private final EventPublishManager eventPublishManager;

    public ChannelService(RoomChannelRepository roomChannelRepository, EventPublishManager eventPublishManager) {
        this.roomChannelRepository = roomChannelRepository;
        this.eventPublishManager = eventPublishManager;
    }

    public JoinChannelResponse joinChannel(JoinChannelRequest joinChannelRequest) {
        JoinChannelResponse joinChannelResponse = new JoinChannelResponse();
        switch (joinChannelRequest.getChannelType()) {
            case ChannelType.ROOM:
                RoomChannel roomChannel = joinRoomChannel(joinChannelRequest);
                joinChannelResponse.setChannelID(roomChannel.getRoomID());
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

    private RoomChannel joinRoomChannel(JoinChannelRequest joinChannelRequest) {
        RoomChannel roomChannel = getRoomChannel(joinChannelRequest.getChannelID());
        if (roomChannel == null) {
            roomChannel = createRoomChannel(joinChannelRequest.getChannelID());
        }

        eventPublishManager.publishUserJoinedToRoomEvent(roomChannel.getRoomID(), null);
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

    public void removeUserPresence(String roomID, UserPresence userPresence) {
        RoomChannel roomChannel = getRoomChannel(roomID);
        roomChannel.removePresence(userPresence);
        roomChannelRepository.save(roomChannel);
    }

    public List<RoomChannel> findAllRoomChannels() {
        return roomChannelRepository.findAll();
    }

    public RoomChannel saveRoomChannel(RoomChannel roomChannel) {
        return roomChannelRepository.save(roomChannel);
    }
}
