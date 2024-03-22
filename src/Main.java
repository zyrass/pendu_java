// Importation des packages nécessaires
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

    /**
     * Déclaration des constantes pour les actions du menu
     */
    private static final int JOUER = 1;
    private static final int AFFICHER_MENU_CATEGORIES = 2;
    private static final int QUITTER = 0;

    /**
     * Initialisation de la liste des mots avec une structure de Map
     */
    private static final Map<String, List<String>> wordLists = new HashMap<>();

    /**
     * Chemin vers le dossier contenant les listes de mots
     */
    private static final Path WORDS_FOLDER = Paths.get("wordlists");

    /**
     * Scanner pour lire les entrées de l'utilisateur
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Méthode pour initialiser les listes de mots (création de dossiers)
     */
    public static void initializeWordLists() {
        // Vérifier si le répertoire existe déjà, sinon le créer
        if (!Files.exists(WORDS_FOLDER)) {
            try {
                Files.createDirectory(WORDS_FOLDER);
                System.out.println("Répertoire 'wordlists' créé avec succès !");
            } catch (IOException e) {
                System.err.println("Erreur lors de la création du répertoire 'wordlists' : " + e.getMessage());
            }
        }
    }

    // Méthode pour générer une liste de fruits par défaut
    private static void generateFruitsList() throws IOException {
        List<String> fruits = new ArrayList<>();
        fruits.add("Pommes"); fruits.add("Bananes"); fruits.add("Poires"); fruits.add("Fraises"); fruits.add("Groseilles");
        fruits.add("Framboises"); fruits.add("Kiwis"); fruits.add("Oranges"); fruits.add("Pastèques"); fruits.add("Clémentines");
        fruits.add("Melons"); fruits.add("Figues"); fruits.add("Avocats"); fruits.add("Noix"); fruits.add("Mures");
        fruits.add("Dattes"); fruits.add("Mandarines"); fruits.add("Cerises"); fruits.add("Ananas"); fruits.add("Prunes");
        fruits.add("Peches"); fruits.add("Raisins"); fruits.add("Abricots"); fruits.add("Citrons"); fruits.add("Cassis");
        fruits.add("Litchis"); fruits.add("Grenades"); fruits.add("Nectarines"); fruits.add("Pamplemousses"); fruits.add("Mirabelles");
        fruits.add("Mangues"); fruits.add("Fruit de la passion"); fruits.add("Myrtilles"); fruits.add("Goyaves"); fruits.add("Papayes");
        fruits.add("Physalis"); fruits.add("Rhubarbe"); fruits.add("Caramboles"); fruits.add("Fruit de l'arbre à pain");
        fruits.add("Kakis"); fruits.add("Pitayas"); fruits.add("Pommes cannelle"); fruits.add("Durians"); fruits.add("Grenadilles");
        fruits.add("Kumquats"); fruits.add("Chérimoles"); fruits.add("Fruit de l'arbre à saucisses"); fruits.add("Fruits du jacquier");

        Path fruitsFile = WORDS_FOLDER.resolve("fruits.txt");
        Files.write(fruitsFile, fruits);

        wordLists.put("fruits", fruits);
    }

    private static void generateAnimalsList() throws IOException {
        List<String> animals = new ArrayList<>();
        animals.add("Chien"); animals.add("Chat"); animals.add("Cheval"); animals.add("Poisson"); animals.add("Souris");
        animals.add("Lapin"); animals.add("Oiseau"); animals.add("Cochon"); animals.add("Vache"); animals.add("Mouton");
        animals.add("Serpent"); animals.add("Tigre"); animals.add("Lion"); animals.add("Éléphant"); animals.add("Girafe");
        animals.add("Singe"); animals.add("Kangourou"); animals.add("Gorille"); animals.add("Hippopotame"); animals.add("Koala");
        animals.add("Crocodile"); animals.add("Loup"); animals.add("Renard"); animals.add("Zèbre"); animals.add("Léopard");
        animals.add("Panthère"); animals.add("Ours"); animals.add("Dromadaire"); animals.add("Chameau"); animals.add("Autruche");
        animals.add("Poule"); animals.add("Canard"); animals.add("Oie"); animals.add("Faucon"); animals.add("Aigle");
        animals.add("Pigeon"); animals.add("Perruche"); animals.add("Perroquet");
        animals.add("Poussin"); animals.add("Poule"); animals.add("Coq"); animals.add("Paon"); animals.add("Cygne");
        animals.add("Dinde"); animals.add("Ours"); animals.add("Écureuil"); animals.add("Hérisson"); animals.add("Taureau");
        animals.add("Pangolin"); animals.add("Marmotte"); animals.add("Chinchilla"); animals.add("Furet"); animals.add("Fennec");
        animals.add("Gerboise"); animals.add("Guépard"); animals.add("Guenon"); animals.add("Guanaco"); animals.add("Gourami");
        animals.add("Grizzli"); animals.add("Gavial"); animals.add("Lama"); animals.add("Lézard"); animals.add("Lynx");
        animals.add("Langur"); animals.add("Lion de mer"); animals.add("Lézard"); animals.add("Marsouin"); animals.add("Marmotte");
        animals.add("Morse"); animals.add("Musaraigne"); animals.add("Macareux"); animals.add("Narval"); animals.add("Nyala");

        Path animalsFile = WORDS_FOLDER.resolve("animaux.txt");
        Files.write(animalsFile, animals);

        wordLists.put("animaux", animals);
    }

    private static void generateDefaultLists() throws IOException {
        generateFruitsList();
        generateAnimalsList();
    }

    // Méthode pour charger les listes de mots à partir des fichiers
    private static void loadWordLists() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(WORDS_FOLDER)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    List<String> words = Files.readAllLines(file);
                    String categoryName = file.getFileName().toString().replace(".txt", "");
                    wordLists.put(categoryName, words);
                }
            }
        }

        if (wordLists.isEmpty()) {
            generateDefaultLists();
        }
    }

    // Méthode principale qui exécute le programme
    public static void main(String[] args) {
        try {
            initializeWordLists();
            loadWordLists();

            int selectedMenu;

            do {
                selectedMenu = menu();
                switch (selectedMenu) {
                    case JOUER:
                        jouer();
                        break;
                    case AFFICHER_MENU_CATEGORIES:
                        afficherMenuCategories();
                        break;
                    case QUITTER:
                        System.out.println("Merci d'avoir joué, à bientôt.");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                        break;
                }
            } while (selectedMenu != QUITTER);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/écriture des fichiers.");
            e.printStackTrace();
        }
    }

    // Affichage du menu principal et récupération du choix de l'utilisateur
    private static int menu() {
        System.out.println("\n==========================================================================");
        System.out.println("\t\uD83C\uDFB2 - JEU DU PENDU");
        System.out.println("==========================================================================");
        System.out.println("\t\t[1] ➡️ JOUER AU JEU");
        System.out.println("\t\t[2] ➡️ AFFICHER LE MENU DES CATEGORIES");
        System.out.println("==========================================================================");
        System.out.println("\t\t[0] ➡️ QUITTER LE JEU");
        System.out.println("==========================================================================");
        System.out.println("\tVeuillez saisir un nombre correspondant à votre choix :");

        while (!scanner.hasNextInt()) { // Vérifie si l'entrée suivante est un entier
            scanner.next(); // Élimine l'entrée non désirée
            System.out.println("Entrée invalide. Veuillez saisir un nombre correspondant à votre choix :");
        }
        return scanner.nextInt();
    }

    // Logique du jeu du pendu
    private static void jouer() {

        System.out.println("\nREGLES");
        System.out.println("Vous disposez d'un nombre de vie correspondant à la longueur du mot à trouver.");
        System.out.println("Vous devrez saisir une lettre en espérant trouver le mot.");
        System.out.println("Si vous trouvez une lettre, alors celle-ci sera visible sur le mot à trouver");
        System.out.println("Si vous saisissez une lettre déjà cité, alors vous ne serez pas emputé d'une vie.");
        System.out.println("En revanche, si vous n'avez plus de vie, faute de ne pas avoir trouvé le mot, alors vous aurez perdu et le mot que vous deviez trouver vous sera révélé.\n");

        System.out.println("\n\uD83D\uDCDA - Catégories disponibles :");
        for (String category : wordLists.keySet()) {
            System.out.println("\t- " + category);
        }

        // Sélection de la catégorie et récupération du mot à deviner
        String category = choisirCategorie();
        List<String> mots = wordLists.get(category);
        Random random = new Random();
        String motADeviner = mots.get(random.nextInt(mots.size()));

        // Nombre de vies initiales du joueur
        int PLAYER_LIFE = motADeviner.length();

        // Préparation de l'affichage du mot avec des underscores
        StringBuilder motCache = new StringBuilder("_".repeat(motADeviner.length()));
        Set<Character> lettresDevinees = new HashSet<>();

        while (PLAYER_LIFE > 0 && motCache.toString().contains("_")) {
            System.out.println("\nMot à deviner : " + motCache + " (" + motCache.length() + " caractères)");

            // Affichage des vies restantes avec des émojis cœur
            System.out.print("Vies restantes : ");
            for (int i = 0; i < PLAYER_LIFE; i++) {
                System.out.print("❤️ ");
            }
            System.out.println("\nEntrez une lettre :");
            String lettre = scanner.next().toLowerCase();
            if (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
                System.out.println("Veuillez entrer une lettre valide.");
                continue;
            }

            char charLettre = lettre.charAt(0);
            if (mots.contains(lettre) || lettresDevinees.contains(charLettre)) {
                System.out.println("⛔ - Lettre déjà devinée ou incorrecte.");
            } else if (motADeviner.toLowerCase().contains(lettre)) {
                for (int i = 0; i < motADeviner.length(); i++) {
                    if (motADeviner.toLowerCase().charAt(i) == charLettre) {
                        motCache.setCharAt(i, motADeviner.charAt(i));
                    }
                }
                lettresDevinees.add(charLettre);
                System.out.println("✔\uFE0F - Bonne lettre !");
            } else {
                PLAYER_LIFE--;
                System.out.println("❌ - Mauvaise lettre.");
                lettresDevinees.add(charLettre);
            }
        }

        if (PLAYER_LIFE > 0) {
            System.out.println("\n\uD83C\uDF89 - Félicitations ! Vous avez trouvé le mot : " + motADeviner);
        } else {
            System.out.println("\n\uD83D\uDC80 - Dommage ! Vous avez perdu. Le mot était : " + motADeviner);
        }
    }

    // Choix d'une catégorie de mots pour jouer
    private static String choisirCategorie() {
        System.out.println("\n\uD83C\uDFF7\uFE0F - Choisissez une catégorie de mots :");

        System.out.println("✏\uFE0F - Entrez le nom de la catégorie avec laquelle vous souhaitez jouer :");
        return scanner.next();
    }

    // Méthode pour afficher le menu des catégories de mots
    private static void afficherMenuCategories() throws IOException {
        System.out.println("\n==========================================================================");
        System.out.println("\t\t\t\uD83C\uDF7D - MENU CATÉGORIE");
        System.out.println("==========================================================================");
        System.out.println("\t\t[1] ➡️ Afficher les catégories");
        System.out.println("\t\t[2] ➡️ Ajouter un fichier comprenant une série de mots pour une catégorie spécifique");
        System.out.println("\t\t[3] ➡️ Modifier un fichier comprenant une série de mots pour une catégorie spécifique");
        System.out.println("\t\t[4] ➡️ Supprimer un fichier comprenant une série de mots pour une catégorie spécifique");
        System.out.println("\t\t[0] ➡️ Retour au menu principal");
        System.out.println("==========================================================================");
        System.out.println("\tVeuillez saisir un nombre correspondant à votre choix :");

        while (!scanner.hasNextInt()) { // Vérifie si l'entrée suivante est un entier
            scanner.next(); // Élimine l'entrée non désirée
            System.out.println("Entrée invalide. Veuillez saisir un nombre correspondant à votre choix :");
        }
        int choix = scanner.nextInt();
        switch (choix) {
            case 1:
                afficherListeMotsCategorie();
                break;
            case 2:
                ajouterNouveauFichierMots();
                break;
            case 3:
                ajouterMotsListeExistante();
                break;
            case 4:
                supprimerListeExistante();
                break;
            case 0:
                // Retour au menu principal
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
                break;
        }
    }

    // Méthode pour ajouter un nouveau fichier de mots à une catégorie
    private static void ajouterNouveauFichierMots() throws IOException {
        System.out.println("\n\uD83D\uDCDA - Catégories de mots disponibles :");
        for (String currentCategorie : wordLists.keySet()) {
            System.out.println("\t- " + currentCategorie);
        }

        System.out.println("Veuillez saisir une catégorie existante");
        String categorie = scanner.nextLine();

        System.out.println("\n\uD83D\uDCDD - Entrez le nom du nouveau fichier (sans l'extension .txt) :");
        String nomFichier = scanner.next();

        // Chemin du nouveau fichier
        Path newFilePath = WORDS_FOLDER.resolve(categorie).resolve(nomFichier + ".txt");

        // Création du fichier
        if (!Files.exists(newFilePath)) {
            Files.createFile(newFilePath);
            System.out.println("Nouveau fichier créé avec succès : " + nomFichier + ".txt");

            // Ajout des mots
            List<String> mots = new ArrayList<>();
            System.out.println("\n\uD83D\uDCDD - Ajout de mots au fichier (tapez 'fin' pour terminer) :");
            String mot;
            do {
                System.out.print("Mot : ");
                mot = scanner.next();
                if (!mot.equalsIgnoreCase("fin")) {
                    mots.add(mot);
                }
            } while (!mot.equalsIgnoreCase("fin"));

            // Écriture des mots dans le fichier
            Files.write(newFilePath, mots);
            System.out.println("Mots ajoutés au fichier avec succès.");
            System.out.println("Fermeture du projet, relancé le afin de charger celui-ci");
            System.exit(0);
        } else {
            System.out.println("Le fichier existe déjà.");
        }
    }

    // Méthode pour afficher la liste des mots d'une catégorie spécifique
    private static void afficherListeMotsCategorie() throws IOException {
        System.out.println("\n\uD83D\uDCDA - Catégories de mots disponibles :");
        for (String category : wordLists.keySet()) {
            System.out.println("\t- " + category);
        }

        System.out.println("Veuillez saisir le nom de la catégorie pour afficher sa liste de mots :");
        String selectedCategory = scanner.next();

        List<String> mots = wordLists.get(selectedCategory);
        if (mots != null) {
            System.out.println("\n\uD83D\uDCD7 - Liste des mots de la catégorie '" + selectedCategory + "' :");
            for (String mot : mots) {
                System.out.println("\t- " + mot);
            }
        } else {
            System.out.println("La catégorie spécifiée n'existe pas.");
        }
    }

    private static void supprimerListeExistante() throws IOException {
        System.out.println("\n\uD83D\uDCDA - Catégories de mots disponibles :");
        for (String category : wordLists.keySet()) {
            System.out.println("\t- " + category);
        }

        System.out.println("\n\uD83D\uDCDD - Entrez le nom de la catégorie contenant la liste à supprimer :");
        String categorie = scanner.next();

        // Vérifier si la catégorie existe
        if (wordLists.containsKey(categorie)) {
            // Supprimer la liste de mots
            wordLists.remove(categorie);
            System.out.println("La liste dans la catégorie \"" + categorie + "\" a été supprimée avec succès.");
        } else {
            System.out.println("La catégorie spécifiée n'existe pas.");
        }
    }

    // Méthode pour ajouter de nouveaux mots à une liste existante
    private static void ajouterMotsListeExistante() throws IOException {
        System.out.println("\n\uD83D\uDCDA - Catégories de mots disponibles :");
        for (String category : wordLists.keySet()) {
            System.out.println("\t- " + category);
        }

        System.out.println("\uD83D\uDCDD - Entrez le nom de la catégorie à laquelle ajouter des mots :");
        String categorie = scanner.next();

        // Vérifier si la catégorie existe
        if (wordLists.containsKey(categorie)) {

            // Afficher les mots actuels dans la liste
            List<String> motsActuels = wordLists.get(categorie);
            System.out.println("\n\uD83D\uDCDA - Liste actuelle dans la catégorie \"" + categorie + "\" :");
            for (String motCourant : motsActuels) {
                System.out.println("\t- " + motCourant);
            }

            // Demander les nouveaux mots à ajouter à l'utilisateur
            System.out.println("\n\uD83D\uDCDD - Entrez les nouveaux mots à ajouter (tapez 'fin' pour terminer) :");
            String mot;
            do {
                System.out.print("Mot : ");
                mot = scanner.next();
                if (!mot.equalsIgnoreCase("fin")) {
                    motsActuels.add(mot);
                }
            } while (!mot.equalsIgnoreCase("fin"));

            // Mettre à jour la liste de mots
            wordLists.put(categorie, motsActuels);
            System.out.println("Nouveaux mots ajoutés avec succès.");
        } else {
            System.out.println("La catégorie spécifiée n'existe pas.");
        }
    }
}
