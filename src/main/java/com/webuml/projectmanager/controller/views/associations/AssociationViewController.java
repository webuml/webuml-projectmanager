package com.webuml.projectmanager.controller.views.associations;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import com.webuml.projectmanager.domain.viewmodel.AssociationViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.ASSOCIATION_VIEW;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/associationViews/{associationViewId}")
@Relation(collectionRelation = ASSOCIATION_VIEW)
@ExposesResourceFor(AssociationView.class)
public class AssociationViewController extends CommonErrorController {

  @Inject
  AssociationViewRepository repository;

  @Inject
  AssociationViewResourceAssembler resourceAssembler;

  @Value("${webuml-ui.baseUri}")
  String webumlUiBaseUri;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("associationViewId") ElementViewId elementViewId, @RequestBody AssociationView associationView) throws IOException {
    if (!repository.exists(elementViewId)) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    associationView.setId(elementViewId);
    repository.save(associationView);
  }

  @RequestMapping(method = DELETE)
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable("associationViewId") ElementViewId elementViewId) throws IOException {
    AssociationView associationView = repository.findOne(elementViewId);
    if (associationView == null) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    repository.delete(associationView);
  }

  @RequestMapping(method = GET, produces = "application/hal+json")
  public AssociationViewResource getJson(@PathVariable("associationViewId") ElementViewId elementViewId) {
    AssociationView associationView = repository.findOne(elementViewId);
    if (associationView == null) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    return resourceAssembler.toResource(associationView);
  }

  @RequestMapping(method = GET, produces = "text/html")
  public void getHtml(@PathVariable("associationViewId") ElementViewId elementViewId, HttpServletResponse response) throws IOException {
    if (!repository.exists(elementViewId)) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    response.sendRedirect(webumlUiBaseUri + "/associationViews/" + elementViewId.getValue());
  }
}
