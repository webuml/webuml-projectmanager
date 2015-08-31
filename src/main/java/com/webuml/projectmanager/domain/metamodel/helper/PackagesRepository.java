package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.metamodel.*;
import com.webuml.projectmanager.domain.metamodel.Package;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackagesRepository extends CrudRepository<com.webuml.projectmanager.domain.metamodel.Package, ElementId> {

  List<Package> findByProjectId(ProjectId projectId);

  List<Package> findByOwningPackage(PackageId packageId);
}
