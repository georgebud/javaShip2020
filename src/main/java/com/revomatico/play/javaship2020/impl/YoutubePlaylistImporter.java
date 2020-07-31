package com.revomatico.play.javaship2020.impl;

import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import io.vavr.collection.Seq;
import org.raisercostin.jedio.Locations;
import org.raisercostin.nodes.Nodes;

public class YoutubePlaylistImporter implements MediaItemImporter {
  public static final String GOOGLE_API_PLAYLIST_URL_PREFIX = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=%s&key=%s";

  @Override
  public List<MediaItem> importMediaItems(String path) {
    String playlistId = "PL4o29bINVT4EG_y-k5jGoOu3-Am8Nvi10";
    return readPlaylist(playlistId, "AIzaSyDaU9IH856cYXsNvoctljCzAStI8kbmGrI").toJavaList();
  }

  public static Seq<MediaItem> readPlaylist(String playlistId, String apiKey) {
    return readPlaylist(playlistId, playlistId, apiKey);
  }

  public static Seq<MediaItem> readPlaylist(String source, String playlistId, String apiKey) {
    YoutubePlaylist result = readYoutubePlaylist(playlistId, apiKey);
    return result.items.map(youtubeItem -> youtubeItem.snippet)
      .map(x -> {
        x.source = "Google playlist " + source;
        return x;
      })
      .map(x -> x.validate());
  }

  private static YoutubePlaylist readYoutubePlaylist(String playListId, String apiKey) {
    String content = Locations
      .url(String.format(YoutubePlaylistImporter.GOOGLE_API_PLAYLIST_URL_PREFIX, playListId, apiKey))
      .readContentSync();
    YoutubePlaylist result = Nodes.json.withIgnoreUnknwon().toObject(content, YoutubePlaylist.class);
    return result;
  }
}

class YoutubePlaylist {
  Seq<YoutubeItem> items;
}

class YoutubeItem {
  MediaItem snippet;
}
