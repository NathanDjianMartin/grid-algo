

import java.util.*;

public class Algos {

    public static boolean egalEnsembliste(ArrayList<?> a1, ArrayList<?> a2){
        //retourn vrai ssi les a1 à les même éléments que a2 (peut importe l'ordre)
        return a1.containsAll(a2) && a2.containsAll(a1);
    }


    public static Solution greedySolver(Instance i) {

        //calcule la solution obtenue en allant chercher à chaque étape la pièce restante la plus proche
        //(si plusieurs pièces sont à la même distance, on fait un choix arbitraire)

        return i.calculerSol(i.greedyPermut());
    }


    public static Solution algoFPT1(InstanceDec id) {
        //algorithme qui décide id (c'est à dire si opt(id.i) >= id.c) en branchant (en 4^k) dans les 4 directions pour chacun des k pas
        //retourne une solution de valeur >= c si une telle solution existe, et null sinon
        //Ne doit pas modifier le paramètre
        //Rappel : si c==0, on peut retourner la solution égale au point de départ puisque l'on est pas obligé d'utiliser les k pas
        // (on peut aussi retourner une solution plus longue si on veut)

        // TODO: utiliser borne sup quand on l'aura codée
        //Remarque : quand vous aurez codé la borneSup, pensez à l'utiliser dans cet algorithme pour ajouter un cas de base

        InstanceDec copyInstanceDec = id.copy();
        Instance copyInstance = copyInstanceDec.i;
        Solution result = new Solution();

        if (copyInstance.piecePresente(copyInstance.getStartingP())) { // une pièce est présente,
            copyInstanceDec.c -= 1; // on la ramasse
        }

        if (copyInstanceDec.c <= 0) {
            return new Solution(copyInstance.getStartingP());
        }

        if (copyInstance.getK() <= 0) {
            return null;
        }


        if (copyInstance.canGoUp(copyInstance.getStartingP())) {
            // définit la coordonnée du voisin du haut
            Coord instanceStartingPoint = copyInstance.getStartingP();
            int line = instanceStartingPoint.getL() - 1;
            int col = instanceStartingPoint.getC();
            Coord voisinHaut = new Coord(line, col);

            copyInstance.setK(copyInstance.getK() - 1); // on avance de 1 pas

            copyInstance.setStartingP(voisinHaut); // avance vers le haut

            Solution upResult = algoFPT1(copyInstanceDec);
            if (upResult != null) {
                result.add(id.i.getStartingP()); // result.add(me)
                result.addAll(upResult);
                return result;
            }
        }

        if (copyInstance.canGoDown(copyInstance.getStartingP())) {
            // définit la coordonnée du voisin du bas
            Coord instanceStartingPoint = copyInstance.getStartingP();
            int line = instanceStartingPoint.getL() + 1;
            int col = instanceStartingPoint.getC();
            Coord voisinBas = new Coord(line, col);

            copyInstance.setK(copyInstance.getK() - 1); // on avance de 1 pas

            copyInstance.setStartingP(voisinBas); // avance vers le haut

            Solution downResult = algoFPT1(copyInstanceDec);
            if (downResult != null) {
                result.add(id.i.getStartingP()); // result.add(me)
                result.addAll(downResult);
                return result;
            }
        }

        if (copyInstance.canGoLeft(copyInstance.getStartingP())) {
            // définit la coordonnée du voisin du bas
            Coord instanceStartingPoint = copyInstance.getStartingP();
            int line = instanceStartingPoint.getL();
            int col = instanceStartingPoint.getC() - 1;
            Coord voisinGauche = new Coord(line, col);

            copyInstance.setK(copyInstance.getK() - 1); // on avance de 1 pas

            copyInstance.setStartingP(voisinGauche); // avance vers la gauche

            Solution leftResult = algoFPT1(copyInstanceDec);
            if (leftResult != null) {
                result.add(id.i.getStartingP()); // result.add(me)
                result.addAll(leftResult);
                return result;
            }
        }

        if (copyInstance.canGoRight(copyInstance.getStartingP())) {
            // définit la coordonnée du voisin du bas
            Coord instanceStartingPoint = copyInstance.getStartingP();
            int line = instanceStartingPoint.getL();
            int col = instanceStartingPoint.getC() + 1;
            Coord voisinDroite = new Coord(line, col);

            copyInstance.setK(copyInstance.getK() - 1); // on avance de 1 pas

            copyInstance.setStartingP(voisinDroite); // avance vers la gauche

            Solution rightResult = algoFPT1(copyInstanceDec);
            if (rightResult != null) {
                result.add(id.i.getStartingP()); // result.add(me)
                result.addAll(rightResult);
                return result;
            }
        }

        return null;
    }




    public static Solution algoFPT1DP(InstanceDec id,  HashMap<InstanceDec,Solution> table) {
        //même spécification que algoFPT1, si ce n'est que
        // - si table.containsKey(id), alors id a déjà été calculée, et on se contente de retourner table.get(id)
        // - sinon, alors on doit calculer la solution s pour id, la ranger dans la table (table.put(id,res)), et la retourner

        //Remarques
        // - ne doit pas modifier l'instance id en param (mais va modifier la table bien sûr)
        // - même si le branchement est le même que dans algoFPT1, ne faites PAS appel à algoFPT1 (et donc il y aura de la duplication de code)


        //à compléter
        return null;
    }


    public static Solution algoFPT1DPClient(InstanceDec id){
        //si il est possible de collecter >= id.c pièces dans id.i, alors retourne une Solution de valeur >= c, sinon retourne null
        //doit faire appel à algoFPT1DP

        //à completer
        return null;

    }



}
