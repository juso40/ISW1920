package com.example.blatt02;

public class PokemonTest {
    public static void main(String[] args) {
        // https://de.wikipedia.org/wiki/Liste_der_Pok%C3%A9mon
        // Test of Constructor with each Type
        System.out.println("Testen des Konstruktors");
        Pokemon p0 = new Pokemon("Pikachu", Type.POISON);
        System.out.println("Pokemon p0 angelegt mit Name Pikachu und Typ Poison");
        Pokemon p1 = new Pokemon("Carapuce", Type.WATER);
        System.out.println("Pokemon p1 angelegt mit Name Carapuce und Typ Water");
        Pokemon p2 = new Pokemon("Raupy", Type.FIRE);
        System.out.println("Pokemon p2 angelegt mit Name Raupy und Typ Fire");

        // Test of toString and check if each Pokemon is constructed right
        System.out.println();
        System.out.println("Testen von toString und ob alle Pokemon richtig angelegt wurden");
        System.out.println("Pokemon p0:");
        System.out.println(p0);
        System.out.println("Pokemon p1:");
        System.out.println(p1);
        System.out.println("Pokemon p2:");
        System.out.println(p2);

        // Test of getter and setter of name
        System.out.println();
        System.out.println("Testen von getName");
        System.out.println("Name von Pokemon p1:");
        System.out.println(p1.getName());
        System.out.println("Testen von setName");
        System.out.println("Name von p1 zu Schiggy ändern:");
        p1.setName("Schiggy");
        System.out.println(p1);

        // Test of getter and setter of type
        System.out.println();
        System.out.println("Testen von getType");
        System.out.println("Typ von Pokemon p2:");
        System.out.println(p2.getType());
        System.out.println("Testen von setType");
        System.out.println("Typ von p2 zu Water ändern");
        p2.setType(Type.WATER);
        System.out.println(p2);

        // Test of getter of number (no setter available, because it should not be
        // changed)
        System.out.println();
        System.out.println("Testen von getNumber");
        System.out.println("Nummer von p2: " + p2.getNumber());

        // Trainer erzeugen

        Trainer Ash = new Trainer("Ash", "Ketchum");
        Ash.addPokemon(p1);
        String pokis;
        pokis = Ash.getPokemonNames();
        System.out.println(pokis);


        // Getter Test From Trainer
        System.out.println();
        System.out.println("Testen von getFirstName");
        System.out.println("First Name von Trainer:");
        System.out.println(Ash.getFirstName());
        System.out.println("Testen von getLastName");
        System.out.println("Last Name von Trainer:");
        System.out.println(Ash.getLastName());

        // Testen von Setter Funktionen
        System.out.println();
        System.out.println("Testen von setFirstName");
        Ash.setFirstName("Red");
        System.out.println("New First Name von Trainer:");
        System.out.println(Ash.getFirstName());
        System.out.println("Testen von setLastName");
        Ash.setLastName("---");
        System.out.println("New Last Name von Trainer:");
        System.out.println(Ash.getLastName());

        // Testen von addPokemon
        Pokemon testPokemon = new Pokemon("MissingNo", Type.POISON);
        System.out.println("Testen von addPokemon");
        System.out.println("Vorher Pokemons:");
        System.out.println(Ash.getPokemonNames());
        Ash.addPokemon(testPokemon);
        System.out.println("Nachher Pokemons:");
        System.out.println(Ash.getPokemonNames());

        // Testen von Trainer des Pokemons ändern?!
        Pokemon testPokemon2 = new Pokemon("Namenslos", Type.FIRE);
        System.out.println("Testen von trainer getter im Pokemon: ");
        System.out.println(testPokemon2.getMyTrainer());
        System.out.println("Nach zuweisung zum Trainer:");
        Ash.addPokemon(testPokemon2);
        System.out.println(testPokemon2.getMyTrainer());


    }
}
