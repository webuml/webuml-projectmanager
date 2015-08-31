package com.webuml.projectmanager.controller.views.associations;

import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class AssociationViewResource extends Resource<AssociationView> {

  public AssociationViewResource(AssociationView content, Link... links) {
    super(content, links);
  }

  public AssociationViewResource(AssociationView content, Iterable<Link> links) {
    super(content, links);
  }
}
