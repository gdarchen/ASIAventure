package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestClef {
  private Monde monde;
  private Clef clef;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    clef = new Clef("clefTest",monde);
  }

  @Test
  public void testConstructeur(){
    assertThat(clef.getNom(),IsEqual.equalTo("clefTest"));
    assertThat(clef.getMonde(),IsSame.sameInstance(monde));
  }

  @Test
  public void testEstDeplacable(){
    assertThat(clef.estDeplacable(),Is.is(true));
  }
}
