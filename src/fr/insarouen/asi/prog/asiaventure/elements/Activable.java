package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

/**
 * Interface contenant 3 méthodes : activableAvec(), activer(), et activerAvec().
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public interface Activable {
  boolean activableAvec(Objet obj);

  void activer() throws ActivationException;

  void activerAvec(Objet obj) throws ActivationException;
}
