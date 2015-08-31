package com.webuml.projectmanager.controller.metamodel.associations;

import com.webuml.projectmanager.domain.metamodel.Association;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

@Component
class AssociationCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<Association>, AssociationCollectionResource> {

  @Inject
  AssociationResourceAssembler resourceAssembler;

  public AssociationCollectionResourceAssembler() {
    super(AssociationCollectionController.class, AssociationCollectionResource.class);
  }

  @Override
  public AssociationCollectionResource toResource(Iterable<Association> associationCollection) {

    ArrayList<AssociationResource> resourceList = new ArrayList<>();
    for (Association association : associationCollection) {
      AssociationResource aResource = resourceAssembler.toResource(association);
      resourceList.add(aResource);
    }
    return new AssociationCollectionResource(resourceList);
  }
}
