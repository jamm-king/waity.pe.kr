package com.waity.api.domain;

import com.waity.api.domain.dataStruct.EntityList;
import com.waity.api.dto.channelDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Channel {
    private int id;
    private String channelId;
    private String keyword;
    private String image;
    private String title;
    private long subscriptionCount;
    private long viewCount;
    private String description;
    private int videoCount;

    private EntityList<Tag> tags;
    private EntityList<Video> videos;

    public Channel(channelDTO channelDTO) {
        this.id = channelDTO.getId();
        this.channelId = channelDTO.getChannelId();
        this.keyword = channelDTO.getKeyword();
        this.image = channelDTO.getImage();
        this.title = channelDTO.getTitle();
        this.subscriptionCount = channelDTO.getSubscriptionCount();
        this.viewCount = channelDTO.getViewCount();
        this.description = channelDTO.getDescription();
        this.videoCount = channelDTO.getVideoCount();
        this.tags = new EntityList<>();
        this.videos = new EntityList<>();
    }

    // static
    public static Channel createChannel(channelDTO channelDTO) {
        if(channelDTO == null) throw new NullPointerException();
        Channel channel = new Channel(channelDTO);
        return channel;
    }
    public static List<Channel> createChannels(List<channelDTO> channelDTOs) {
        List<Channel> channels = new ArrayList<>();
        for(channelDTO channelDTO : channelDTOs) {
            channels.add(createChannel(channelDTO));
        }
        return channels;
    }
    public static channelDTO toChannelDTO(Channel channel) {
        return new channelDTO(
                channel.getId(),
                channel.getChannelId(),
                channel.getKeyword(),
                channel.getImage(),
                channel.getTitle(),
                channel.getSubscriptionCount(),
                channel.getViewCount(),
                channel.getDescription(),
                channel.getVideoCount()
        );
    }
    public static List<channelDTO> toChannelDTOs(List<Channel> channels) {
        List<channelDTO> channelDTOs = new ArrayList<>();
        for(Channel channel : channels) {
            channelDTOs.add(toChannelDTO(channel));
        }
        return channelDTOs;
    }

    public void update(channelDTO channelDTO) {
        setChannelId(channelDTO.getChannelId());
        setKeyword(channelDTO.getKeyword());
        setImage(channelDTO.getImage());
        setTitle(channelDTO.getTitle());
        setSubscriptionCount(channelDTO.getSubscriptionCount());
        setViewCount(channelDTO.getViewCount());
        setDescription((channelDTO.getDescription()));
        setVideoCount(channelDTO.getVideoCount());
    }

    // videos
    public List<Video> getVideos() { return videos.getList(); }
    public void setVideos(List<Video> videos) { this.videos.setList(videos); }
    public void addVideo(Video video) throws Exception { videos.add(video); }
    public void addVideos(List<Video> videos) throws Exception { videos.addAll(videos); }
    public void removeVideo(Video video) { videos.remove(video); }
    public void removeVideos(List<Video> videos) throws Exception { videos.removeAll(videos); }
    public List<Integer> getVideoIds() {
        List<Integer> videoIds = new ArrayList<>();
        videos.forEach(video -> {
            videoIds.add(video.getId());
        });
        return videoIds;
    }

    // tags
    public List<Tag> getTags() { return tags.getList(); }
    public void setTags(List<Tag> tags) { this.tags.setList(tags); }
    public void addTag(Tag tag) throws Exception { tags.add(tag); }
    public void addTags(List<Tag> tags) throws Exception { this.tags.addAll(tags); }
    public void removeTag(Tag tag) { tags.remove(tag); }
    public void removeTags(List<Tag> tags) throws Exception { this.tags.removeAll(tags); }
    public List<Integer> getTagIds() {
        List<Integer> tagIds = new ArrayList<>();
        tags.forEach(tag -> {
            tagIds.add(tag.getId());
        });
        return tagIds;
    }
}
