package com.codercampus.assignment14.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Channel {

    private Long channelId;

    private String channelName;

    private List<Comment> comments = new ArrayList<>();


    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long id) {
        this.channelId = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
