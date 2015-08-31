package com.webuml.projectmanager.controller.views.associations;

import org.springframework.hateoas.Resources;

class AssociationViewCollectionResource extends Resources<AssociationViewResource> {

  public AssociationViewCollectionResource(Iterable<AssociationViewResource> classViewResources) {
    super(classViewResources);
  }
}
