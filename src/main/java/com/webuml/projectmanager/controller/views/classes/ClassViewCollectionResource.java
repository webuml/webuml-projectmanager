package com.webuml.projectmanager.controller.views.classes;

import org.springframework.hateoas.Resources;

class ClassViewCollectionResource extends Resources<ClassViewResource> {

  public ClassViewCollectionResource(Iterable<ClassViewResource> classViewResources) {
    super(classViewResources);
  }
}
