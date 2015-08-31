package com.webuml.projectmanager.controller.metamodel.associations;

import com.webuml.projectmanager.domain.metamodel.Association;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationRepository;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.webuml.projectmanager.controller.LinkRelations.ASSOCIATIONS;
import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Relation(collectionRelation = ASSOCIATIONS)
@RequestMapping("/associations")
public class AssociationCollectionController {

  @Inject
  AssociationRepository associationRepository;

  @Inject
  ProjectRepository projectRepository;

  @Inject
  AssociationResourceAssembler resourceAssembler;

  @Inject
  AssociationCollectionResourceAssembler resourceListAssembler;

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public AssociationResource post(@RequestParam(value = "projectId", required = true) ProjectId projectId, HttpServletResponse response) {

    if (!projectRepository.exists(projectId)) {
      throw new NoSuchElementException("No project exists with ProjectId=" + projectId);
    }

    Association entity = new Association();
    entity.setProjectId(projectId);
    associationRepository.save(entity);
    AssociationResource resource = resourceAssembler.toResource(entity);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  public AssociationCollectionResource find(
      @RequestParam(value = "projectId", required = false) ProjectId projectId,
      @RequestParam(value = "typeId", required = false) PropertyId typeId) {

    if (projectId == null && typeId == null) {
      throw new IllegalArgumentException("No request parameter given.");
    }

    Set<Association> projectAssociations = associationRepository.findByProjectId(projectId);

    Set<Association> typeAssociations = associationRepository.findByOwnedEnd(typeId);

    Set<Association> entities = new HashSet<>();
    entities.addAll(projectAssociations);
    entities.addAll(typeAssociations);

    AssociationCollectionResource resource = resourceListAssembler.toResource(entities);
    resource.add(linkTo(methodOn(AssociationCollectionController.class).find(projectId, typeId)).withRel(REL_SELF));
    return resource;
  }
}
