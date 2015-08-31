package com.webuml.projectmanager.controller.views.associations;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.viewmodel.*;
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
@RequestMapping("/associationViews")
@Relation(collectionRelation = LinkRelations.ASSOCIATION_VIEWS)
public class AssociationViewCollectionController extends CommonErrorController {

  @Inject
  AssociationViewRepository repository;

  @Inject
  AssociationViewResourceAssembler resourceAssembler;

  @Inject
  AssociationViewCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST, produces = "application/json")
  @ResponseStatus(CREATED)
  public AssociationViewResource createRepository(AssociationView associationView, HttpServletResponse response) throws IOException {
    associationView.setId(new ElementViewId());
    associationView = repository.save(associationView);
    AssociationViewResource resource = resourceAssembler.toResource(associationView);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET, produces = "application/json")
  @ResponseStatus(OK)
  @ResponseBody
  public AssociationViewCollectionResource get(@RequestParam(value = "lightTableId", required = false) ElementViewId parentViewId) {
    if (parentViewId != null) {
      return resourcesAssembler.toResource(repository.findByParent(parentViewId));
    }
    return resourcesAssembler.toResource(repository.findAll());
  }
}
