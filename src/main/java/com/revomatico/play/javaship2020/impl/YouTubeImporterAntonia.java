package com.revomatico.play.javaship2020.impl;

import java.util.ArrayList;
import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.collection.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.raisercostin.jedio.Locations;

public class YouTubeImporterAntonia implements MediaItemImporter {
  private static String myApiKey = "AIzaSyBQ0yj3OzsENXmMJ5sXlwp-r-U7ZzAUAZM";
  //static String playListId1 = "PLRXt5CxEntwiGu-3FXEo5iMCeaHFkJKOw";
  //static String videoId = "LXXQLa-5n5w";
  private static String channelId = "UCogyDjqEVcRWajNRm6PWx7Q";

  @Override
  public List<MediaItem> importMediaItems(String path) {
    Vector<Tuple2<String, String>> idsOfPlaylists = getIdOfPlaylist(channelId, myApiKey);
    return idsOfPlaylists
      .flatMap(playlistId -> YoutubePlaylistImporter.readPlaylist(playlistId._1, playlistId._2, myApiKey))
      .toJavaList();
  }

  public static Vector<Tuple2<String, String>> getIdOfPlaylist(String channelId, String myApiKey) {
    String content = Locations
      .url("https://www.googleapis.com/youtube/v3/playlists?part=snippet,contentDetails&channelId="
          + channelId + "&key=" + myApiKey)
      .readContentSync();

    List<Tuple2<String, String>> idsOfPlaylists = new ArrayList<>();

    //YoutubePlaylistAntonia result = Nodes.json.withIgnoreUnknwon().toObject(content, YoutubePlaylistAntonia.class);
    JSONObject obj = new JSONObject(content);
    JSONArray arr = obj.getJSONArray("items");
    for (int i = 0; i < arr.length(); i++) {
      String id = arr.getJSONObject(i).getString("id");
      String source = arr.getJSONObject(i).getJSONObject("snippet").getString("title");
      idsOfPlaylists.add(API.Tuple(source, id));
      //System.out.println(id);
    }
    return Vector.ofAll(idsOfPlaylists);
  }
}
