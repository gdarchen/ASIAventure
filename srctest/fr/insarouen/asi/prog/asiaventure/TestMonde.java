package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestMonde {
  private Monde monde;

  @Before
  public void avantTest() {
    monde = new Monde("MondeDeTest");
  }

  @Test
  public void testAjouter() throws Exception{
    Entite pdb = new PiedDeBiche("pdbTest",monde); // ajouter appelé dans le constructeur
    assertThat(monde.getEntite("pdbTest"),IsSame.sameInstance(pdb));
  }

  @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
  public void testAjouterNomDEntiteDejaUtiliseDansLeMonde() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    monde.ajouter(pdb);
  }

  @Test(expected = EntiteDejaDansUnAutreMondeException.class)
  public void testAjouterEntiteDejaDansUnAutreMondeException() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    Monde monde2 = new Monde("monde2");
    monde2.ajouter(pdb);
  }

  @Test
  public void testGetEntite() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(monde.getEntite("pdbTest"),IsSame.sameInstance((Entite) pdb));
  }

  @Test
  public void testGetNom(){
    assertThat(monde.getNom(),IsEqual.equalTo("MondeDeTest"));
  }
}
