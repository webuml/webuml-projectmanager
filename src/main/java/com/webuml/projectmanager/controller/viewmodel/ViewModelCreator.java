package com.webuml.projectmanager.controller.viewmodel;

import com.webuml.projectmanager.domain.metamodel.Association;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationRepository;
import com.webuml.projectmanager.domain.metamodel.Class;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.projectmodel.Project;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import com.webuml.projectmanager.domain.viewmodel.AssociationViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import com.webuml.projectmanager.domain.viewmodel.ClassViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import com.webuml.projectmanager.domain.viewmodel.LightTableViewRepository;
import com.webuml.projectmanager.domain.viewmodel.PathRoutingStrategy;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
class ViewModelCreator {

  @Inject
  ProjectRepository projectRepository;

  @Inject
  ClassRepository classRepository;

  @Inject
  AssociationRepository associationRepository;

  @Inject
  ClassViewRepository classViewRepository;

  @Inject
  AssociationViewRepository associationViewRepository;

  @Inject
  LightTableViewRepository lightTableViewRepository;

  public ElementViewId createViewModel(ProjectId projectId, UserPreferences userPreferences) {

    Project project = projectRepository.findOne(projectId);

    ElementViewId lightTableViewId = createLightTableView(projectId, project);

    createClassViews(projectId, lightTableViewId, userPreferences);
    createAssociationViews(projectId, lightTableViewId);

    return lightTableViewId;
  }

  private void createAssociationViews(ProjectId projectId, ElementViewId lightTableViewId) {
    Set<Association> associations = associationRepository.findByProjectId(projectId);
    for (Association association : associations) {
      AssociationView view = new AssociationView(projectId);
      view.setParent(lightTableViewId);
      view.setAssociation(association.getId());
      view.setRouting(PathRoutingStrategy.STRAIGHT);
      associationViewRepository.save(view);
    }
  }

  private void createClassViews(ProjectId projectId, ElementViewId lightTableViewId, UserPreferences userPreferences) {
    List<Class> clazzList = classRepository.findByProjectId(projectId);
    sortClasses(clazzList);

    RectangleLayouter layouter = new RectangleLayouter();
    if (userPreferences != null) {
      layouter.setMaxWidth(userPreferences.getWindowWidth());
    }

    for (Class clazz : clazzList) {
      ClassView classView = new ClassView(projectId, clazz.getId(), lightTableViewId);
      layouter.layout(classView);
      classViewRepository.save(classView);
    }
  }

  private void sortClasses(List<com.webuml.projectmanager.domain.metamodel.Class> clazzList) {
    Collections.sort(clazzList, (clazz1, clazz2) -> clazz1.getName().compareTo(clazz2.getName()));
  }

  private ElementViewId createLightTableView(ProjectId projectId, Project project) {
    LightTableView lightTableView = new LightTableView(projectId);
    lightTableView.setName(project.getName());
    lightTableView.setX(1);
    lightTableView.setY(1);
    lightTableView.setScale(1);
    lightTableViewRepository.save(lightTableView);
    return lightTableView.getId();
  }
}
