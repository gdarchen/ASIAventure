package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestVivant {
  private Vivant vivant;
  private Monde monde;
  private Piece piece;
  private PiedDeBiche pdb;

  @Before
  public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
    monde = new Monde("mondeTest");
    piece = new Piece("pieceTest",monde);
    vivant = new Vivant("vivantTest",monde,15,30,piece){};
    pdb = new PiedDeBiche("pdbTest",monde);
    piece.deposer(pdb);
  }

  @Test
  public void testConstructeur() {
    assertThat(vivant.getPointVie(),IsEqual.equalTo(15));
    assertThat(vivant.getPointForce(),IsEqual.equalTo(30));
    assertThat(vivant.getPiece(),IsSame.sameInstance(piece));
    assertThat(vivant.getObjets().size(),IsEqual.equalTo(0));
    assertThat(piece.contientVivant(vivant),Is.is(true));
  }

  @Test
  public void testPrendre() throws Exception{
    vivant.prendre(pdb);
    assertThat(vivant.possede(pdb),Is.is(true));
  }

  @Test
  public void testPrendreChaine() throws Exception{
    vivant.prendre("pdbTest");
    assertThat(vivant.possede(pdb),Is.is(true));
  }

  @Test(expected=ObjetAbsentDeLaPieceException.class)
  public void testPrendreObjetAbsentDeLaPiece() throws Exception{
    PiedDeBiche pdb2 = new PiedDeBiche("pdbAbsentPiece",monde);
    vivant.prendre(pdb2);
  }

  @Test(expected=ObjetNonDeplacableException.class)
  public void testPrendreObjetNonDeplacable() throws Exception{
    Objet objNonDep = new Objet("objNonDeplacable",monde){
      public boolean estDeplacable(){
        return false;
      }
    };
    piece.deposer(objNonDep);
    vivant.prendre(objNonDep);
  }

  @Test
  public void testEstMort() throws Exception{
    Vivant vivantTest = new Vivant("vivantTest2",monde,0,30,piece){};
    assertThat(vivantTest.estMort(),Is.is(true));
  }

  @Test
  public void testGetPiece(){
    assertThat(vivant.getPiece(),IsSame.sameInstance(piece));
  }

  @Test
  public void testGetPointForce(){
    assertThat(vivant.getPointForce(),IsEqual.equalTo(30));
  }

  @Test
  public void testGetPointVie(){
    assertThat(vivant.getPointVie(),IsEqual.equalTo(15));
  }

  @Test
  public void testGetObjet() throws Exception{
    vivant.prendre(pdb);
    assertThat(vivant.getObjet("pdbTest"),IsSame.sameInstance((Objet)pdb));
    vivant.deposer(pdb);
  }

  @Test
  public void testGetObjets() throws Exception {
    assertThat(vivant.getObjets().size(),IsEqual.equalTo(0));
    vivant.prendre("pdbTest");
    assertThat(vivant.getObjets().size(),IsEqual.equalTo(1));
    assertThat(vivant.getObjets().get("pdbTest"),IsSame.sameInstance((Objet) pdb));
  }

  @Test
  public void testDeposer() throws Exception {
    vivant.prendre("pdbTest");
    vivant.deposer(pdb);
    assertThat(vivant.possede(pdb),Is.is(false));
  }

  @Test
  public void testDeposerChaine() throws Exception {
    vivant.prendre("pdbTest");
    vivant.deposer("pdbTest");
    assertThat(vivant.possede(pdb),Is.is(false));
  }

  @Test(expected=ObjetNonPossedeParLeVivantException.class)
  public void testDeposerObjetNonPossedeParLeVivant() throws Exception{
    PiedDeBiche pdbNonPossede = new PiedDeBiche("pdbNonPossede",monde);
    vivant.deposer(pdbNonPossede);
  }

  @Test
  public void testPossede() throws Exception {
    vivant.prendre(pdb);
    assertThat(vivant.possede(pdb),Is.is(true));
    PiedDeBiche pdbNonPossede = new PiedDeBiche("pdbNonPossede",monde);
    assertThat(vivant.possede(pdbNonPossede),Is.is(false));
  }

}
