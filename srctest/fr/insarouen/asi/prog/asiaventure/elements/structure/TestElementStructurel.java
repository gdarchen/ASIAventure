package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestElementStructurel {
  private Monde monde;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
  }

  @Test
  public void testConstructeur() throws Exception {
    ElementStructurel element = new ElementStructurel("elementTest",monde){};
    assertThat(element.getMonde().getNom(),IsEqual.equalTo("mondeTest"));
    assertThat(element.getNom(),IsEqual.equalTo("elementTest"));
  }

}
