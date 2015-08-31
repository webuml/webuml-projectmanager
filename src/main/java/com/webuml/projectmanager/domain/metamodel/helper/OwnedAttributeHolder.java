package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.primitives.PropertyId;

import java.util.Set;

public interface OwnedAttributeHolder {

  Set<PropertyId> getOwnedAttribute();

  void setOwnedAttribute(Set<PropertyId> ownedAttributes);
}
