package com.waity.api.domain;

import com.waity.api.domain.dataStruct.EntityList;
import com.waity.api.dto.tagDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Tag {
    private int id;
    private String tagName;

    private EntityList<Channel> channels;
    private EntityList<Video> videos;

    public Tag(tagDTO tagDTO) {
        this.id = tagDTO.getId();
        this.tagName = tagDTO.getTagName();
        this.channels = new EntityList<>();
        this.videos = new EntityList<>();
    }
    // static
    public static Tag createTag(tagDTO tagDTO) {
        return new Tag(tagDTO);
    }
    public static List<Tag> createTags(List<tagDTO> tagDTOs) {
        List<Tag> tags = new ArrayList<>();
        tagDTOs.forEach(tagDTO -> {
            tags.add(createTag(tagDTO));
        });
        return tags;
    }
    public static tagDTO toTagDTO(Tag tag) {
        return new tagDTO(
                tag.getId(),
                tag.getTagName()
        );
    }
    public static List<tagDTO> toTagDTOs(List<Tag> tags) {
        List<tagDTO> tagDTOs = new ArrayList<>();
        for(Tag tag : tags) {
            tagDTOs.add(toTagDTO(tag));
        }
        return tagDTOs;
    }

    public void update(tagDTO tagDTO) {
        setTagName(tagDTO.getTagName());
    }

    // channels
    public List<Channel> getChannels() { return channels.getList(); }
    public void setChannels(List<Channel> channels) { this.channels.setList(channels); }
    public void addChannel(Channel channel) throws Exception { this.channels.add(channel); }
    public void addChannels(List<Channel> channels) throws Exception { this.channels.addAll(channels); }
    public void removeChannel(Channel channel) throws Exception { this.channels.remove(channel); }
    public void removeChannels(List<Channel> channels) throws Exception { this.channels.removeAll(channels); }
    public List<Integer> getChannelIds() throws Exception {
        List<Integer> channelIds = new ArrayList<>();
        channels.forEach(channel -> {
            channelIds.add(channel.getId());
        });
        return channelIds;
    }

    // videos
    public List<Video> getVideos() { return videos.getList(); }
    public void setVideos(List<Video> videos) { this.videos.setList(videos); }
    public void addChannel(Video video) throws Exception { this.videos.add(video); }
    public void addVideos(List<Video> videos) throws Exception { this.videos.addAll(videos); }
    public void removeVideo(Video video) throws Exception { this.videos.remove(video); }
    public void removeVideos(List<Video> videos) throws Exception { this.videos.removeAll(videos); }
    public List<Integer> getVideoIds() throws Exception {
        List<Integer> videoIds = new ArrayList<>();
        videos.forEach(video -> {
            videoIds.add(video.getId());
        });
        return videoIds;
    }
}
