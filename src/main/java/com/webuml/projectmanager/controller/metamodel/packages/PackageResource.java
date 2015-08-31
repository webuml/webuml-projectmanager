package com.webuml.projectmanager.controller.metamodel.packages;

import com.webuml.projectmanager.domain.metamodel.Package;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class PackageResource extends Resource<Package> {

  public PackageResource(Package content, Link... links) {
    super(content, links);
  }

  public PackageResource(Package content, Iterable<Link> links) {
    super(content, links);
  }
}
