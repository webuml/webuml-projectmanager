package com.webuml.projectmanager.controller.views.associations;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.metamodel.associations.AssociationController;
import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.ASSOCIATION;
import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class AssociationViewResourceAssembler extends ResourceAssemblerSupport<AssociationView, AssociationViewResource> {

  public AssociationViewResourceAssembler() {
    super(AssociationViewCollectionController.class, AssociationViewResource.class);
  }

  @Override
  public AssociationViewResource toResource(AssociationView associationView) {
    AssociationViewResource resource = createResourceWithId(associationView.getId(), associationView);

    Link associationLink = linkTo(methodOn(AssociationController.class).get(associationView.getAssociation())).withRel(ASSOCIATION);
    resource.add(new HalLink(associationLink, shortRel(ASSOCIATION)));

    return resource;
  }

  @Override
  protected AssociationViewResource instantiateResource(AssociationView entity) {
    return new AssociationViewResource(entity);
  }
}
