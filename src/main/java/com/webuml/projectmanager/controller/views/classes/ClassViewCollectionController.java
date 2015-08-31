package com.webuml.projectmanager.controller.views.classes;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import com.webuml.projectmanager.domain.viewmodel.ClassViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/classViews")
@Relation(collectionRelation = LinkRelations.CLASS_VIEWS)
public class ClassViewCollectionController extends CommonErrorController {

  @Inject
  ClassViewRepository repository;

  @Inject
  ClassViewResourceAssembler resourceAssembler;

  @Inject
  ClassViewCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST, produces = "application/json")
  @ResponseStatus(CREATED)
  public ClassViewResource createRepository(@RequestParam(value = "projectId", required = true) ProjectId projectId,
                                            @RequestParam(value = "classId", required = true) ElementId classId,
                                            @RequestParam(value = "parentViewId", required = true) ElementViewId parentViewId,
                                            HttpServletResponse response) throws IOException {
    ClassView classView=new ClassView(projectId, classId, parentViewId);
    classView = repository.save(classView);
    ClassViewResource resource = resourceAssembler.toResource(classView);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET, produces = "application/json")
  @ResponseStatus(OK)
  @ResponseBody
  public ClassViewCollectionResource get(@RequestParam(value = "lightTableId", required = false) ElementViewId parentViewId) {
    if (parentViewId != null) {
      return resourcesAssembler.toResource(repository.findByParent(parentViewId));
    }
    return resourcesAssembler.toResource(repository.findAll());
  }
}
