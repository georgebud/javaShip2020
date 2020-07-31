package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import org.junit.jupiter.api.Test;

public class YoutubePlaylistImporterTest {
  @Test
  void testImportMediaItems() {
    List<MediaItem> list = new YoutubePlaylistImporter().importMediaItems("");
    assertThat(list).isNotNull();
    assertThat(list).hasSize(50);
    //assertThat(list.get(1000).url).isNotNull();
    assertThat(list).element(0).extracting(m -> m.url).isNotNull();
    //assertThat(list).element(0).extracting(m -> m.url);
  }
}
