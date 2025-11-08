import java.io.*;
import java.util.*;

// ------------------------ Person Classes ------------------------
abstract class Person implements Serializable{
    private static final long serialVersionUID=1L;
    private String name;
    public Person(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public abstract void work();
}
// Actor Class
class Actor extends Person{
    public Actor(String name){
        super(name);
    }

    @Override
    public void work() {
        System.out.print(getName()+" is acting perfectly on screen!");
    }
}

// Actress Class
class Actress extends Person{
    public Actress(String name){
        super(name);
    }

    @Override
    public void work() {
        System.out.print(getName()+" is acting beautifully on screen!");
    }
}

// Director Class
class Director extends Person{
    public Director(String name){
        super(name);
    }

    @Override
    public void work() {
        System.out.print(getName()+" is directing the film!");
    }
}

// Cinemotographer Class
class Cinematographer extends Person{
    public Cinematographer(String name){
        super(name);
    }

    @Override
    public void work() {
        System.out.print(getName()+" is capturing stunning visuals!");
    }
}

// Editor Class
class Editor extends Person{
    public Editor(String name){
        super(name);
    }

    @Override
    public void work() {
        System.out.print(getName()+" is the editing of the film!");
    }
}

// ------------------------ Movie Class ------------------------

class Movie implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private Director director;
    private Actor actor;
    private Actress actress;
    private Cinematographer cinematographer;
    private Editor editor;
    public Movie(String title,Director director, Actor actor, Actress actress,Cinematographer cinematographer,Editor editor){
        this.title=title;
        this.director=director;
        this.actress=actress;
        this.actor=actor;
        this.cinematographer=cinematographer;
        this.editor=editor;
    }
    public String getTitle(){ return title;}
    public Director getDirector(){ return director;}
    public void showMovie(){ // Show the movie details
        System.out.println("\n Movie: "+title);
        System.out.println("Directed by: "+director.getName());
        System.out.println("Actor: "+actor.getName());
        System.out.println("Actress: "+actress.getName());
        System.out.println("Cinematographer: "+cinematographer.getName());
        System.out.println("Editor: "+editor.getName());
    }
}

// ------------------------ Production Class ------------------------
class Production implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Movie> movies;

    public Production(String name){
        this.movies=new ArrayList<>();
        this.name=name;
    }
    public String getName(){return name;}
    public List<Movie> getMovies(){return movies;}
    public void addMovie(Movie movie){movies.add(movie);}

    public void showProduction(){
        System.out.println("\n===============================");
        System.out.println("Production: "+name);
        System.out.println("===============================");
        if(movies.isEmpty()){
            System.out.println("No movies yet under this production..");
        }else{
            for(Movie m:movies) m.showMovie();
        }
    }
}

// ------------------------ Main Class ------------------------

public class CineVerseUniversePro {
    private static final String FILE_NAME = "productions_data.ser";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Production> productions= ProductionsFromFile(); // Load productions from the file
        System.out.println("Welcome to CineVerse Universe");
        int choice;
        do{
            System.out.println("\n ---- MENU ----");
            System.out.println("1. Add Production");
            System.out.println("2. Add Movie to Production");
            System.out.println("3. Show All Productions and Movies");
            System.out.println("4. Search Movie by Name");
            System.out.println("5. Show Movies by Director");
            System.out.println("6. Show All Movies under a Production");
            System.out.println("7. Show Total Movie Count");
            System.out.println("8. Save and Exit");
            System.out.print("Enter your choice: ");
            choice=Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1->addProduction(sc,productions);
                case 2->addMovietoProduction(sc,productions);
                case 3->showAllProductions(productions);
                case 4->searchMovieByName(sc,productions);
                case 5->showMoviesByDirector(sc,productions);
                case 6->showMoviesByProduction(sc,productions);
                case 7->showTotalMovies(productions);
                case 8->{
                    SaveProductionstoFile(productions);
                    System.out.println("Data saved sucessfully. Exiting Cineverse Universe...");
                }
                default -> System.out.println("Invalid Choice!! Try Again");
            }
        } while (choice!=8);
    }
    private static void addProduction(Scanner sc,List<Production> productions){
        System.out.print("Enter Production Name: ");
        String production_name = sc.nextLine();
        productions.add(new Production(production_name));
        System.out.println("Production added successfully");
        SaveProductionstoFile(productions);
    }
    private static void addMovietoProduction(Scanner sc,List<Production> productions){
        if(productions.isEmpty()){
            System.out.println("No productions found. Add a production first!!");
            return;
        }
        System.out.println("Select Production: ");
        for(int i=0;i<productions.size();i++){
            System.out.println((i+1)+". "+productions.get(i).getName());
        }
        System.out.println("Enter choice: ");
        int production_choice = sc.nextInt()-1;
        sc.nextLine();
        if(production_choice<0 || production_choice>=productions.size()){
            System.out.println("Invalid selection!");
            return;
        }
        Production selectedProd = productions.get(production_choice);
        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Director Name: ");
        Director dir = new Director(sc.nextLine());

        System.out.print("Enter Actor Name: ");
        Actor actor = new Actor(sc.nextLine());

        System.out.print("Enter Actress Name: ");
        Actress actress = new Actress(sc.nextLine());

        System.out.print("Enter Cinematographer Name: ");
        Cinematographer cinematographer = new Cinematographer(sc.nextLine());

        System.out.print("Enter Editor Name: ");
        Editor edit = new Editor(sc.nextLine());

        selectedProd.addMovie(new Movie(title,dir,actor,actress,cinematographer,edit));
        System.out.println("Movie added sucessfully!!!");
        SaveProductionstoFile(productions);
    }

    private static void showAllProductions(List<Production> productions){
        if(productions.isEmpty()){
            System.out.println("No productions available!!");
        }else{
            for(Production p: productions) p.showProduction();
        }
    }

    private static void searchMovieByName(Scanner sc,List<Production> productions){
        System.out.print("Enter movie title to search: ");
        String title=sc.nextLine();
        boolean found=false;
        for(Production p:productions){
            for(Movie m: p.getMovies()){
                if(m.getTitle().equalsIgnoreCase(title)){
                    System.out.println("\nFound in Production: "+p.getName());
                    m.showMovie();
                    found=true;
                }
            }
        }
        if(!found)
            System.out.println("No Movie found with that name...");
    }

    private static void showMoviesByDirector(Scanner sc,List<Production> productions){
        System.out.print("Enter Director Name: ");
        String director_name=sc.nextLine();
        boolean found=false;
        for(Production p:productions){
            for(Movie m: p.getMovies()){
                if(m.getDirector().getName().equalsIgnoreCase(director_name)){
                    System.out.println("\nProduction: "+p.getName());
                    m.showMovie();
                    found=true;
                }
            }
        }
        if(!found)
            System.out.println("No Movie found for the director.");
    }


    private static void showMoviesByProduction(Scanner sc,List<Production> productions){
        System.out.print("Enter Production Name: ");
        String production_name=sc.nextLine();
        boolean found=false;
        for(Production p:productions){
            if(p.getName().equalsIgnoreCase(production_name)) {
                p.showProduction();
                found=true;
            }
        }
        if(!found)
            System.out.println("No Movie found for the director.");
    }

    private static void showTotalMovies(List<Production> productions){
        int total_movies=0;
        for(Production p:productions) total_movies+=p.getMovies().size();
        System.out.println("Total number of movies managed: "+total_movies);
    }
//    ------------------------ File Handling ------------------------
    private static void SaveProductionstoFile(List<Production> productions){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(productions);
        }
        catch (IOException e){
            System.out.println("Error saving: "+e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private static List<Production> ProductionsFromFile(){
        File f=new File(FILE_NAME);
        if(!f.exists()) return new ArrayList<>();
        try(ObjectInputStream os = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            List<Production> productions = (List<Production>) os.readObject();
            System.out.println("Loaded existing production sucessfully!!");
            return productions;
        }
        catch (IOException | ClassNotFoundException exception){
            System.out.println("Error loading data. Starting from scratch.");
            return new ArrayList<>();
        }
    }
}
