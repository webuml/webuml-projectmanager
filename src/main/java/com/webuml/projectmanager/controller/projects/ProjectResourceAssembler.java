package com.webuml.projectmanager.controller.projects;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.metamodel.associations.AssociationCollectionController;
import com.webuml.projectmanager.controller.metamodel.classes.ClassCollectionController;
import com.webuml.projectmanager.controller.metamodel.packages.PackageCollectionController;
import com.webuml.projectmanager.controller.views.lighttables.LightTableViewCollectionController;
import com.webuml.projectmanager.domain.projectmodel.Project;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {

  public ProjectResourceAssembler() {
    super(ProjectCollectionController.class, ProjectResource.class);
  }

  @Override
  public ProjectResource toResource(Project project) {
    ProjectResource resource = createResourceWithId(project.getId(), project);

    Link classesLink = linkTo(methodOn(ClassCollectionController.class).getClassForProject(project.getId())).withRel(CLASSES);
    resource.add(new HalLink(classesLink, shortRel(classesLink.getRel())));

    Link packagesLink = linkTo(methodOn(PackageCollectionController.class).getPackagesForProject(project.getId())).withRel(PACKAGES);
    resource.add(new HalLink(packagesLink, shortRel(packagesLink.getRel())));

    Link associationsLink = linkTo(methodOn(AssociationCollectionController.class).find(project.getId(), null)).withRel(ASSOCIATIONS);
    resource.add(new HalLink(associationsLink, shortRel(associationsLink.getRel())));

    Link lightTableViewLink = linkTo(methodOn(LightTableViewCollectionController.class).get(project.getId())).withRel(LIGHT_TABLES);
    resource.add(new HalLink(lightTableViewLink, shortRel(lightTableViewLink.getRel())));
    return resource;
  }

  @Override
  protected ProjectResource instantiateResource(Project entity) {
    return new ProjectResource(entity);
  }
}
