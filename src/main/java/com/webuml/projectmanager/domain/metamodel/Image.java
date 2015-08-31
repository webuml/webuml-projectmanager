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

@Document(collection = "images")
public class Image {

  String content;
  String format;
  String location;

  StereotypeId owner;

  @Indexed
  ImageId id;

  public Image() {}

  public StereotypeId getOwner() {
    return owner;
  }

  public void setOwner(StereotypeId owner) {
    this.owner = owner;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
