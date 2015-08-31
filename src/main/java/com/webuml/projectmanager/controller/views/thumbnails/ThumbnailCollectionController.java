package com.webuml.projectmanager.controller.views.thumbnails;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.ThumbnailRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/thumbnails")
@Relation(collectionRelation = LinkRelations.LIGHT_TABLES)
public class ThumbnailCollectionController extends CommonErrorController {

  @Inject
  ThumbnailRepository repository;

  @Inject
  ThumbnailResourceAssembler resourceAssembler;

  @Inject
  ThumbnailCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = GET, produces = "application/json")
  @ResponseStatus(OK)
  @ResponseBody
  public ThumbnailCollectionResource get(@RequestParam(value = "limit", required = false, defaultValue = "10") ProjectId projectId) {
    return resourcesAssembler.toResource(repository.findAll());
  }
}
