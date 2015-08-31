package com.webuml.projectmanager.controller.metamodel.property;

import org.springframework.hateoas.Resources;

class PropertyCollectionResource extends Resources<PropertyResource> {

  public PropertyCollectionResource(Iterable<PropertyResource> resourceIterable) {
    super(resourceIterable);
  }
}
