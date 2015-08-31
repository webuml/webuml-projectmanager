package com.webuml.projectmanager.controller.views.thumbnails;

import org.springframework.hateoas.Resources;

class ThumbnailCollectionResource extends Resources<ThumbnailResource> {

  public ThumbnailCollectionResource(Iterable<ThumbnailResource> lightTableViewResource) {
    super(lightTableViewResource);
  }
}
