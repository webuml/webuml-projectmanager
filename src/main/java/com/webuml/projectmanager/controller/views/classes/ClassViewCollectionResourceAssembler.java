package com.webuml.projectmanager.controller.views.classes;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ClassViewCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<ClassView>, ClassViewCollectionResource> {

  @Inject
  ClassViewResourceAssembler resourceAssembler;

  public ClassViewCollectionResourceAssembler() {
    super(ClassViewCollectionController.class, ClassViewCollectionResource.class);
  }

  @Override
  public ClassViewCollectionResource toResource(Iterable<ClassView> classViewCollection) {

    ArrayList<ClassViewResource> resourceList = new ArrayList<>();
    for (ClassView classView : classViewCollection) {
      ClassViewResource resource = resourceAssembler.toResource(classView);
      resourceList.add(resource);
    }
    ClassViewCollectionResource collectionResource = new ClassViewCollectionResource(resourceList);
    Link self = linkTo(methodOn(ClassViewCollectionController.class).get(null)).withRel(REL_SELF);
    collectionResource.add(new HalLink(self, shortRel(REL_SELF)));
    return collectionResource;
  }
}
