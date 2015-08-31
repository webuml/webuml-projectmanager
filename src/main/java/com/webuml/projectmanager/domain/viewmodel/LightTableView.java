package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.geometrymodel.Point;

import java.io.Serializable;

public class LightTableView extends ElementView implements Point, Serializable {

  private ProjectId projectId;
  private String name;
  private double y = 0;
  private double x = 0;
  private double scale = 0;

  private LightTableView() {
    // for serialization only
  }

  public LightTableView(ProjectId projectId) {
    this.projectId = projectId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double getZ() {
    return 0;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public ProjectId getProjectId() {
    return projectId;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }
}
