package com.webuml.projectmanager.controller.views.lighttables;

import org.springframework.hateoas.Resources;

class LightTableViewCollectionResource extends Resources<LightTableViewResource> {

  public LightTableViewCollectionResource(Iterable<LightTableViewResource> lightTableViewResource) {
    super(lightTableViewResource);
  }
}
