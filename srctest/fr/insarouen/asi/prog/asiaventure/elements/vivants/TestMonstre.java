package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import java.util.*;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

/*import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
*/

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestMonstre {

  private Monstre monstre;
  private Monde monde;
  private Piece piece,piece2,piece3;
  private Porte porte,porteFermee;
  private Serrure serrure;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    piece = new Piece("pieceTest",monde);
    piece2 = new Piece("pieceTest2",monde);
    piece3 = new Piece("pieceTest3",monde);
    serrure = new Serrure("serrureTest",monde);
    monstre = new Monstre("monstreTest",monde,20,30,piece);
    porte = new Porte("BernardLaPorte",monde,serrure,piece,piece2); // boucle infinie si pas de porte FERMEE
    porteFermee = new Porte("BernardLaPorteFermee",monde,piece,piece3);
  }

  @Test
  public void testConstructeur(){
    assertThat(monstre.getPointVie(),IsEqual.equalTo(20));
    assertThat(monstre.getPointForce(),IsEqual.equalTo(30));
    assertThat(monstre.getPiece(),IsSame.sameInstance(piece));
    assertThat(monstre.getObjets().size(),IsEqual.equalTo(0));
    assertThat(piece.contientVivant(monstre),Is.is(true));
  }

  @Test
  public void testExecuter() throws Throwable {
    PiedDeBiche pdb1 = new PiedDeBiche("pdb1",monde);
    PiedDeBiche pdb2 = new PiedDeBiche("pdb2",monde);
    PiedDeBiche pdb3 = new PiedDeBiche("pdb3",monde);

    Map<String,Objet> map = new HashMap<>();
    Map<String,Objet> map2 = new HashMap<>();
    Map<String,Objet> map3 = new HashMap<>();

    map.put(pdb1.getNom(),pdb1);
    map2.put(pdb2.getNom(),pdb2);
    map3.put(pdb3.getNom(),pdb3);

    piece.deposer(pdb1);
    monstre.prendre("pdb1");
    piece.deposer(pdb2);
    piece3.deposer(pdb3);

    assertThat(piece.contientObjet(pdb1),Is.is(false));
    assertThat(piece.contientObjet(pdb2),Is.is(true));
    assertThat(piece3.contientObjet(pdb3),Is.is(true));
    assertThat(monstre.getPiece().getObjets(),IsEqual.equalTo(map2));
    assertThat(monstre.getObjets(),IsEqual.equalTo(map));

    monstre.executer();

    assertThat(monstre.getPointVie(),IsEqual.equalTo(19));
    assertThat(monstre.getPiece().getObjets(),IsEqual.equalTo(map));
    assertThat(monstre.getObjets(),IsEqual.equalTo(map3));
  }

  @Test(expected=Throwable.class)
  public void testExcuterMonstreMort() throws Throwable{
    Monstre monstre2 = new Monstre("monstreTestMort",monde,0,30,piece);
    monstre2.executer();
  }
}
