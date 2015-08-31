package com.webuml.projectmanager.domain.metamodel;

import com.webuml.projectmanager.domain.metamodel.helper.OwnedAttributeHolder;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "classes")
public class Class extends Classifier implements OwnedAttributeHolder, PackageableElement {

  Set<PropertyId> ownedAttribute = new HashSet<>();

  @Indexed
  PackageId owningPackage;

  @Override
  public Set<PropertyId> getOwnedAttribute() {
    return ownedAttribute;
  }

  @Override
  public void setOwnedAttribute(Set<PropertyId> ownedAttribute) {
    this.ownedAttribute = ownedAttribute;
  }

  @Override
  public PackageId getOwningPackage() {
    return owningPackage;
  }

  @Override
  public void setOwningPackage(PackageId packageId) {
    this.owningPackage = packageId;
  }
}
