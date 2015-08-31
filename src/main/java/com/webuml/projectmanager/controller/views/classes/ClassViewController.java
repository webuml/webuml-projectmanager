package com.webuml.projectmanager.controller.views.classes;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import com.webuml.projectmanager.domain.viewmodel.ClassViewRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/classViews/{classViewId}")
@Relation(collectionRelation = LinkRelations.CLASS_VIEW)
@ExposesResourceFor(ClassView.class)
public class ClassViewController extends CommonErrorController {

  @Inject
  ClassViewRepository repository;

  @Inject
  ClassViewResourceAssembler resourceAssembler;

  @Value("${webuml-ui.baseUri}")
  String webumlUiBaseUri;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("classViewId") ElementViewId classViewId, @RequestBody ClassView classView) throws IOException {
    if (!repository.exists(classViewId)) {
      throw new NoSuchElementException("id : " + classViewId);
    }
    classView.setId(classViewId);
    repository.save(classView);
  }

  @RequestMapping(method = DELETE)
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable("classViewId") ElementViewId elementViewId) {
    ClassView entity = repository.findOne(elementViewId);
    if (entity == null) {
      throw new NoSuchElementException("id" + elementViewId);
    }
    repository.delete(entity);
  }

  @RequestMapping(method = GET, produces = "application/hal+json")
  public ClassViewResource getJson(@PathVariable("classViewId") ElementViewId elementViewId) {
    ClassView classView = repository.findOne(elementViewId);
    if (classView == null) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    return resourceAssembler.toResource(classView);
  }

  @RequestMapping(method = GET, produces = "text/html")
  public void getHtml(@PathVariable("classViewId") ElementViewId elementViewId, HttpServletResponse response) throws IOException {
    if (!repository.exists(elementViewId)) {
      throw new NoSuchElementException("id : " + elementViewId);
    }
    response.sendRedirect(webumlUiBaseUri + "/classViews/" + elementViewId.getValue());
  }
}
