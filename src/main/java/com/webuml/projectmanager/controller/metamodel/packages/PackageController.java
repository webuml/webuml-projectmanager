package com.webuml.projectmanager.controller.metamodel.packages;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.controller.metamodel.classes.ClassCollectionResourceAssembler;
import com.webuml.projectmanager.domain.metamodel.Package;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.metamodel.helper.PackagesRepository;
import com.webuml.projectmanager.domain.primitives.PackageId;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.OWNING_PACKAGE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@Relation(collectionRelation = OWNING_PACKAGE)
@RequestMapping("/packages/{packageId}")
public class PackageController extends CommonErrorController {

  @Inject
  PackagesRepository repository;

  @Inject
  PackageResourceAssembler resourceAssembler;

  @Inject
  PackageCollectionResourceAssembler collectionResourceAssembler;

  @Inject
  ClassRepository classRepository;

  @Inject
  ClassCollectionResourceAssembler classesResourceAssembler;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  void put(@PathVariable("packageId") PackageId packageId, @RequestBody Package aPackage) {
    Package oldVersion = repository.findOne(packageId);
    if (oldVersion == null) {
      throw new NoSuchElementException("id=" + packageId);
    }
    aPackage.setId(packageId);
    aPackage.setProjectId(oldVersion.getProjectId());
    repository.save(aPackage);
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  public PackageResource get(@PathVariable("packageId") PackageId packageId) {
    Package entity = repository.findOne(packageId);
    if (entity == null) {
      throw new NoSuchElementException("id" + packageId);
    }
    return resourceAssembler.toResource(entity);
  }

  @RequestMapping(method = GET, value = "/ownedMember")
  @ResponseStatus(OK)
  public Resources getOwnedMember(@PathVariable("packageId") PackageId packageId) {

    // TODO package mit ausliefern.
//    List<Package> packages = repository.findByOwningPackage(packageId);
//    Resources packageResources = collectionResourceAssembler.toResource(packages);

    List<com.webuml.projectmanager.domain.metamodel.Class> clazzs = classRepository.findByOwningPackage(packageId);
    Resources classResources = classesResourceAssembler.toResource(clazzs);

    return classResources;
  }
}
