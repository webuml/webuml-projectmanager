package com.webuml.projectmanager.controller.views.thumbnails;

import com.webuml.projectmanager.domain.viewmodel.Thumbnail;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class ThumbnailResource extends Resource<Thumbnail> {

  public ThumbnailResource(Thumbnail content, Link... links) {
    super(content, links);
  }

  public ThumbnailResource(Thumbnail content, Iterable<Link> links) {
    super(content, links);
  }
}
