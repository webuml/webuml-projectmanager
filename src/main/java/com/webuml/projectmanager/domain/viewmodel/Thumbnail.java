package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.geometrymodel.Point;

import java.io.Serializable;

public class Thumbnail extends ElementView implements Serializable {

  private ElementViewId lightTableViewId;
  private String imageData;
  private String title;

  private Thumbnail() {
    // for serialization only
  }

  public Thumbnail(ElementViewId id) {
    super(id);
  }

  public ElementViewId getLightTableViewId() {
    return lightTableViewId;
  }

  public void setLightTableViewId(ElementViewId lightTableViewId) {
    this.lightTableViewId = lightTableViewId;
  }

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
