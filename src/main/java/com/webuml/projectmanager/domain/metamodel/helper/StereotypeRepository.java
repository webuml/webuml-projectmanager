package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.metamodel.Package;
import com.webuml.projectmanager.domain.metamodel.Stereotype;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StereotypeRepository extends CrudRepository<Stereotype, ElementId> {

  List<Stereotype> findByOwningPackage(PackageId packageId);
}
