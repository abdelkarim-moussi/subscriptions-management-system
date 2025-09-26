package main.java.presentation;

import main.java.service.PaymentService;
import main.java.service.SubscriptionService;

import java.util.Scanner;

public class Menu {

    static SubscriptionService subscriptionService = new SubscriptionService();
    static PaymentService paymentService = new PaymentService();
    static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        int choice = 0;
        do {
            System.out.println("1-créer abonnement");
            System.out.println("2-modifier abonnement");
            System.out.println("3-supprimer abonnement");
            System.out.println("4-modifier abonnement");
            System.out.println("5-modifier paiement");
            System.out.println("6-supprimer payment");

            choice = scanner.nextInt();

            switch(choice){
                case 1 :
                    createSubscription();
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
                case 5 :
                    break;
                case 6 :
                    break;
            }

        }while(true);
    }


    public static void createSubscription(){
        System.out.println("choisir le type d'abonnement");

        do {
            System.out.println("1-abonnement avec engagement");
            System.out.println("2-abonnement sans engagement");
            int choice = scanner.nextInt();
            String seviceName = "";
            float montantMensuel;
            int periodeEngagement = 0;

            scanner.nextLine();
            switch (choice){
                case 1 :
                    System.out.println("nom de service :");
                    seviceName = scanner.nextLine();
                    System.out.println("montant mensuel :");
                    montantMensuel = scanner.nextFloat();
                    System.out.println("periode d'engagement en mois :");
                    periodeEngagement = scanner.nextInt();

                    subscriptionService.createSubscription(seviceName,montantMensuel,periodeEngagement);
                    break;
                case 2 :
                    System.out.println("nom de service :");
                    seviceName = scanner.nextLine();
                    System.out.println("montant mensuel :");
                    montantMensuel = scanner.nextFloat();

                    subscriptionService.createSubscription(seviceName,montantMensuel,periodeEngagement);
                    break;
            }
        }while(true);
    }
}
