package com.webuml.projectmanager.controller.metamodel.packages;

import org.springframework.hateoas.Resources;

class PackageCollectionResource extends Resources<PackageResource> {

  public PackageCollectionResource(Iterable<PackageResource> resourceIterable) {
    super(resourceIterable);
  }
}
