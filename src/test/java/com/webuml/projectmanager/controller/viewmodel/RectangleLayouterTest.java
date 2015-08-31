package com.webuml.projectmanager.controller.viewmodel;

import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.viewmodel.ClassView;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RectangleLayouterTest {

  public static final int WIDTH = 100;
  public static final int HEIGHT = 80;
  public static final int MAX_WIDTH = 1024;

  private RectangleLayouter layouter;

  @BeforeMethod
  public void setUp() throws Exception {
    layouter = new RectangleLayouter();
    layouter.setMaxWidth(MAX_WIDTH);
    layouter.setW(WIDTH);
    layouter.setH(HEIGHT);
  }

  @Test
  public void default_layouts_10_classes_per_row_within_the_maxWidth_limit() {
    ClassView[] views = createClassViews(10);
    for (ClassView view : views) {
      layouter.layout(view);
    }

    assertThat(views[8].getY()).describedAs("still on first line").isEqualTo(1);
    assertThat(views[8].getX() + WIDTH).describedAs("still fits on screen").isLessThan(MAX_WIDTH);

    assertThat(views[9].getY()).describedAs("still on first line").isGreaterThan(1 + HEIGHT);
    assertThat(views[9].getX() + WIDTH).describedAs("still fits on screen").isLessThan(MAX_WIDTH);
  }

  @Test
  public void there_is_some_horizontal_space_between_two_neighbour_views() {
    ClassView view1 = createClassView(1);
    ClassView view2 = createClassView(2);

    layouter.layout(view1);
    layouter.layout(view2);

    assertThat(view1.getX()).isEqualTo(1);
    assertThat(view2.getX()).isGreaterThan(1 + WIDTH + 1);
  }

  private ClassView[] createClassViews(int number) {
    ClassView[] views = new ClassView[number];
    for (int i = 0; i < views.length; i++) {
      views[i] = createClassView(i);
    }
    return views;
  }

  private ClassView createClassView(int id) {
    ClassView classView = new ClassView(new ProjectId("egal"), new ElementId("class" + id), new ElementViewId("view" + id));
    return classView;
  }
}