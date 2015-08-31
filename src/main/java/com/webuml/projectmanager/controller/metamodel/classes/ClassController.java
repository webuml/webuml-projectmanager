package com.webuml.projectmanager.controller.metamodel.classes;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.metamodel.Class;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.primitives.ElementId;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.CLASS;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@Relation(collectionRelation = CLASS)
@RequestMapping("/classes/{classId}")
public class ClassController extends CommonErrorController {

  @Inject
  ClassRepository repository;

  @Inject
  ClassResourceAssembler resourceAssembler;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  void put(@PathVariable("classId") ElementId classId, @RequestBody Class newVersion) {
    Class oldVersion = repository.findOne(classId);
    if (oldVersion == null) {
      throw new NoSuchElementException("id=" + classId);
    }
    newVersion.setId(classId);
    newVersion.setProjectId(oldVersion.getProjectId());
    repository.save(newVersion);
  }

  @RequestMapping(method = GET)
  @ResponseStatus(OK)
  @ResponseBody
  public ClassResource get(@PathVariable("classId") ElementId classId) {
    Class entity = repository.findOne(classId);
    if (entity == null) {
      throw new NoSuchElementException("id" + classId);
    }
    return resourceAssembler.toResource(entity);
  }

}
