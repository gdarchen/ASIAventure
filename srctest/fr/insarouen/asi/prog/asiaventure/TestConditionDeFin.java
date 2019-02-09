package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestConditionDeFin {
  private Monde monde;
  private Vivant vivant;
  private Piece piece;
  private EtatDuJeu etat;

  @Before
  public void avantTest() throws Exception {
    monde = new Monde("MondeDeTest");
    piece = new Piece("pieceTest",monde);
    vivant = new Vivant("vivantTest",monde,10,15,piece){};
    etat = EtatDuJeu.SUCCES;
  }

  @Test
  public void testConditionDeFinVivantDansPiece() throws Exception{
    ConditionDeFinVivantDansPiece condFinVivantDansPiece = new ConditionDeFinVivantDansPiece(etat,vivant,piece);
    assertThat(condFinVivantDansPiece.verifierCondition(),IsEqual.equalTo(etat));
    Vivant vivant2 = new Vivant("vivantTest2",monde,12,17,new Piece("Piece2",monde)){};
    ConditionDeFinVivantDansPiece condFinVivantDansPiece2 = new ConditionDeFinVivantDansPiece(etat,vivant2,piece);
    assertThat(condFinVivantDansPiece2.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));
  }

  @Test
  public void testConditionDeFinVivantDansPieceEtPossedeObjets() throws Exception{
    Objet[] objets = {new PiedDeBiche("pdbTest",monde), new Coffre("coffreTest",monde)};
    Vivant vivantAvecObjets = new Vivant("vivantAvecObjetsTest",monde,10,15,piece,objets){};
    ConditionDeFinVivantDansPieceEtPossedeObjets cdfPieceObjets = new ConditionDeFinVivantDansPieceEtPossedeObjets(etat,vivantAvecObjets,piece,objets);
    assertThat(cdfPieceObjets.verifierCondition(),IsEqual.equalTo(etat));

    Vivant vivantAbsentPiece = new Vivant("vivantAbsentPieceTest",monde,12,17,new Piece("Piece2",monde),objets){};
    ConditionDeFinVivantDansPieceEtPossedeObjets cdfAbsentPieceObjets = new ConditionDeFinVivantDansPieceEtPossedeObjets(etat,vivantAbsentPiece,piece,objets);
    assertThat(cdfAbsentPieceObjets.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));

    Vivant vivantSansObjets = new Vivant("vivantSansObjets",monde,12,17,piece){};
    ConditionDeFinVivantDansPieceEtPossedeObjets cdfPieceSansObjets = new ConditionDeFinVivantDansPieceEtPossedeObjets(etat,vivantSansObjets,piece,objets);
    assertThat(cdfPieceSansObjets.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));
  }

  @Test
  public void testConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction() throws Exception{
    Objet[] objets = {new PiedDeBiche("pdbTest",monde), new Coffre("coffreTest",monde)};
    Vivant vivantAvecObjets = new Vivant("vivantAvecObjetsTest",monde,10,15,piece,objets){};
    ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction cdfPieceObjetsConj = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(etat,vivantAvecObjets,piece,objets);
    assertThat(cdfPieceObjetsConj.verifierCondition(),IsEqual.equalTo(etat));

    Vivant vivantAbsentPiece = new Vivant("vivantAbsentPieceTest",monde,12,17,new Piece("Piece2",monde),objets){};
    ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction cdfAbsentPieceObjetsConj = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(etat,vivantAbsentPiece,piece,objets);
    assertThat(cdfAbsentPieceObjetsConj.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));

    Vivant vivantSansObjets = new Vivant("vivantSansObjets",monde,12,17,piece){};
    ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction cdfPieceSansObjetsConj = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(etat,vivantSansObjets,piece,objets);
    assertThat(cdfPieceSansObjetsConj.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));
  }

  @Test
  public void testConditionDeFinVivantMort(){
    ConditionDeFinVivantMort cdf = new ConditionDeFinVivantMort(etat,vivant);
    assertThat(cdf.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));

    vivant.setPointsDeVie(0);
    ConditionDeFinVivantMort cdfMort = new ConditionDeFinVivantMort(etat,vivant);
    assertThat(cdfMort.verifierCondition(),IsEqual.equalTo(etat));
  }

  @Test
  public void testConditionDeFinVivantPossedeObjets() throws Exception{
    Objet[] objets = {new PiedDeBiche("pdbTest",monde), new Coffre("coffreTest",monde)};
    Vivant vivantAvecObjets = new Vivant("vivantAvecObjetsTest",monde,10,15,piece,objets){};
    ConditionDeFinVivantPossedeObjets cdf = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.ECHEC,vivantAvecObjets,objets);
    assertThat(cdf.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ECHEC));

    Vivant vivantSansObjets = new Vivant("vivantSansObjets",monde,12,17,piece){};
    ConditionDeFinVivantPossedeObjets cdfSansObjet = new ConditionDeFinVivantPossedeObjets(etat,vivantSansObjets,objets);
    assertThat(cdfSansObjet.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));
  }

  @Test
  public void testConditionDeFinConjonctionConditionDeFin() throws Exception{
    ConditionDeFinVivantDansPiece condFinVivantDansPiece = new ConditionDeFinVivantDansPiece(etat,vivant,piece);
    vivant.setPointsDeVie(0);
    ConditionDeFinVivantMort cdfMort = new ConditionDeFinVivantMort(etat,vivant);
    ConditionDeFinConjonctionConditionDeFin cdfConjConj = new ConditionDeFinConjonctionConditionDeFin(etat,condFinVivantDansPiece,cdfMort);
    assertThat(cdfConjConj.verifierCondition(),IsEqual.equalTo(etat));

    vivant.setPointsDeVie(14);
    ConditionDeFinVivantMort cdfNonMort = new ConditionDeFinVivantMort(etat,vivant);
    ConditionDeFinConjonctionConditionDeFin cdfConjConjEnCours = new ConditionDeFinConjonctionConditionDeFin(etat,condFinVivantDansPiece,cdfNonMort);
    assertThat(cdfConjConj.verifierCondition(),IsEqual.equalTo(EtatDuJeu.ENCOURS));
  }
}
