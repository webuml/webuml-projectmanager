package com.webuml.projectmanager.controller.views.lighttables;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import com.webuml.projectmanager.domain.viewmodel.LightTableViewRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/lightTables/{lightTableId}")
@Relation(collectionRelation = LinkRelations.LIGHT_TABLE)
@ExposesResourceFor(LightTableView.class)
public class LightTableViewController extends CommonErrorController {

  @Inject
  LightTableViewRepository repository;

  @Inject
  LightTableViewResourceAssembler resourceAssembler;

  @Value("${webuml-ui.baseUri}")
  String webumlUiBaseUri;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("lightTableId") ElementViewId elementViewId, @RequestBody LightTableView lightTableView) throws IOException {
    if (!repository.exists(elementViewId)) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    lightTableView.setId(elementViewId);
    repository.save(lightTableView);
  }

  @RequestMapping(method = GET, produces = "application/hal+json")
  public LightTableViewResource getJson(@PathVariable("lightTableId") ElementViewId elementViewId) {
    LightTableView lightTableView = repository.findOne(elementViewId);
    if (lightTableView == null) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    return resourceAssembler.toResource(lightTableView);
  }

  @RequestMapping(method = GET, produces = "text/html")
  public void getHtml(@PathVariable("lightTableId") ElementViewId elementViewId, HttpServletResponse response) throws IOException {
    if (!repository.exists(elementViewId)) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    response.sendRedirect(webumlUiBaseUri + "/lightTables/" + elementViewId.getValue());
  }
}
