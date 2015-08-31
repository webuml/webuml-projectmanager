package com.webuml.projectmanager.controller.metamodel.classes;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.metamodel.associations.AssociationController;
import com.webuml.projectmanager.controller.metamodel.packages.PackageController;
import com.webuml.projectmanager.controller.metamodel.property.PropertyController;
import com.webuml.projectmanager.controller.projects.ProjectController;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationResolver;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static com.webuml.projectmanager.controller.LinkRelations.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ClassResourceAssembler extends ResourceAssemblerSupport<com.webuml.projectmanager.domain.metamodel.Class, ClassResource> {

  public ClassResourceAssembler() {
    super(ClassCollectionController.class, ClassResource.class);
  }

  @Inject
  AssociationResolver associationResolver;

  @Override
  public ClassResource toResource(com.webuml.projectmanager.domain.metamodel.Class entity) {
    ClassResource resource = createResourceWithId(entity.getId(), entity);

    Link projectLink = linkTo(methodOn(ProjectController.class).getJson(entity.getProjectId())).withRel(PROJECT);
    resource.add(new HalLink(projectLink, shortRel(projectLink.getRel())));

    if (entity.getOwningPackage() != null) {
      Link packageLink = linkTo(methodOn(PackageController.class).get(entity.getOwningPackage())).withRel(OWNING_PACKAGE);
      resource.add(new HalLink(packageLink, shortRel(packageLink.getRel())));
    }

    associationResolver.getAssociations(entity.getId()).forEach(
        association -> resource.add(new HalLink(linkTo(methodOn(AssociationController.class).get(association.getId())).withRel(ASSOCIATION), shortRel(ASSOCIATION)))
    );

    entity.getOwnedAttribute().forEach(
        propertyId -> resource.add(new HalLink(linkTo(methodOn(PropertyController.class).get(propertyId)).withRel(OWNED_ATTRIBUTE), shortRel(OWNED_ATTRIBUTE)))
    );

    return resource;
  }

  @Override
  protected ClassResource instantiateResource(com.webuml.projectmanager.domain.metamodel.Class entity) {
    return new ClassResource(entity);
  }
}
