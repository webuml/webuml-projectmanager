package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.primitives.PropertyId;

import java.util.Set;

public interface OwnedEndHolder {

  Set<PropertyId> getOwnedEnd();

  void setOwnedEnd(Set<PropertyId> ownedEnds);
}
