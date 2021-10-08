package com.codercampus.assignment14.contoller;

import com.codercampus.assignment14.domain.Channel;
import com.codercampus.assignment14.domain.Comment;
import com.codercampus.assignment14.domain.User;
import com.codercampus.assignment14.repository.ChannelRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ChannelController {
    private final ChannelRepository channelRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChannelController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    @GetMapping("/channels")
    public String getChannels(ModelMap model){
        Map<Long, Channel> channels = this.channelRepository.getChannels();
        model.put("channels",channels);
        return "channels";
    }

    @GetMapping("/channels/{channelId}")
    public String getChannel(@PathVariable Long channelId, ModelMap model){
        Channel channel = this.channelRepository.getChannelById(channelId);
        model.put("activeChannel",channel);
        return "activeChannel";
    }

    @GetMapping("/channels/{channelId}/history")
    public String getChannelComments(@PathVariable Long channelId, ModelMap model){
        List<Comment> comments = this.channelRepository.getChannelComments(channelId);
//        comments.add(new Comment("leoo","leo is le"));
        model.addAttribute("comments",comments);
        return "/fragments/chatContainerFragment::chatContainer";
    }

    @PostMapping("/channels")
    public String saveUserInput(@RequestBody String requestBody, ModelMap model) throws IOException {

        JsonNode requestBodyNode = stringToObject(requestBody);
        String userInput = requestBodyNode.get("userInput").asText();
        String userName = requestBodyNode.get("userName").asText();
        Comment newComment = new Comment(userName,userInput);
        Long channelId = requestBodyNode.get("activeChannelId").asLong();

        this.channelRepository.saveComment(newComment,channelId);
//        Channel activeChannel = this.channelRepository.getChannelById(channelId);
//        model.put("activeChannel",activeChannel);
        return "redirect:/channels/"+channelId;
//        return "activeChannel";
    }

    private JsonNode stringToObject(String value) throws IOException {
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(value);
        return mapper.readTree(jsonParser);
    }
}
