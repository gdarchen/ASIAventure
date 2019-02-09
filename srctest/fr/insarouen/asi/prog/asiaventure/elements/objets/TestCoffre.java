package fr.insarouen.asi.prog.asiaventure.elements.objets;

import java.util.*;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestCoffre{

  private Monde monde;
  private Coffre coffre;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    coffre = new Coffre("coffreTest",monde);
  }

  @Test
  public void testConstructeur(){
    assertThat(coffre.getNom(),IsEqual.equalTo("coffreTest"));
    assertThat(coffre.getMonde(),IsSame.sameInstance(monde));
  }

  @Test
  public void testEstDeplacable(){
    assertThat(coffre.estDeplacable(),Is.is(false));
  }

  @Test
  public void testActivableAvec() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(coffre.activableAvec(pdb),Is.is(true));
  }

  @Test
  public void testActiver() throws Exception{
    assertThat(coffre.getEtat(),IsEqual.equalTo(Etat.FERME));
    coffre.activer();
    assertThat(coffre.getEtat(),IsEqual.equalTo(Etat.OUVERT));
  }

  @Test(expected = ActivationException.class)
  public void testActiverCoffreCasse() throws Exception {
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    coffre.activerAvec(pdb);
    coffre.activer();
  }
}
