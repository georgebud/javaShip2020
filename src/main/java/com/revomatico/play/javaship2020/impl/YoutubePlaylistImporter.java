package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import io.vavr.collection.Seq;
import org.raisercostin.jedio.Locations;
import org.raisercostin.nodes.Nodes;

import java.util.List;

public class YoutubePlaylistImporter implements MediaItemImporter {
  @Override
  public List<MediaItem> importMediaItems(String path) {
    String playlistId = "PL4o29bINVT4EG_y-k5jGoOu3-Am8Nvi10";
    String content = Locations.url(String.format(
      "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=%s&key=AIzaSyDaU9IH856cYXsNvoctljCzAStI8kbmGrI",
      playlistId)).readContentSync();
    YoutubePlaylist result = Nodes.json.withIgnoreUnknwon().toObject(content, YoutubePlaylist.class);
    return result.items.map(youtubeItem -> youtubeItem.snippet)
      .map(x -> x.validate())
      .toJavaList();
  }
}

class YoutubePlaylist {
  Seq<YoutubeItem> items;
}

class YoutubeItem {
  MediaItem snippet;
}
