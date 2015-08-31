package com.webuml.projectmanager.controller.projects;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.Project;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/projects")
@Relation(collectionRelation = LinkRelations.PROJECTS)
public class ProjectCollectionController extends CommonErrorController {

  @Inject
  ProjectRepository repository;

  @Inject
  ProjectResourceAssembler resourceAssembler;

  @Inject
  ProjectCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST, produces = "application/json")
  @ResponseStatus(CREATED)
  public ProjectResource createRepository(HttpServletResponse response) throws IOException {
    Project project = new Project(new ProjectId());
    project = repository.save(project);
    ProjectResource resource = resourceAssembler.toResource(project);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET, produces = "application/json")
  @ResponseStatus(OK)
  @ResponseBody
  public ProjectCollectionResource get() {
    return resourcesAssembler.toResource(repository.findAll());
  }
}
