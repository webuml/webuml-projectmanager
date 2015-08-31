package com.webuml.projectmanager.controller.metamodel.classes;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class ClassResource extends Resource<com.webuml.projectmanager.domain.metamodel.Class> {

  public ClassResource(com.webuml.projectmanager.domain.metamodel.Class content, Link... links) {
    super(content, links);
  }

  public ClassResource(com.webuml.projectmanager.domain.metamodel.Class content, Iterable<Link> links) {
    super(content, links);
  }
}
