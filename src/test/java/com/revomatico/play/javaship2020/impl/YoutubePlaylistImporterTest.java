package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.MediaItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YoutubePlaylistImporterTest {

  @Test
  void testImportMediaItems() {
    List<MediaItem> list = new YoutubePlaylistImporter().importMediaItems("");
    assertThat(list).isNotNull();
    assertThat(list).hasSize(50);
  }
}
