package com.webuml.projectmanager.controller.metamodel.classes;

import org.springframework.hateoas.Resources;

class ClassCollectionResource extends Resources<ClassResource> {

  public ClassCollectionResource(Iterable<ClassResource> resourceIterable) {
    super(resourceIterable);
  }
}
