package com.webuml.projectmanager.controller.metamodel.property;

import com.webuml.projectmanager.domain.metamodel.Property;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class PropertyResource extends Resource<Property> {

  public PropertyResource(Property content, Link... links) {
    super(content, links);
  }

  public PropertyResource(Property content, Iterable<Link> links) {
    super(content, links);
  }
}
