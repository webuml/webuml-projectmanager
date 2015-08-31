package com.webuml.projectmanager.domain.viewmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ClassViewRepository extends CrudRepository<ClassView, ElementViewId> {

  public Collection<ClassView> findByParent(ElementViewId parentId);

}
