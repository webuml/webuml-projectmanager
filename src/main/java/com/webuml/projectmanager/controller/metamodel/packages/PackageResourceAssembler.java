package com.webuml.projectmanager.controller.metamodel.packages;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.projects.ProjectController;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.metamodel.Package;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static com.webuml.projectmanager.controller.LinkRelations.PROJECT;
import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class PackageResourceAssembler extends ResourceAssemblerSupport<com.webuml.projectmanager.domain.metamodel.Package, PackageResource> {

  public PackageResourceAssembler() {
    super(PackageCollectionController.class, PackageResource.class);
  }

  @Inject
  ClassRepository classRepository;

  @Override
  public PackageResource toResource(com.webuml.projectmanager.domain.metamodel.Package entity) {
    PackageResource resource = createResourceWithId(entity.getId(), entity);

    Link projectLink = linkTo(methodOn(ProjectController.class).getJson(entity.getProjectId())).withRel(PROJECT);
    resource.add(new HalLink(projectLink, shortRel(projectLink.getRel())));

    Link ownedMemberLink = linkTo(methodOn(PackageController.class).getOwnedMember(entity.getId())).withRel(LinkRelations.OWNED_MEMBER);
    resource.add(new HalLink(ownedMemberLink, shortRel(ownedMemberLink.getRel())));

    return resource;
  }

  @Override
  protected PackageResource instantiateResource(Package entity) {
    return new PackageResource(entity);
  }
}
