package com.webuml.projectmanager.controller.viewmodel;

import com.webuml.projectmanager.domain.viewmodel.ClassView;

import static java.lang.Math.abs;

class RectangleLayouter {

  private double maxWidth = 1024;

  private double x = 1;
  private double y = 1;
  private double z = 1;

  private double w;
  private double h;

  private double verticalSpace;
  private double horizontalSpace;

  RectangleLayouter() {
    this.setW(100);
    this.setH(80);
  }

  public void layout(ClassView classView) {
    if (x + w > maxWidth) {
      x = 1;
      y = y + h + verticalSpace;
    }

    classView.setX(x);
    classView.setY(y);
    classView.setZ(z);
    classView.setW(w);
    classView.setH(h);

    x = x + w + horizontalSpace;
  }

  public void setMaxWidth(int width) {
    this.maxWidth = width;
  }

  public double getMaxWidth() {
    return maxWidth;
  }

  public double getW() {
    return w;
  }

  public void setW(double w) {
    this.w = w;
    this.horizontalSpace = abs(w) * 0.1;
  }

  public double getH() {
    return h;
  }

  public void setH(double h) {
    this.h = h;
    this.verticalSpace = abs(h) * 0.1;
  }
}
