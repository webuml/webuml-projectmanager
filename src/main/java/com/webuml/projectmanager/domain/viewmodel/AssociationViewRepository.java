package com.webuml.projectmanager.domain.viewmodel;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AssociationViewRepository extends CrudRepository<AssociationView, ElementViewId> {

  public Collection<AssociationView> findByParent(ElementViewId parentId);

}
