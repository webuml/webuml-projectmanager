package com.webuml.projectmanager.controller.views.classes;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.metamodel.classes.ClassController;
import com.webuml.projectmanager.controller.views.lighttables.LightTableViewController;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.CLASS;
import static com.webuml.projectmanager.controller.LinkRelations.LIGHT_TABLE;
import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ClassViewResourceAssembler extends ResourceAssemblerSupport<ClassView, ClassViewResource> {

  public ClassViewResourceAssembler() {
    super(ClassViewCollectionController.class, ClassViewResource.class);
  }

  @Override
  public ClassViewResource toResource(ClassView classView) {
    ClassViewResource resource = createResourceWithId(classView.getId(), classView);

    Link clazz = linkTo(methodOn(ClassController.class).get(classView.getClassId())).withRel(CLASS);
    resource.add(new HalLink(clazz, shortRel(CLASS)));

    Link parentView = linkTo(methodOn(LightTableViewController.class).getJson(classView.getParent())).withRel(LIGHT_TABLE);
    resource.add(new HalLink(parentView, "parent"));

    return resource;
  }

  @Override
  protected ClassViewResource instantiateResource(ClassView entity) {
    return new ClassViewResource(entity);
  }
}
