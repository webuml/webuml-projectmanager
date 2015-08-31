package com.webuml.projectmanager.controller.metamodel.associations;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.projects.ProjectController;
import com.webuml.projectmanager.controller.metamodel.property.PropertyController;
import com.webuml.projectmanager.domain.metamodel.Association;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.MEMBER_END;
import static com.webuml.projectmanager.controller.LinkRelations.OWNED_END;
import static com.webuml.projectmanager.controller.LinkRelations.PROJECT;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class AssociationResourceAssembler extends ResourceAssemblerSupport<Association, AssociationResource> {

  public AssociationResourceAssembler() {
    super(AssociationController.class, AssociationResource.class);
  }

  @Override
  public AssociationResource toResource(Association entity) {
    AssociationResource resource = createResourceWithId(entity.getId(), entity, "");
    resource.add(linkTo(methodOn(ProjectController.class).getJson(entity.getProjectId())).withRel(PROJECT));

    entity.getOwnedEnd().forEach(
        propertyId -> {
          Link link = linkTo(methodOn(PropertyController.class).get(propertyId)).withRel(OWNED_END);
          resource.add(new HalLink(link));
        }
    );
    entity.getMemberEnd().forEach(
        propertyId -> resource.add(new HalLink(linkTo(methodOn(PropertyController.class).get(propertyId)).withRel(MEMBER_END)))
    );
    return resource;
  }

  @Override
  protected AssociationResource instantiateResource(Association entity) {
    return new AssociationResource(entity);
  }
}
