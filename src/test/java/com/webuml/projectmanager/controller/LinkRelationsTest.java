package com.webuml.projectmanager.controller;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class LinkRelationsTest {

  @DataProvider
  public Object[][] examples() {
    return new Object[][] {
        {"http://projects.webuml.com/rel/association", "association"},
        {"self", "self"},
        {"/myRel", "myRel"},
        {"/myOtherRel/", ""},
        {null, null},
        {"", ""},
    };
  }

  @Test(dataProvider = "examples")
  public void shortRel(String longRelation, String expected) throws Exception {
    assertThat(LinkRelations.shortRel(longRelation)).isEqualTo(expected);
  }
}
