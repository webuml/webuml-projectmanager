package com.webuml.projectmanager.domain.viewmodel.geometrymodel;

import com.webuml.projectmanager.domain.viewmodel.ElementView;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;

public abstract class RectangleView extends ElementView implements Rectangle {

  double x;
  double y;
  double z;
  double w;
  double h;

  public RectangleView() {
    super();
  }

  public RectangleView(ElementViewId id) {
    super(id);
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
    return z;
  }

  @Override
  public double getW() {
    return w;
  }

  @Override
  public double getH() {
    return h;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public void setW(double w) {
    this.w = w;
  }

  public void setH(double h) {
    this.h = h;
  }
}
