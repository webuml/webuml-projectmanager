package com.webuml.projectmanager.domain.metamodel;

import com.webuml.projectmanager.domain.primitives.ElementId;

public interface TypedElement {

  ElementId getTypeId();

  void setTypeId(ElementId typeId);
}
