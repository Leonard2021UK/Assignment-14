package com.codercampus.assignment14.repository;

import com.codercampus.assignment14.domain.Channel;
import com.codercampus.assignment14.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChannelRepository {

    private Long channelId = 1L;
    private Map<Long, Channel> channels = new HashMap<>();


    public ChannelRepository(){
        Channel initialChannel = new Channel();
        initialChannel.setChannelId(channelId);
        initialChannel.setChannelName("General");
        this.channels.put(channelId,initialChannel);
    }

    public Channel saveChannel(Channel channel){
        channel.setChannelId(++channelId);
        this.channels.put(channelId, channel);
        return channel;
    }

    public Map<Long, Channel> getChannels(){
        return this.channels;
    }

    public Channel getChannelById(Long channelId){
        return this.channels.get(channelId);
    }

    public List<Comment> getChannelComments(Long channelId){
        return this.channels.get(channelId).getComments();
    }

    public void saveComment(Comment newComment,Long channelId){
        this.channels.get(channelId).getComments().add(newComment);
    }
}
