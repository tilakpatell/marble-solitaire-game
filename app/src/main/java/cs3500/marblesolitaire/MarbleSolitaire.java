package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

public class MarbleSolitaire {
    public static void main(String[] args) {
        MarbleSolitaireModel model;
        MarbleSolitaireView view;
        MarbleSolitaireController controller;
        
        if(args.length == 0) {
            return;
        }

        String gameType = args[0];
        switch (gameType) {
            case "english":
                if (args.length > 2 && args[1].equals("-size")) {
                    int size = Integer.parseInt(args[2]);
                    model = new EnglishSolitaireModel(size);
                } else if (args.length > 3 && args[1].equals("-hole")) {
                    int row = Integer.parseInt(args[2]) - 1;
                    int col = Integer.parseInt(args[3]) - 1;
                    model = new EnglishSolitaireModel(row, col);
                } else {
                    model = new EnglishSolitaireModel();
                }
                view = new MarbleSolitaireTextView(model);
                break;
            case "european":
                if (args.length > 2 && args[1].equals("-size")) {
                    int size = Integer.parseInt(args[2]);
                    model = new EuropeanSolitaireModel(size);
                } else if (args.length > 3 && args[1].equals("-hole")) {
                    int row = Integer.parseInt(args[2]) - 1;
                    int col = Integer.parseInt(args[3]) - 1;
                    model = new EuropeanSolitaireModel(row, col);
                } else {
                    model = new EuropeanSolitaireModel();
                }
                view = new EuropeanSolitaireTextView(model);
                break;
            case "triangular":
                if (args.length > 2 && args[1].equals("-size")) {
                    int size = Integer.parseInt(args[2]);
                    model = new TriangleSolitaireModel(size);
                } else if (args.length > 3 && args[1].equals("-hole")) {
                    int row = Integer.parseInt(args[2]) - 1;
                    int col = Integer.parseInt(args[3]) - 1;
                    model = new TriangleSolitaireModel(row, col);
                } else {
                    model = new TriangleSolitaireModel();
                }
                view = new TriangleSolitaireTextView(model);
                break;
            default:
                throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
    
        controller = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
        controller.playGame();
    }
}
