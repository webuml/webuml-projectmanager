package com.webuml.projectmanager.controller.metamodel.property;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.metamodel.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.TYPE;
import static com.webuml.projectmanager.controller.LinkRelations.shortRel;

@Component
class PropertyResourceAssembler extends ResourceAssemblerSupport<Property, PropertyResource> {

  @Value("${baseUri}")
  String baseUri;

  public PropertyResourceAssembler() {
    super(PropertyController.class, PropertyResource.class);
  }

  @Override
  public PropertyResource toResource(Property entity) {
    PropertyResource resource = createResourceWithId(entity.getId(), entity, "");

    if (entity.getTypeId() != null) {
      resource.add(new HalLink(baseUri + "/types/" + entity.getTypeId(), TYPE, shortRel(TYPE)));
    }
    return resource;
  }

  @Override
  protected PropertyResource instantiateResource(Property entity) {
    return new PropertyResource(entity);
  }
}
