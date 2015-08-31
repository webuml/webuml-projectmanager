package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ThumbnailRepository extends CrudRepository<Thumbnail, ElementViewId> {

  public Collection<Thumbnail> findByLightTableViewId(ElementViewId lightTableViewId);

}
