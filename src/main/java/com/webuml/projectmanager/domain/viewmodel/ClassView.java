package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.geometrymodel.RectangleView;

public class ClassView extends RectangleView {

  ProjectId projectId;
  ElementId classId;
  ElementViewId parent;

  private ClassView() {
    // for serialization only
  }

  public ClassView(ProjectId projectId, ElementId classId, ElementViewId parent) {
    this.projectId = projectId;
    this.classId = classId;
    this.parent = parent;
  }

  public ElementId getClassId() {
    return classId;
  }

  public ElementViewId getParent() {
    return parent;
  }

  public ProjectId getProjectId() {
    return projectId;
  }

}
