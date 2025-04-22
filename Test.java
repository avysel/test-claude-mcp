import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe de démonstration avec différentes fonctionnalités Java
 */
public class Test {
    
    // Constantes globales
    private static final int MAX_ITERATIONS = 100;
    private static final String DATA_FOLDER = "./data";
    private static final double PI = 3.14159265359;
    
    // Variables statiques
    private static Random random = new Random();
    private static Map<String, Integer> statistics = new HashMap<>();
    
    /**
     * Point d'entrée principal du programme
     */
    public static void main(String[] args) {
        System.out.println("Programme de démonstration Java démarré");
        System.out.println("Date et heure actuelles: " + getCurrentDateTime());
        
        // Initialisation des données de test
        initializeStatistics();
        
        // Calcul de quelques valeurs mathématiques
        System.out.println("Calculs mathématiques:");
        for (int i = 1; i <= 5; i++) {
            double result = calculatePower(i, 2);
            System.out.println(i + "² = " + result);
        }
        
        // Manipulation de tableaux
        int[] numbers = generateRandomArray(10);
        System.out.println("Tableau généré: " + Arrays.toString(numbers));
        System.out.println("Somme du tableau: " + sumArray(numbers));
        System.out.println("Moyenne du tableau: " + calculateAverage(numbers));
        
        // Manipulation de chaînes
        String text = "Java est un langage de programmation orienté objet";
        System.out.println("Texte original: " + text);
        System.out.println("Texte en majuscules: " + text.toUpperCase());
        System.out.println("Nombre de mots: " + countWords(text));
        
        // Manipulation de listes
        List<String> fruits = new ArrayList<>(Arrays.asList("Pomme", "Banane", "Orange", "Fraise", "Kiwi"));
        System.out.println("Liste de fruits: " + fruits);
        
        // Utilisation de streams pour filtrer
        List<String> filteredFruits = fruits.stream()
                .filter(f -> f.length() > 5)
                .collect(Collectors.toList());
        System.out.println("Fruits avec plus de 5 lettres: " + filteredFruits);
        
        // Manipulation de Map
        processStatistics();
        
        // Simulation de traitement de fichiers
        simulateFileProcessing();
        
        // Programmation concurrente simple
        try {
            demonstrateConcurrency();
        } catch (Exception e) {
            System.out.println("Erreur dans le traitement concurrent: " + e.getMessage());
        }
        
        // Section corrigée - Plus de NullPointerException
        if (random.nextInt(100) > 50) {
            // Création d'un tableau vide au lieu de null
            String[] data = new String[0];
            System.out.println("Longueur des données: " + data.length);
        }
        
        // Recherche dans un graphe
        simulateGraphTraversal();
        
        // Logique métier complexe simulée
        processBusinessLogic();
        
        System.out.println("Programme terminé avec succès");
    }
    
    /**
     * Obtient la date et l'heure actuelles formatées
     */
    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
    /**
     * Initialise les statistiques de test
     */
    private static void initializeStatistics() {
        statistics.put("visites", 1024);
        statistics.put("téléchargements", 348);
        statistics.put("inscriptions", 86);
        statistics.put("achats", 42);
    }
    
    /**
     * Calcule une puissance
     */
    private static double calculatePower(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    /**
     * Génère un tableau d'entiers aléatoires
     */
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }
    
    /**
     * Calcule la somme des éléments d'un tableau
     */
    private static int sumArray(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
    
    /**
     * Calcule la moyenne d'un tableau
     */
    private static double calculateAverage(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        return (double) sumArray(array) / array.length;
    }
    
    /**
     * Compte le nombre de mots dans une chaîne
     */
    private static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.split("\\s+");
        return words.length;
    }
    
    /**
     * Traite les statistiques en simulant des calculs
     */
    private static void processStatistics() {
        System.out.println("Traitement des statistiques:");
        
        int total = 0;
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
            total += entry.getValue();
        }
        
        System.out.println("Total des statistiques: " + total);
        
        // Calcul du taux de conversion
        double conversionRate = 100.0 * statistics.get("achats") / statistics.get("visites");
        System.out.printf("Taux de conversion: %.2f%%\n", conversionRate);
    }
    
    /**
     * Simule le traitement de fichiers
     */
    private static void simulateFileProcessing() {
        System.out.println("Simulation de traitement de fichiers...");
        
        // Création d'un répertoire fictif si nécessaire
        try {
            Files.createDirectories(Paths.get(DATA_FOLDER));
            System.out.println("Répertoire de données prêt");
        } catch (IOException e) {
            System.out.println("Impossible de créer le répertoire: " + e.getMessage());
        }
        
        // Simulation de lecture de fichiers
        List<String> fileNames = Arrays.asList("data1.txt", "data2.txt", "config.json");
        for (String fileName : fileNames) {
            System.out.println("Traitement simulé du fichier: " + fileName);
            // Ici, nous simulons juste le traitement, pas de lecture réelle
            try {
                Thread.sleep(100); // Simulation de traitement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Démontre l'utilisation de la programmation concurrente
     */
    private static void demonstrateConcurrency() throws Exception {
        System.out.println("Démonstration de programmation concurrente...");
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> results = new ArrayList<>();
        
        // Soumettre quelques tâches
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            results.add(executor.submit(() -> {
                System.out.println("Tâche " + taskId + " en cours d'exécution sur le thread " + 
                                   Thread.currentThread().getName());
                Thread.sleep(200); // Simulation de travail
                return taskId * 10;
            }));
        }
        
        // Récupérer les résultats
        int sum = 0;
        for (Future<Integer> result : results) {
            sum += result.get();
        }
        
        System.out.println("Résultat total du traitement concurrent: " + sum);
        
        // Fermeture propre
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
    
    /**
     * Simule une recherche dans un graphe (algorithme simplifié)
     */
    private static void simulateGraphTraversal() {
        System.out.println("Simulation de parcours de graphe...");
        
        // Simulation d'un graphe sous forme de matrice d'adjacence
        int[][] graph = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0}
        };
        
        // Afficher le graphe
        System.out.println("Matrice d'adjacence du graphe:");
        for (int[] row : graph) {
            System.out.println(Arrays.toString(row));
        }
        
        // Simuler un parcours en profondeur à partir du nœud 0
        boolean[] visited = new boolean[graph.length];
        System.out.print("Parcours en profondeur depuis le nœud 0: ");
        depthFirstSearch(graph, 0, visited);
        System.out.println();
    }
    
    /**
     * Implémentation simple d'un parcours en profondeur
     */
    private static void depthFirstSearch(int[][] graph, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                depthFirstSearch(graph, i, visited);
            }
        }
    }
    
    /**
     * Simulation de logique métier complexe
     */
    private static void processBusinessLogic() {
        System.out.println("Traitement de la logique métier...");
        
        // Simulation d'une application bancaire simplifiée
        double initialBalance = 1000.0;
        double finalBalance = initialBalance;
        
        // Simuler quelques transactions
        double[] transactions = {-50.0, 200.0, -25.5, -80.0, 15.75};
        System.out.println("Transactions:");
        
        for (double transaction : transactions) {
            finalBalance += transaction;
            String type = transaction >= 0 ? "Crédit" : "Débit";
            System.out.printf("%s: %.2f € (Solde: %.2f €)\n", 
                             type, Math.abs(transaction), finalBalance);
            
            // Simulation de frais si le solde devient négatif
            if (finalBalance < 0) {
                double fee = 5.0;
                finalBalance -= fee;
                System.out.printf("Frais de découvert: %.2f € (Solde: %.2f €)\n", 
                                 fee, finalBalance);
            }
        }
        
        // Calculer les intérêts (simplifié)
        double interestRate = 0.025; // 2.5%
        double interest = finalBalance > 0 ? finalBalance * interestRate : 0;
        finalBalance += interest;
        
        System.out.printf("Intérêts: %.2f € (Solde final: %.2f €)\n", 
                         interest, finalBalance);
    }
}