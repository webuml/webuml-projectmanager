package com.webuml.projectmanager.controller.metamodel.classes;

import com.webuml.projectmanager.domain.metamodel.Class;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.CLASSES;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Relation(collectionRelation = CLASSES)
@RequestMapping("/classes")
public class ClassCollectionController {

  @Inject
  ClassRepository classRepository;

  @Inject
  ProjectRepository projectRepository;

  @Inject
  ClassResourceAssembler resourceAssembler;

  @Inject
  ClassCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public ClassResource post(@RequestParam(value = "projectId", required = true) ProjectId projectId, HttpServletResponse response) {

    if (!projectRepository.exists(projectId)) {
      throw new NoSuchElementException("No project exists with ProjectId=" + projectId);
    }

    Class entity = new com.webuml.projectmanager.domain.metamodel.Class();
    entity.setProjectId(projectId);
    classRepository.save(entity);
    ClassResource resource = resourceAssembler.toResource(entity);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  public ClassCollectionResource getClassForProject(@RequestParam(value = "projectId", required = true) ProjectId projectId) {
    Iterable<com.webuml.projectmanager.domain.metamodel.Class> classes = classRepository.findByProjectId(projectId);
    return resourcesAssembler.toResource(projectId, classes);
  }
}
