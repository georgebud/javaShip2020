package com.revomatico.play.javaship2020.impl;

import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import io.vavr.collection.Seq;
import org.raisercostin.jedio.Locations;
import org.raisercostin.nodes.Nodes;

public class DeezerPlaylistImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {
    //apacheHttpClient, Unirest
    String content = Locations.url("https://api.deezer.com/playlist/183049161").readContentSync();
    //System.out.println(content);
    //jackson
    DeezerPlaylist result = Nodes.json.withIgnoreUnknwon().toObject(content, DeezerPlaylist.class);
    return result.tracks.data.map(x -> x.validate()).toJavaList();
  }
}

class DeezerPlaylist {
  DeezerPlaylistData tracks;
}

class DeezerPlaylistData {
  //Seq<DeezerPlaylistDataTrack> data;
  Seq<MediaItem> data;
}
//
//class DeezerPlaylistDataTrack {
//  String title;
//  @JsonProperty("album.cover_medium")
//  String image;
//}
