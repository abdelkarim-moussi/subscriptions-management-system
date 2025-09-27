package main.java.presentation;

import main.java.dao.PaymentDAO;
import main.java.entity.Payment;
import main.java.entity.Subscription;
import main.java.enums.PaymentStatus;
import main.java.enums.PaymentType;
import main.java.service.PaymentService;
import main.java.service.SubscriptionService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    static SubscriptionService subscriptionService = new SubscriptionService();
    static PaymentService paymentService = new PaymentService();
    static Scanner scanner = new Scanner(System.in);

    public static void menu() throws SQLException {
        try {

            int choice;

            do {
                System.out.println("1-GESTION ABONNEMENTS");
                System.out.println("2-GESTION PAIEMENTS");
                System.out.println("3-GENERER RAPPORT FINACIERE (mensuels, annuels, impayés).");
                System.out.println("0-QUITTER");
                System.out.print("Votre Choix : ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Entrer un choix valide (nembre) : ");
                    scanner.next();
                }
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        subscriptionsManagement();
                        break;
                    case 2:
                        paymentsManagement();
                        break;
                    case 3:
                        System.out.println("Rapports");
                        ;
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix Invalide!");
                }

            } while (true);
        } catch (InputMismatchException e) {
            e.getMessage();
        }

    }

    private static void subscriptionsManagement() throws SQLException {
        do {

            System.out.println("--------GESTION ABONNEMENTS--------");
            System.out.println("1- Créer Abonnement");
            System.out.println("2- Modifier Abonnement");
            System.out.println("3- Supprimer Abonnement");
            System.out.println("4- Consulter List Abonnements");
            System.out.println("5- Afficher les Paiement d'un Abonnements");
            System.out.println("6- retour au menu principal");

            int manChoice;
            while (!scanner.hasNextInt()) {
                System.out.print("Choisir un choix valide : ");
                scanner.next();
            }
            manChoice = scanner.nextInt();

            scanner.nextLine();
            switch (manChoice) {
                case 1:
                    createSubscription();
                    break;
                case 2:
                    updateSubscription();
                    break;
                case 3:
                    deleteSubscription();
                    break;
                case 4:
                    displaySubscriptions();
                    break;
                case 5:
                    displaySubscriptionPayments();
                    break;
                case 6:
                    menu();
                default:
                    System.out.println("Choix Invalide!");

            }

        } while (true);
    }


    private static void createSubscription() throws SQLException {

        System.out.println("choisir le type d'abonnement :");

        do {
            System.out.println("1-abonnement avec engagement");
            System.out.println("2-abonnement sans engagement");
            System.out.println("3-retour au menu precedent");

            int subChoice;
            while (!scanner.hasNextInt()) {
                System.out.println("Choisir un type valide (nembre 1 ou 2)");
                scanner.next();
            }

            subChoice = scanner.nextInt();

            String seviceName = "";
            float montantMensuel;
            int periodeEngagement = 0;

            scanner.nextLine();
            switch (subChoice) {
                case 1:
                    System.out.println("nom de service :");
                    seviceName = scanner.nextLine();
                    System.out.println("montant mensuel :");
                    while (!scanner.hasNextFloat() || !scanner.hasNextInt()) {
                        System.out.print("le montant mensuel doit être un nembre entier ou decimal : ");
                        scanner.next();
                    }
                    montantMensuel = scanner.nextFloat();
                    System.out.println("periode d'engagement en mois :");
                    while (!scanner.hasNextInt()) {
                        System.out.print("la periode d'engagement doit être un nombre entier : ");
                        scanner.next();
                    }
                    periodeEngagement = scanner.nextInt();

                    subscriptionService.createSubscription(seviceName, montantMensuel, periodeEngagement);
                    break;
                case 2:
                    System.out.println("nom de service :");
                    seviceName = scanner.nextLine();
                    System.out.println("montant mensuel :");
                    while (!scanner.hasNextFloat() || !scanner.hasNextInt()) {
                        System.out.print("le montant mensuel doit être un nembre entier ou decimal : ");
                        scanner.next();
                    }

                    montantMensuel = scanner.nextFloat();

                    subscriptionService.createSubscription(seviceName, montantMensuel, periodeEngagement);
                    break;
                case 3:
                    subscriptionsManagement();
                default:
                    System.out.println("choix invalide !");
            }

        } while (true);
    }

    private static void updateSubscription() {

        System.out.println("enter le code d'abonnement a modifier :");
        String subCode = scanner.nextLine();
        System.out.println("new service name :");
        String newServiceName = scanner.nextLine();
        System.out.println("montant mensuel :");
        while (!scanner.hasNextFloat() || !scanner.hasNextInt()) {
            System.out.print("Montant Mensuel Doit être un nembre : ");
            scanner.next();
        }
        float newMonthlyAmount = scanner.nextFloat();

        System.out.println("periode d'engagement en mois :");
        while (!scanner.hasNextInt()) {
            System.out.print("periode d'engagement doit être un nembre entier : ");
            scanner.next();
        }
        int newPeriod = scanner.nextInt();

        subscriptionService.updateSubscription(subCode, newServiceName, newMonthlyAmount, newPeriod);

    }

    private static void deleteSubscription() {
        System.out.println("entrer le code d'abonnement a supprimer :");
        String subCode = scanner.nextLine();

        if (!subCode.trim().isEmpty()) {
            subscriptionService.deleteSubscription(subCode);
        } else System.out.println("code ne peut pas être vide!");
    }

    private static void displaySubscriptions() {

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        subscriptions.forEach(subscription -> System.out.println(subscription));

    }

    private static void displaySubscriptionPayments() throws SQLException {
        System.out.print("Entrer le code d'abonnement : ");
        String subId = scanner.nextLine();

        List<Payment> subPayments = paymentService.getSubscriptionPayments(subId);

        System.out.println(subPayments);

    }


    //Payments Management

    private static void paymentsManagement() throws SQLException {
        do {

            System.out.println("--------GESTION PAIEMENTS--------");
            System.out.println("1- Enregistrer Paiement");
            System.out.println("2- Modifier Paiement");
            System.out.println("3- Supprimer Paiement");
            System.out.println("4- Consulter les paiements manqués avec le montant totale impayé (pour les abonnement avec engagement)");
            System.out.println("5- afficher la somme payée d'un abonnement");
            System.out.println("6- afficher les 5 dernier paiement");
            System.out.println("7- retour au menu principal");

            int manChoice;
            while (!scanner.hasNextInt()) {
                System.out.print("Choisir un choix valide : ");
                scanner.next();
            }
            manChoice = scanner.nextInt();

            scanner.nextLine();
            switch (manChoice) {
                case 1:
                    savePayment();
                    break;
                case 2:
                    updatePayment();
                    break;
                case 3:
                    deletePayment();
                    break;
                case 4:
                    missedPayments();
                    break;
                case 5:
                    SubTotalPayedAmount();
                    break;
                case 6:
                    lastFivePayments();
                    break;
                case 7:
                    menu();
                default:
                    System.out.println("Choix Invalide!");

            }

        } while (true);
    }


    private static void savePayment() {
        System.out.println("Entrer le code paiement :");
        String payCode = scanner.nextLine();

        if (!payCode.trim().isEmpty()) {
            System.out.println("Choisir la Methode de Paiement \n1-Card\n2-Paypal\n3-bank-app");
            while (!scanner.hasNextInt()) {
                System.out.print("Entre un choix valide : ");
                scanner.next();
            }
            int entredType = scanner.nextInt();
            PaymentType paymentType = null;

            if (entredType == 1) {
                paymentType = PaymentType.pay_with_card;
            } else if (entredType == 2) {
                paymentType = PaymentType.paypal;
            } else if (entredType == 3) {
                paymentType = PaymentType.bank_app;
            }

            int res = paymentService.updatePayment(payCode, LocalDateTime.now(), paymentType, PaymentStatus.payed);

            if (res == 1) {
                System.out.println("Payment saved successfully");
            } else System.out.println("There is an error!");
        }
    }

    private static void updatePayment() {

        System.out.println("Entrer le code paiement :");
        String payCode = scanner.nextLine();

        if (!payCode.trim().isEmpty()) {

                System.out.println("Choisir la Methode de Paiement \n1-Card\n2-Paypal\n3-bank-app");
                int entredType;
                while (!scanner.hasNextInt()) {
                    System.out.print("Entre un choix valide : ");
                    scanner.next();
                }

                entredType = scanner.nextInt();

                PaymentType paymentType = null;

                scanner.nextLine();
                switch (entredType) {
                    case 1:
                        paymentType = PaymentType.pay_with_card;
                        break;
                    case 2:
                        paymentType = PaymentType.paypal;
                        break;
                    case 3:
                        paymentType = PaymentType.bank_app;
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
                int entredStatus;

                System.out.println("Choisir le status de Paiement \n1-not payed\n2-payed\n3-late");
                while (!scanner.hasNextInt()) {
                    System.out.print("Entre un choix valide : ");
                    scanner.next();
                }

                entredStatus = scanner.nextInt();

                PaymentStatus status = null;

                scanner.nextLine();
                switch (entredStatus) {
                    case 1:
                        status = PaymentStatus.not_payed;
                        break;
                    case 2:
                        status = PaymentStatus.payed;
                        break;
                    case 3:
                        status = PaymentStatus.late;
                        break;
                    default:
                        System.out.println("Choix invalide!");
                }

                int res = paymentService.updatePayment(payCode, LocalDateTime.now(), paymentType, status);

                if (res == 1) {
                    System.out.println("Payment updated successfully");
                } else System.out.println("There is an error!");

        }
    }

    private static void deletePayment(){

        System.out.println("Entrer le code de paiement a supprimer : ");
        String codePay = scanner.nextLine();
        if(!codePay.trim().isEmpty()){
           int res = paymentService.deletePayment(codePay);
           if(res == 1) {
                System.out.println("Paiment supprimer");
            }else System.out.println("Il ya un problem");
        }

    }

    private static void missedPayments(){
        System.out.println("Entrer le code d'abonnemnt");
        String code = scanner.nextLine();
        List<Payment> missedPayments = paymentService.getMissedPayments(code);
        if(missedPayments == null){
            System.out.println("aucun paiement manqué");
        }
        else System.out.println(missedPayments);
    }

    private static void SubTotalPayedAmount(){

        System.out.println("Entrer le code d'abonnemnt");
        String code = scanner.nextLine();

        float total = subscriptionService.getTotalPayed(code);
        System.out.println("Le Montant Total Payé : "+total);

    }

    private static void lastFivePayments(){
        List<Payment> lastPayements = paymentService.lastPayments();

        System.out.println(lastPayements);
    }
}
