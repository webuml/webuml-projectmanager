package com.webuml.projectmanager.controller.projects;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.Project;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/projects/{projectId}")
@Relation(collectionRelation = LinkRelations.PROJECT)
@ExposesResourceFor(Project.class)
public class ProjectController extends CommonErrorController {

  @Inject
  ProjectRepository repository;

  @Inject
  ProjectResourceAssembler resourceAssembler;

  @Value("${webuml-ui.baseUri}")
  String webumlUiBaseUri;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("projectId") ProjectId projectId, @RequestBody Project project) throws IOException {
    if (!repository.exists(projectId)) {
      throw new NoSuchElementException("id : " + projectId);
    }
    project.setId(projectId);
    repository.save(project);
  }

  @RequestMapping(method = GET, produces = "application/hal+json")
  public ProjectResource getJson(@PathVariable("projectId") ProjectId projectId) {
    Project project = repository.findOne(projectId);
    if (project == null) {
      throw new NoSuchElementException("id : " + projectId);
    }
    return resourceAssembler.toResource(project);
  }

  @RequestMapping(method = GET, produces = "text/html")
  public void getHtml(@PathVariable("projectId") ProjectId projectId, HttpServletResponse response) throws IOException {
    if (!repository.exists(projectId)) {
      throw new NoSuchElementException();
    }
    response.sendRedirect(webumlUiBaseUri + "/projects/" + projectId.getValue());
  }
}
