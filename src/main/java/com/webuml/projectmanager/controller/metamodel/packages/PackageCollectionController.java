package com.webuml.projectmanager.controller.metamodel.packages;

import com.webuml.projectmanager.domain.metamodel.Package;
import com.webuml.projectmanager.domain.metamodel.helper.PackagesRepository;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.PACKAGES;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Relation(collectionRelation = PACKAGES)
@RequestMapping("/packages")
public class PackageCollectionController {

  @Inject
  PackagesRepository repository;

  @Inject
  ProjectRepository projectRepository;

  @Inject
  PackageResourceAssembler resourceAssembler;

  @Inject
  PackageCollectionResourceAssembler resourcesAssembler;

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public PackageResource post(@RequestParam(value = "projectId", required = true) ProjectId projectId, HttpServletResponse response) {

    if (!projectRepository.exists(projectId)) {
      throw new NoSuchElementException("No project exists with ProjectId=" + projectId);
    }

    Package entity = new Package();
    entity.setProjectId(projectId);
    repository.save(entity);
    PackageResource resource = resourceAssembler.toResource(entity);
    response.addHeader("Location", resource.getId().getHref());
    return resource;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  public PackageCollectionResource getPackagesForProject(@RequestParam("projectId") ProjectId projectId) {
    List<Package> packages = repository.findByProjectId(projectId);
    return resourcesAssembler.toResource(projectId, packages);
  }
}
