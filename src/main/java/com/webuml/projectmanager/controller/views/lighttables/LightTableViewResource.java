package com.webuml.projectmanager.controller.views.lighttables;

import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class LightTableViewResource extends Resource<LightTableView> {

  public LightTableViewResource(LightTableView content, Link... links) {
    super(content, links);
  }

  public LightTableViewResource(LightTableView content, Iterable<Link> links) {
    super(content, links);
  }
}
