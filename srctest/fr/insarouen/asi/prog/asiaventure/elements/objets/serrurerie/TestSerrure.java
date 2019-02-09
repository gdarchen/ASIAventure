package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestSerrure {
  private Serrure serrure;
  private Monde monde;
  private Clef clef;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    serrure = new Serrure("serrureTest",monde);
    clef = serrure.creerClef();
  }

  @Test
  public void testConstructeur() throws Exception{
    assertThat(serrure.getEtat(),IsSame.sameInstance(Etat.VERROUILLE));
  }

  @Test
  public void testCreerNomAleatoire() throws Exception{
    Serrure s1 = new Serrure(monde);
    Serrure s2 = new Serrure(monde);
    assertThat(s1.getNom().equals(s2.getNom()),Is.is(false));
  }

  @Test
  public void testActivableAvecPiedDeBiche() throws Exception {
    PiedDeBiche pdbTest = new PiedDeBiche("pdbTest",monde);
    assertThat(serrure.activableAvec(pdbTest),Is.is(true));
  }

  @Test
  public void testActivableAvec() throws Exception {
    Serrure serrure2 = new Serrure("serrureTest2",monde);
    Clef clef2 = serrure2.creerClef();
    assertThat(serrure.activableAvec(clef),Is.is(true));
    assertThat(serrure.activableAvec(clef2),Is.is(false));
  }

  @Test(expected = ActivationException.class)
  public void testActiver() throws Exception {
    serrure.activer();
  }

  @Test
  public void testActiverAvecPiedDeBiche() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    serrure.activerAvec(pdb);
    assertThat(this.serrure.getEtat(),IsEqual.equalTo(Etat.CASSE));
  }

  @Test
  public void testActiverAvec() throws Exception{
    assertThat(this.serrure.getEtat(),IsEqual.equalTo(Etat.VERROUILLE));
    serrure.activerAvec(clef);
    assertThat(this.serrure.getEtat(),IsEqual.equalTo(Etat.DEVERROUILLE));
  }

  @Test(expected = ActivationImpossibleAvecObjetException.class)
  public void testActiverAvecMauvaiseCle() throws Exception{
    Serrure serrure2 = new Serrure("serrureTest2",monde);
    Clef laCle = serrure2.creerClef();
    this.serrure.activerAvec(laCle);
  }
}
