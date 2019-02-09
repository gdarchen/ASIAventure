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

public class TestObjet {
  private Monde monde;
  private Objet obj;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    obj = new Objet("objetTest",monde){
      public boolean estDeplacable(){
        return false;
      }
    };
  }

  @Test
  public void testConstructeur() throws Exception{
    assertThat(obj.getNom(),IsEqual.equalTo("objetTest"));
    assertThat(obj.getMonde(),IsSame.sameInstance(monde));
  }
}
