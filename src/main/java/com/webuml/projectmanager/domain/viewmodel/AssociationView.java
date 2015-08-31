package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.ProjectId;

public class AssociationView extends ElementView {

  ProjectId projectId;

  ElementId association;

  ElementViewId parent;

  PathRoutingStrategy routing;

  private AssociationView() {
    // for serialization only
  }

  public AssociationView(ProjectId projectId) {
    super();
    this.projectId = projectId;
  }

  public AssociationView(ElementViewId id, ProjectId projectId) {
    super(id);
    this.projectId = projectId;
  }

  public ElementViewId getParent() {
    return parent;
  }

  public void setParent(ElementViewId parent) {
    this.parent = parent;
  }

  public ElementId getAssociation() {
    return association;
  }

  public void setAssociation(ElementId association) {
    this.association = association;
  }

  public PathRoutingStrategy getRouting() {
    return routing;
  }

  public void setRouting(PathRoutingStrategy routing) {
    this.routing = routing;
  }
}
