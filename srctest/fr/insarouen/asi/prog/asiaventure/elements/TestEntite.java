package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestEntite {
  private Entite entite ;

  @Before
  public void avantTest() throws Exception{
    Monde monde = new Monde("mondeTest");
    entite = new Entite("entiteTest",monde){};
  }

  @Test
  public void testGetNom(){
    assertThat(entite.getNom(),IsEqual.equalTo("entiteTest"));
  }

  @Test
  public void testGetMonde(){
    assertThat(entite.getMonde().getNom(),IsEqual.equalTo("mondeTest"));
  }

  @Test
  public void testEquals() throws Exception{
    Monde monde2 = new Monde("mondeTest2");
    Entite entite2 = new Entite("entiteTest2",monde2){};
    assertThat(entite.equals(entite), Is.is(true));
    assertThat(entite.equals(entite2), Is.is(false));
  }
}
