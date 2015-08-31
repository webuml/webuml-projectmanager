package com.webuml.projectmanager.controller.metamodel.property;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.metamodel.Property;
import com.webuml.projectmanager.domain.metamodel.helper.PropertyRepository;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.webuml.projectmanager.controller.LinkRelations.PROPERTIES;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Relation(collectionRelation = PROPERTIES)
@RequestMapping("/properties")
public class PropertyCollectionController extends CommonErrorController {

  @Inject
  PropertyRepository repository;

  @Inject
  ProjectRepository projectRepository;
  
  @Inject
  PropertyResourceAssembler resourceAssembler;

  @Inject
  PropertyCollectionResourceAssembler collectionResourceAssembler;

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public PropertyResource post(HttpServletResponse response) {
    com.webuml.projectmanager.domain.metamodel.Property entity = new Property();
    repository.save(entity);
    PropertyResource resource = resourceAssembler.toResource(entity);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET)
  public PropertyCollectionResource get(@RequestParam(value = "typeId", required = false) ElementId typeId) {
    Iterable<Property> properties;
    if (typeId != null && typeId.isValid()) {
      properties = repository.findByTypeId(typeId);
    } else {
      properties = repository.findAll();
    }
    if (properties == null) {
      throw new NoSuchElementException("No property with typeId : " + typeId);
    }
    return collectionResourceAssembler.toResource(typeId, properties);
  }

}