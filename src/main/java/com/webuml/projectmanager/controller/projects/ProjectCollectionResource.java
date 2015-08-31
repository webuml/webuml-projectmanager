package com.webuml.projectmanager.controller.projects;

import org.springframework.hateoas.Resources;

class ProjectCollectionResource extends Resources<ProjectResource> {

  public ProjectCollectionResource(Iterable<ProjectResource> projectResource) {
    super(projectResource);
  }
}
