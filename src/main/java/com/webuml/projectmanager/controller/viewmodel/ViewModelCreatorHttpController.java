package com.webuml.projectmanager.controller.viewmodel;

import com.webuml.projectmanager.controller.views.lighttables.LightTableViewController;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

import static com.webuml.projectmanager.controller.LinkRelations.LIGHT_TABLE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/viewModelCreation")
public class ViewModelCreatorHttpController {

  @Inject
  ProjectRepository projectRepository;

  @Inject
  ViewModelCreator viewModelCreator;

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public void post(@RequestParam(value = "projectId", required = true) String projectIdStr,
                   @RequestParam(value = "width", required = false) String width,
                   @RequestParam(value = "height", required = false) String height,
                   HttpServletResponse response) throws IOException {

    ProjectId projectId = new ProjectId(projectIdStr);

    if (!projectRepository.exists(projectId)) {
      throw new NoSuchElementException("Project not found for id=" + projectIdStr);
    }

    ElementViewId lightTableViewId = viewModelCreator.createViewModel(projectId, UserPreferences.parseFrom(width, height));
    Link link = linkTo(methodOn(LightTableViewController.class).getJson(lightTableViewId)).withRel(LIGHT_TABLE);
    response.sendRedirect(link.getHref());
  }

  @RequestMapping(method = GET)
  @ResponseStatus(CREATED)
  public void get(@RequestParam(value = "projectId", required = true) String projectIdStr,
                  @RequestParam(value = "width", required = false) String width,
                  @RequestParam(value = "height", required = false) String height,
                  HttpServletResponse response) throws IOException {
    // TODO (20140609, maki) not RESTFUL, but keeps the workflow for importer running
    post(projectIdStr, width, height, response);
  }

}
