package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface LightTableViewRepository extends CrudRepository<LightTableView, ElementViewId> {

  public Collection<LightTableView> findByProjectId(ProjectId projectId);

}
