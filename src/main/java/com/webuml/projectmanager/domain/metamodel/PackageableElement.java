package com.webuml.projectmanager.domain.metamodel;

import com.webuml.projectmanager.domain.primitives.PackageId;

public interface PackageableElement {

  PackageId getOwningPackage();

  void setOwningPackage(PackageId packageId);
}
