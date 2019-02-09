package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.objets.SuiteObjets;
import fr.insarouen.asi.prog.asiaventure.elements.structure.SuiteStructure;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.SuiteVivants;
import fr.insarouen.asi.prog.asiaventure.elements.SuiteElements;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.SuiteSerrurerie;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  SuiteObjets.class,
  SuiteStructure.class,
  SuiteVivants.class,
  SuiteElements.class,
  SuiteSerrurerie.class,
  SuiteASIAventure.class
})

public class AllTests {

}
