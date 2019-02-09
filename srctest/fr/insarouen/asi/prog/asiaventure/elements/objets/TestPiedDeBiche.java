package fr.insarouen.asi.prog.asiaventure.elements.objets;

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

public class TestPiedDeBiche {
  private Monde monde;
  private PiedDeBiche pdb;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    pdb = new PiedDeBiche("pdbTest",monde);
  }

  @Test
  public void testConstructeur() throws Exception{
    assertThat(pdb.getNom(),IsEqual.equalTo("pdbTest"));
    assertThat(pdb.getMonde(),IsSame.sameInstance(monde));
  }

  @Test
  public void testEstDeplacable(){
    assertThat(pdb.estDeplacable(),Is.is(true));
  }
}
