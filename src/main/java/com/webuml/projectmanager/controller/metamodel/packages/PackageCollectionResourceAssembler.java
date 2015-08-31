package com.webuml.projectmanager.controller.metamodel.packages;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.metamodel.Package;
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
class PackageCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<com.webuml.projectmanager.domain.metamodel.Package>, PackageCollectionResource> {

  @Inject
  PackageResourceAssembler resourceAssembler;

  public PackageCollectionResourceAssembler() {
    super(PackageCollectionController.class, PackageCollectionResource.class);
  }

  public PackageCollectionResource toResource(ProjectId projectId, Iterable<Package> packages) {
    PackageCollectionResource resource = toResource(packages);
    Link self = linkTo(methodOn(PackageCollectionController.class).getPackagesForProject(projectId)).withRel(Link.REL_SELF);
    resource.add(new HalLink(self, shortRel(Link.REL_SELF)));
    return resource;
  }

  @Override
  public PackageCollectionResource toResource(Iterable<Package> packages) {
    ArrayList<PackageResource> resourceList = new ArrayList<>();
    for (Package aPackage : packages) {
      PackageResource resource = resourceAssembler.toResource(aPackage);
      resourceList.add(resource);
    }
    return new PackageCollectionResource(resourceList);
  }
}
