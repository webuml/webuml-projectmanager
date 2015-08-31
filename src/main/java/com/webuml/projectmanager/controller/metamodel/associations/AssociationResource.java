package com.webuml.projectmanager.controller.metamodel.associations;

import com.webuml.projectmanager.domain.metamodel.Association;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class AssociationResource extends Resource<Association> {

  public AssociationResource(Association content, Link... links) {
    super(content, links);
  }

  public AssociationResource(Association content, Iterable<Link> links) {
    super(content, links);
  }
}
