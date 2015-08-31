package com.webuml.projectmanager.controller.views.associations;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class AssociationViewCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<AssociationView>, AssociationViewCollectionResource> {

  @Inject
  AssociationViewResourceAssembler resourceAssembler;

  public AssociationViewCollectionResourceAssembler() {
    super(AssociationViewCollectionController.class, AssociationViewCollectionResource.class);
  }

  @Override
  public AssociationViewCollectionResource toResource(Iterable<AssociationView> associationViewCollection) {

    ArrayList<AssociationViewResource> resourceList = new ArrayList<>();
    for (AssociationView associationView : associationViewCollection) {
      AssociationViewResource resource = resourceAssembler.toResource(associationView);
      resourceList.add(resource);
    }
    AssociationViewCollectionResource collectionResource = new AssociationViewCollectionResource(resourceList);
    Link self = linkTo(methodOn(AssociationViewCollectionController.class).get(null)).withRel(REL_SELF);
    collectionResource.add(new HalLink(self, shortRel(REL_SELF)));
    return collectionResource;
  }
}
