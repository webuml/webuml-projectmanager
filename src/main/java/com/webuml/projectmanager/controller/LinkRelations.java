package com.webuml.projectmanager.controller;

public interface LinkRelations {

  static final String PROJECTS = "http://projects.webuml.com/rel/projects";
  static final String PROJECT = "http://projects.webuml.com/rel/project";

  static final String CLASSES = "http://projects.webuml.com/rel/classes";
  static final String CLASS = "http://projects.webuml.com/rel/class";

  static final String PACKAGES = "http://projects.webuml.com/rel/packages";
  static final String OWNING_PACKAGE = "http://projects.webuml.com/rel/owningPackage";
  static final String OWNED_MEMBER = "http://projects.webuml.com/rel/ownedMember";

  static final String TYPE = "http://projects.webuml.com/rel/type";
  static final String THUMBNAIL = "http://projects.webuml.com/rel/thumbnail";
  static final String THUMBNAILS = "http://projects.webuml.com/rel/thumbnailS";

  static final String ASSOCIATIONS = "http://projects.webuml.com/rel/associations";
  static final String ASSOCIATION = "http://projects.webuml.com/rel/association";

  static final String PROPERTIES = "http://projects.webuml.com/rel/properties";
  static final String PROPERTY = "http://projects.webuml.com/rel/property";

  static final String OWNED_ATTRIBUTE = "http://projects.webuml.com/rel/ownedAttribute";
  static final String OWNED_END = "http://projects.webuml.com/rel/ownedEnd";
  static final String MEMBER_END = "http://projects.webuml.com/rel/memberEnd";

  static final String LIGHT_TABLES = "http://projects.webuml.com/rel/lightTables";
  static final String LIGHT_TABLE = "http://projects.webuml.com/rel/lightTable";

  static final String CLASS_VIEWS = "http://projects.webuml.com/rel/classViews";
  static final String CLASS_VIEW = "http://projects.webuml.com/rel/classView";

  static final String ASSOCIATION_VIEWS = "http://projects.webuml.com/rel/associationViews";
  static final String ASSOCIATION_VIEW = "http://projects.webuml.com/rel/associationView";


  public static String shortRel(String relation) {
    if (relation == null) {
      return null;
    }
    return relation.substring(relation.lastIndexOf("/") + 1, relation.length());
  }
}
