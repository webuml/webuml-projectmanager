package com.webuml.projectmanager.controller.metamodel.classes;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ClassCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<com.webuml.projectmanager.domain.metamodel.Class>, ClassCollectionResource> {

  @Inject
  ClassResourceAssembler resourceAssembler;

  public ClassCollectionResourceAssembler() {
    super(ClassCollectionController.class, ClassCollectionResource.class);
  }

  @Override
  public ClassCollectionResource toResource(Iterable<com.webuml.projectmanager.domain.metamodel.Class> classCollection) {
    ArrayList<ClassResource> resourceList = new ArrayList<>();
    for (com.webuml.projectmanager.domain.metamodel.Class project : classCollection) {
      ClassResource resource = resourceAssembler.toResource(project);
      resourceList.add(resource);
    }
    return new ClassCollectionResource(resourceList);
  }

  public ClassCollectionResource toResource(ProjectId projectId, Iterable<com.webuml.projectmanager.domain.metamodel.Class> classes) {
    ClassCollectionResource resource = toResource(classes);
    Link self = linkTo(methodOn(ClassCollectionController.class).getClassForProject(projectId)).withRel(Link.REL_SELF);
    resource.add(new HalLink(self, shortRel(Link.REL_SELF)));
    return resource;
  }
}
