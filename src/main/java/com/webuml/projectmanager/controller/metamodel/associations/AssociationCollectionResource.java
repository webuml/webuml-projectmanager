package com.webuml.projectmanager.controller.metamodel.associations;

import org.springframework.hateoas.Resources;

class AssociationCollectionResource extends Resources<AssociationResource> {

  public AssociationCollectionResource(Iterable<AssociationResource> projectResource) {
    super(projectResource);
  }
}
