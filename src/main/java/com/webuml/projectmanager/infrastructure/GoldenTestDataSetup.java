package com.webuml.projectmanager.infrastructure;

import com.webuml.projectmanager.domain.metamodel.Association;
import com.webuml.projectmanager.domain.metamodel.helper.AssociationRepository;
import com.webuml.projectmanager.domain.metamodel.Class;
import com.webuml.projectmanager.domain.metamodel.helper.ClassRepository;
import com.webuml.projectmanager.domain.metamodel.Package;
import com.webuml.projectmanager.domain.metamodel.helper.PackagesRepository;
import com.webuml.projectmanager.domain.metamodel.Property;
import com.webuml.projectmanager.domain.metamodel.helper.PropertyRepository;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.PackageId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import com.webuml.projectmanager.domain.projectmodel.Project;
import com.webuml.projectmanager.domain.projectmodel.ProjectRepository;
import com.webuml.projectmanager.domain.viewmodel.AssociationView;
import com.webuml.projectmanager.domain.viewmodel.AssociationViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import com.webuml.projectmanager.domain.viewmodel.ClassViewRepository;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import com.webuml.projectmanager.domain.viewmodel.LightTableViewRepository;
import com.webuml.projectmanager.domain.viewmodel.PathRoutingStrategy;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Component
public class GoldenTestDataSetup {

  private static final Logger LOG = Logger.getLogger(GoldenTestDataSetup.class);

  public static final ProjectId PROJECT_ID = new ProjectId("4711");

  public static final ElementId CLASS_LOCAL_DATE_ID = new ElementId("0814");
  public static final ElementId CLASS_PERSON_ID = new ElementId("0815");
  public static final ElementId CLASS_EMPLOYEE_ID = new ElementId("0816");

  public static final PropertyId PROP_ID_NAME = new PropertyId("1000");
  public static final PropertyId PROP_ID_DATE_OF_BIRTH = new PropertyId("1001");
  public static final PropertyId PROP_ID_NUMBER = new PropertyId("1002");
  public static final PropertyId PROP_FRIENDS_INITIATOR = new PropertyId("1003");
  public static final PropertyId PROP_FRIENDS_INITIATOR2 = new PropertyId("1004");
  public static final PropertyId PROP_FRIENDS_CONFIRMER = new PropertyId("1005");
  public static final PropertyId PROP_FRIENDS_CONFIRMER2 = new PropertyId("1006");
  public static final PropertyId PROP_ID_SALARY = new PropertyId("1005");

  public static final ElementViewId LIGHT_TABLE_VIEW_ID = new ElementViewId("884711");
  public static final ElementViewId CLASS_LOCAL_DATE_VIEW_ID = new ElementViewId("880814");
  public static final ElementViewId CLASS_PERSON_VIEW_ID = new ElementViewId("880815");
  public static final ElementViewId CLASS_EMPLOYEE_VIEW_ID = new ElementViewId("880816");

  public static final ElementId ASSOCIATION_ID_FRIENDS = new ElementId("2000");
  public static final ElementId ASSOCIATION_ID_FRIENDS2 = new ElementId("2001");
  public static final ElementViewId ASSOCIATION_VIEW_ID_1 = new ElementViewId("99001");
  public static final ElementViewId ASSOCIATION_VIEW_ID_2 = new ElementViewId("99002");

  @Inject
  ProjectRepository projectRepository;

  @Inject
  ClassRepository classRepository;

  @Inject
  AssociationRepository associationRepository;

  @Inject
  PropertyRepository propertyRepository;

  @Inject
  PackagesRepository packagesRepository;

  @Inject
  LightTableViewRepository lightTableViewRepository;

  @Inject
  ClassViewRepository classViewRepository;

  @Inject
  AssociationViewRepository associationViewRepository;

  @PostConstruct
  public void persistGoldenTestData() {
    createProject();

    createClass_LocalDate();
    createClass_Person();
    createClass_Employee();

    createAssociation_Friends();
    createAssociation_Friends_2();

    createLightTableView();

    createClassView_LocalDate();
    createClassView_Employee();
    createClassView_Person();

    createAssociationViews();
    createAssociationViews_2();

    LOG.info("Golden Test Project created or updated. projectId : " + PROJECT_ID);
  }

  private void createAssociationViews() {
    AssociationView associationView = new AssociationView(PROJECT_ID);
    associationView.setId(ASSOCIATION_VIEW_ID_1);
    associationView.setParent(LIGHT_TABLE_VIEW_ID);
    associationView.setRouting(PathRoutingStrategy.STRAIGHT);
    associationView.setAssociation(ASSOCIATION_ID_FRIENDS);
    associationViewRepository.save(associationView);

  }

  private void createAssociationViews_2() {
    AssociationView associationView = new AssociationView(PROJECT_ID);
    associationView.setId(ASSOCIATION_VIEW_ID_2);
    associationView.setParent(LIGHT_TABLE_VIEW_ID);
    associationView.setRouting(PathRoutingStrategy.STRAIGHT);
    associationView.setAssociation(ASSOCIATION_ID_FRIENDS2);
    associationViewRepository.save(associationView);

  }

  private void createClassView_LocalDate() {
    ClassView classView = new ClassView(PROJECT_ID, CLASS_LOCAL_DATE_ID, LIGHT_TABLE_VIEW_ID);
    classView.setId(CLASS_LOCAL_DATE_VIEW_ID);
    classView.setX(10);
    classView.setY(10);
    classView.setZ(1);
    classView.setW(80);
    classView.setH(60);
    classViewRepository.save(classView);
  }

  private void createClassView_Person() {
    ClassView classView = new ClassView(PROJECT_ID, CLASS_PERSON_ID, LIGHT_TABLE_VIEW_ID);
    classView.setId(CLASS_PERSON_VIEW_ID);
    classView.setX(100);
    classView.setY(10);
    classView.setZ(1);
    classView.setW(80);
    classView.setH(60);
    classViewRepository.save(classView);
  }

  private void createClassView_Employee() {
    ClassView classView = new ClassView(PROJECT_ID, CLASS_EMPLOYEE_ID, LIGHT_TABLE_VIEW_ID);
    classView.setId(CLASS_EMPLOYEE_VIEW_ID);
    classView.setX(100);
    classView.setY(80);
    classView.setZ(1);
    classView.setW(80);
    classView.setH(60);
    classViewRepository.save(classView);
  }

  private void createLightTableView() {
    LightTableView lightTableView = new LightTableView(PROJECT_ID);
    lightTableView.setId(LIGHT_TABLE_VIEW_ID);
    lightTableView.setName("Simple light view for golden test project");
    lightTableView.setX(1);
    lightTableView.setY(1);
    lightTableView.setScale(1);
    lightTableViewRepository.save(lightTableView);
  }

  private void createAssociation_Friends() {

    Property a = new Property();
    a.setId(PROP_FRIENDS_INITIATOR);
    a.setTypeId(CLASS_PERSON_ID);
    a.setName("friendshipInitiator");
    propertyRepository.save(a);

    Property b = new Property();
    b.setId(PROP_FRIENDS_CONFIRMER);
    b.setTypeId(CLASS_PERSON_ID);
    b.setName("friendshipConfirmer");
    propertyRepository.save(b);

    Set<PropertyId> ownedEnd = new HashSet<>();
    ownedEnd.add(a.getId());
    ownedEnd.add(b.getId());

    Association association = new Association();
    association.setId(ASSOCIATION_ID_FRIENDS);
    association.setProjectId(PROJECT_ID);
    association.setName("Friends");
    association.setMemberEnd(ownedEnd);
    association.setOwnedEnd(ownedEnd);

    associationRepository.save(association);
  }

  private void createAssociation_Friends_2() {

    Property a = new Property();
    a.setId(PROP_FRIENDS_INITIATOR2);
    a.setTypeId(CLASS_PERSON_ID);
    a.setName("friendshipInitiator");
    propertyRepository.save(a);

    Property b = new Property();
    b.setId(PROP_FRIENDS_CONFIRMER2);
    b.setTypeId(CLASS_EMPLOYEE_ID);
    b.setName("friendshipConfirmer");
    propertyRepository.save(b);

    Set<PropertyId> ownedEnd = new HashSet<>();
    ownedEnd.add(a.getId());
    ownedEnd.add(b.getId());

    Association association = new Association();
    association.setId(ASSOCIATION_ID_FRIENDS2);
    association.setProjectId(PROJECT_ID);
    association.setName("Friends");
    association.setMemberEnd(ownedEnd);
    association.setOwnedEnd(ownedEnd);

    associationRepository.save(association);
  }

  private void createClass_LocalDate() {
    Class clazz = new Class();
    clazz.setId(CLASS_LOCAL_DATE_ID);
    clazz.setProjectId(PROJECT_ID);
    clazz.setName("LocalDate");
    classRepository.save(clazz);
  }

  private PackageId createPackage(String name) {
    com.webuml.projectmanager.domain.metamodel.Package aPackage = new Package();
    aPackage.setName(name);
    aPackage.setProjectId(PROJECT_ID);
    packagesRepository.save(aPackage);
    return aPackage.getId();
  }

  private void createProject() {
    Project project = new Project();
    project.setId(PROJECT_ID);
    project.setName("Golden Test Project");
    projectRepository.save(project);
  }

  private void createClass_Person() {
    Class clazz = new Class();
    clazz.setId(CLASS_PERSON_ID);
    clazz.setProjectId(PROJECT_ID);
    clazz.setName("Person");
    clazz.getOwnedAttribute().add(createProperty(PROP_ID_NAME, "name", null).getId());
    clazz.getOwnedAttribute().add(createProperty(PROP_ID_DATE_OF_BIRTH, "dateOfBirth", CLASS_LOCAL_DATE_ID).getId());
    clazz.setOwningPackage(createPackage("com.webuml.person"));
    classRepository.save(clazz);
  }

  private void createClass_Employee() {
    Class clazz = new com.webuml.projectmanager.domain.metamodel.Class();
    clazz.setId(CLASS_EMPLOYEE_ID);
    clazz.setName("Employee");
    clazz.setProjectId(PROJECT_ID);
    clazz.getOwnedAttribute().add(createProperty(PROP_ID_NUMBER, "number", null).getId());
    clazz.getOwnedAttribute().add(createProperty(PROP_ID_SALARY, "salary", null).getId());
    clazz.setOwningPackage(createPackage("com.webuml.employee"));
    classRepository.save(clazz);
  }

  private Property createProperty(PropertyId id, String name, ElementId typeId) {
    Property property = new Property();
    property.setId(id);
    property.setName(name);
    property.setTypeId(typeId);
    propertyRepository.save(property);
    return property;
  }
}
