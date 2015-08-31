package com.webuml.projectmanager.controller.metamodel.property;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.metamodel.Property;
import com.webuml.projectmanager.domain.metamodel.helper.PropertyRepository;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import org.springframework.hateoas.core.Relation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.PROPERTY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@Relation(collectionRelation = PROPERTY)
@RequestMapping("/properties/{propertyId}")
public class PropertyController extends CommonErrorController {

  @Inject
  PropertyRepository repository;

  @Inject
  PropertyResourceAssembler resourceAssembler;

  @RequestMapping(method = GET)
  public PropertyResource get(@PathVariable("propertyId") PropertyId propertyId) {
    Property entity = repository.findOne(propertyId);
    if (entity == null) {
      throw new NoSuchElementException("No property with id : " + propertyId);
    }
    return resourceAssembler.toResource(entity);
  }

  @RequestMapping(method = PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void put(@PathVariable("propertyId") PropertyId propertyId, @RequestBody Property property) {
    if (!repository.exists(propertyId)) {
      throw new NoSuchElementException("No property with id : " + propertyId);
    }
    property.setId(propertyId);
    repository.save(property);
  }

}