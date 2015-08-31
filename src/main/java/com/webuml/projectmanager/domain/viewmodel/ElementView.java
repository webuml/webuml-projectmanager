package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.Entity;

public abstract class ElementView extends Entity<ElementViewId> {

  public ElementView() {
    super(ElementViewId.class);
  }

  public ElementView(ElementViewId id) {
    super(id);
  }
}
