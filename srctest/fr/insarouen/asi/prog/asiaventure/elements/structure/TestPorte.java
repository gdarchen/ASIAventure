package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
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

public class TestPorte {
  private Porte porte;
  private Monde monde;
  private Piece pieceA;
  private Piece pieceB;
  private Serrure serrure;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    pieceA = new Piece("pieceA_test",monde);
    pieceB = new Piece("pieceB_test",monde);
    serrure = new Serrure("serrureTest",monde);
    porte = new Porte("porteTest",monde,serrure,pieceA,pieceB);
  }

  @Test
  public void testConstructeur() {
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.VERROUILLE));
    assertThat(this.pieceA.aLaPorte(porte),Is.is(true));
    assertThat(this.pieceB.aLaPorte(porte),Is.is(true));
  }

  @Test
  public void testActiver() throws Exception{
    Porte porte2 = new Porte("porteTest2",monde,pieceA,pieceB);
    assertThat(porte2.getEtat(),IsEqual.equalTo(Etat.FERME));
    porte2.activer();
    assertThat(porte2.getEtat(),IsEqual.equalTo(Etat.OUVERT));
    porte2.activer();
    assertThat(porte2.getEtat(),IsEqual.equalTo(Etat.FERME));
  }

  @Test(expected = ActivationImpossibleException.class)
  public void testActiverActivationImpossible() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    porte.activerAvec(pdb);
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.CASSE));
    porte.activer();
  }

  @Test
  public void testActiverAvecPiedDeBiche() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    porte.activerAvec(pdb);
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.CASSE));
    assertThat(this.serrure.getEtat(),IsEqual.equalTo(Etat.CASSE));
  }

  @Test
  public void testActiverAvec() throws Exception{
    Clef laCleDeLaSerrure = this.serrure.creerClef();
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.VERROUILLE));
    porte.activerAvec(laCleDeLaSerrure);
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.OUVERT));
  }

  @Test(expected = ActivationImpossibleAvecObjetException.class)
  public void testActiverAvecMauvaiseCle() throws Exception{
    Serrure serrure2 = new Serrure("serrureTest2",monde);
    Porte porte2 = new Porte("porteTest2",monde,serrure2,pieceA,pieceB);
    Clef laCle = porte2.getSerrure().creerClef();
    this.porte.activerAvec(laCle);
  }

  @Test
  public void testActivableAvec() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(this.porte.activableAvec(pdb),Is.is(true));
    porte.activerAvec(pdb);
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.CASSE));
    assertThat(this.porte.activableAvec(pdb),Is.is(false));
  }

  @Test
  public void testActivableAvecCle()throws Exception{
    Clef laCleDeLaSerrure = this.serrure.creerClef();
    assertThat(this.porte.activableAvec(laCleDeLaSerrure),Is.is(true));
  }

  @Test
  public void testGetEtat(){
    assertThat(this.porte.getEtat(),IsEqual.equalTo(Etat.VERROUILLE));
  }

  @Test
  public void testGetPieceAutreCote(){
    assertThat(this.porte.getPieceAutreCote(pieceA),IsSame.sameInstance(pieceB));
    assertThat(this.porte.getPieceAutreCote(pieceB),IsSame.sameInstance(pieceA));
  }

  @Test
  public void testGetSerrure(){
    assertThat(this.porte.getSerrure(),IsSame.sameInstance(serrure));
  }
}
