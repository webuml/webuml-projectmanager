package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.metamodel.*;
import com.webuml.projectmanager.domain.metamodel.Class;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<com.webuml.projectmanager.domain.metamodel.Class, ElementId> {

  List<Class> findByProjectId(ProjectId projectId);

  List<Class> findByOwningPackage(PackageId id);
}
