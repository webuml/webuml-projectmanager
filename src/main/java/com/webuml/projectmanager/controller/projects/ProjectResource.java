package com.webuml.projectmanager.controller.projects;

import com.webuml.projectmanager.domain.projectmodel.Project;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class ProjectResource extends Resource<Project> {

  public ProjectResource(Project content, Link... links) {
    super(content, links);
  }

  public ProjectResource(Project content, Iterable<Link> links) {
    super(content, links);
  }
}
