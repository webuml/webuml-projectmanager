package com.webuml.projectmanager.controller.metamodel.property;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.metamodel.Property;
import com.webuml.projectmanager.domain.primitives.ElementId;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class PropertyCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<Property>, PropertyCollectionResource> {

  @Inject
  PropertyResourceAssembler resourceAssembler;

  public PropertyCollectionResourceAssembler() {
    super(PropertyCollectionController.class, PropertyCollectionResource.class);
  }

  public PropertyCollectionResource toResource(ElementId typeId, Iterable<Property> properties) {
    PropertyCollectionResource resource = toResource(properties);
    Link self = linkTo(methodOn(PropertyCollectionController.class).get(typeId)).withRel(Link.REL_SELF);
    resource.add(new HalLink(self, shortRel(Link.REL_SELF)));
    return resource;
  }

  @Override
  public PropertyCollectionResource toResource(Iterable<Property> properties) {
    ArrayList<PropertyResource> resourceList = new ArrayList<>();
    for (Property aProperty : properties) {
      PropertyResource resource = resourceAssembler.toResource(aProperty);
      resourceList.add(resource);
    }
    return new PropertyCollectionResource(resourceList);
  }
}
