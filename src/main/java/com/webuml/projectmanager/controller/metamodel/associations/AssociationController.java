package com.webuml.projectmanager.controller.metamodel.associations;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.metamodel.Association;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationRepository;
import com.webuml.projectmanager.domain.primitives.ElementId;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.ASSOCIATION;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@Relation(collectionRelation = ASSOCIATION)
@RequestMapping("/associations/{associationId}")
public class AssociationController extends CommonErrorController {

  @Inject
  AssociationRepository repository;

  @Inject
  AssociationResourceAssembler resourceAssembler;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("associationId") ElementId associationId, @RequestBody Association newVersion) {
    Association oldVersion = repository.findOne(associationId);
    if (oldVersion == null) {
      throw new NoSuchElementException("No association exists with ID=" + associationId);
    }
    newVersion.setId(associationId);
    newVersion.setProjectId(oldVersion.getProjectId());
    repository.save(newVersion);
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  @ResponseBody
  public AssociationResource get(@PathVariable("associationId") ElementId associationId) {
    Association entity = repository.findOne(associationId);
    if (entity == null) {
      throw new NoSuchElementException("id" + associationId);
    }
    return resourceAssembler.toResource(entity);
  }

}