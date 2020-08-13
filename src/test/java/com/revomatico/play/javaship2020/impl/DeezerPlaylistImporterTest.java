package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import org.junit.jupiter.api.Test;

class DeezerPlaylistImporterTest {

  @Test
  void testImportMediaItems() {
    List<MediaItem> list = new DeezerPlaylistImporter().importMediaItems("");
    assertThat(list).isNotNull();
    assertThat(list).hasSize(4);
  }
}
