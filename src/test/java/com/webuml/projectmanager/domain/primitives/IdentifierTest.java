package com.webuml.projectmanager.domain.primitives;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class IdentifierTest {

  @Test
  public void valid() throws Exception {
    Identifier id = new Identifier();
    assertTrue(id.isValid());
  }

  @DataProvider
  public Object[][] invalidProvider() {
    return new Object[][] {
        {null},
        {""},
        {"bla"},
        {"76c347-4743-b48c-40595d139"},
        {"7654b946c347-4743-b48c-40595d1395a"},
        {"7654b946-c347-4743-b48c*40595d1395a"},
        {"7654b946-c347-4743-b48c40595d1395a"},
        {"7654b946-c347-4743-b48c-"},
    };
  }

  @Test(dataProvider = "invalidProvider")
  public void notValid(String inValidId) throws Exception {
    Identifier id = new Identifier();
    id.setValue(inValidId);
    assertFalse(id.isValid());
  }
}
