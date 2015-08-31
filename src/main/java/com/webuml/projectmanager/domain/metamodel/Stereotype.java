package com.webuml.projectmanager.domain.metamodel;

import com.webuml.projectmanager.domain.metamodel.helper.OwnedAttributeHolder;
import com.webuml.projectmanager.domain.primitives.ImageId;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import com.webuml.projectmanager.domain.primitives.StereotypeId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "stereotypes")
public class Stereotype extends Class {

  Set<ImageId> icon = new HashSet<>();

  @Indexed
  StereotypeId id;

  public Set<ImageId> getIcon() {
    return icon;
  }

  public void setIcon(Set<ImageId> icon) {
    this.icon = icon;
  }
}
