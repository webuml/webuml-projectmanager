package com.webuml.projectmanager.controller.views.lighttables;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import com.webuml.projectmanager.domain.viewmodel.LightTableViewRepository;
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
@RequestMapping("/lightTables")
@Relation(collectionRelation = LinkRelations.LIGHT_TABLES)
public class LightTableViewCollectionController extends CommonErrorController {

  @Inject
  LightTableViewRepository repository;

  @Inject
  LightTableViewResourceAssembler resourceAssembler;

  @Inject
  LightTableViewCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST, produces = "application/json")
  @ResponseStatus(CREATED)
  public LightTableViewResource createRepository(LightTableView lightTableView, HttpServletResponse response) throws IOException {
    lightTableView.setId(new ElementViewId());
    lightTableView = repository.save(lightTableView);
    LightTableViewResource resource = resourceAssembler.toResource(lightTableView);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET, produces = "application/json")
  @ResponseStatus(OK)
  @ResponseBody
  public LightTableViewCollectionResource get(@RequestParam(value = "projectId", required = false) ProjectId projectId) {
    if (projectId != null) {
      return resourcesAssembler.toResource(repository.findByProjectId(projectId));
    }
    return resourcesAssembler.toResource(repository.findAll());
  }
}
