package com.webuml.projectmanager.controller.metamodel.type;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.controller.metamodel.associations.AssociationController;
import com.webuml.projectmanager.controller.metamodel.classes.ClassController;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationRepository;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.metamodel.Type;
import com.webuml.projectmanager.domain.primitives.ElementId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/types/{typeId}")
public class TypeController extends CommonErrorController {

  @Inject
  ClassRepository classRepository;

  @Inject
  AssociationRepository associationRepository;

  @RequestMapping(method = GET)
  @ResponseStatus(FOUND)
  public void get(@PathVariable("typeId") ElementId typeId, HttpServletResponse response) throws IOException {
    Type type = null;

    type = classRepository.findOne(typeId);
    if (type != null) {
      URI uri = linkTo(methodOn(ClassController.class).get(typeId)).toUri();
      response.sendRedirect(uri.toString());
      return;
    }

    type = associationRepository.findOne(typeId);
    if (type != null) {
      URI uri = linkTo(methodOn(AssociationController.class).get(typeId)).toUri();
      response.sendRedirect(uri.toString());
      return;
    }

    throw new NoSuchElementException("typeId : " + typeId);
  }
}
