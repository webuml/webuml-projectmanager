package com.webuml.projectmanager.controller.views.classes;

import com.webuml.projectmanager.domain.viewmodel.ClassView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

class ClassViewResource extends Resource<ClassView> {

  public ClassViewResource(ClassView content, Link... links) {
    super(content, links);
  }

  public ClassViewResource(ClassView content, Iterable<Link> links) {
    super(content, links);
  }
}
