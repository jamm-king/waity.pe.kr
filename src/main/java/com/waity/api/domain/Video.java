package com.waity.api.domain;

import com.waity.api.domain.dataStruct.EntityList;
import com.waity.api.dto.videoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Video {
    public int id;
    public String videoId;
    public String title;
    public String thumbnail;

    private Channel channel;
    private EntityList<Tag> tags;

    public Video(videoDTO videoDTO) {
        this.id = videoDTO.getId();
        this.videoId = videoDTO.getVideoId();
        this.title = videoDTO.getTitle();
        this.thumbnail = videoDTO.getThumbnail();
        this.channel = new Channel();
        this.tags = new EntityList<>();
    }

    // static
    public static Video createVideo(videoDTO videoDTO) {
        return new Video(videoDTO);
    }
    public static List<Video> createVideos(List<videoDTO> videoDTOs) {
        List<Video> videos = new ArrayList<>();
        videoDTOs.forEach(videoDTO -> {
            videos.add(createVideo(videoDTO));
        });
        return videos;
    }
    public static videoDTO toVideoDTO(Video video) {
        return new videoDTO(
                video.getId(),
                video.getVideoId(),
                video.getTitle(),
                video.getThumbnail(),
                video.getChannelId()
        );
    }
    public static List<videoDTO> toVideoDTOs(List<Video> videos) {
        List<videoDTO> videoDTOs = new ArrayList<>();
        videos.forEach(video -> {
            videoDTOs.add(Video.toVideoDTO(video));
        });
        return videoDTOs;
    }

    public void update(videoDTO videoDTO) {
        setVideoId(videoDTO.getVideoId());
        setTitle(videoDTO.getTitle());
        setThumbnail(videoDTO.getThumbnail());
    }

    // tags
    public List<Tag> getTags() { return tags.getList(); }
    public void setTags(List<Tag> tags) { this.tags.setList(tags); };
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

    // channel
    public int getChannelId() {
        return channel.getId();
    }
}
