package com.webuml.projectmanager.controller.projects;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.projectmodel.Project;
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
class ProjectCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<Project>, ProjectCollectionResource> {

  @Inject
  ProjectResourceAssembler resourceAssembler;

  public ProjectCollectionResourceAssembler() {
    super(ProjectCollectionController.class, ProjectCollectionResource.class);
  }

  @Override
  public ProjectCollectionResource toResource(Iterable<Project> projectCollection) {

    ArrayList<ProjectResource> resourceList = new ArrayList<>();
    for (Project project : projectCollection) {
      ProjectResource resource = resourceAssembler.toResource(project);
      resourceList.add(resource);
    }
    ProjectCollectionResource collectionResource = new ProjectCollectionResource(resourceList);
    Link self = linkTo(methodOn(ProjectCollectionController.class).get()).withRel(REL_SELF);
    collectionResource.add(new HalLink(self, shortRel(REL_SELF)));
    return collectionResource;
  }
}
