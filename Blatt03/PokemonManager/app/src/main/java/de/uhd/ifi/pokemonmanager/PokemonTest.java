package de.uhd.ifi.pokemonmanager;

import java.util.ArrayList;
import java.util.List;

public class PokemonTest {

    public static final String BEFORE_SWAP = "Before Swap:";
    public static final String AFTER_SWAP = "After Swap:";

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

        // A 2.1
        // Test Constructor of Trainers
        System.out.println();
        System.out.println("Testen des Konstruktors von Trainer");
        Trainer t0 = new Trainer("Peter", "Lustig");
        System.out.println("Trainer t0 angelegt mit firstname Peter und lastname Lustig");
        Trainer t1 = new Trainer("Alisa", "Traurig");
        System.out.println("Trainer t1 angelegt mit firstname Alisa und lastname Traurig");

        // Test of toString and check if each Trainer is constructed right
        System.out.println();
        System.out.println("Testen von toString und ob alle Trainer richtig angelegt wurden");
        System.out.println("Trainer t0:");
        System.out.println(t0);
        System.out.println("Trainer t1:");
        System.out.println(t1);

        // Test of getter and setter of firstName
        System.out.println();
        System.out.println("Testen von getFirstName");
        System.out.println("Vorname von Trainer t0:");
        System.out.println(t0.getFirstName());
        System.out.println("Testen von setFirstName");
        System.out.println("Vorname von t0 zu Petra ändern:");
        t0.setFirstName("Petra");
        System.out.println(t0);

        // Test of getter and setter of lastName
        System.out.println();
        System.out.println("Testen von getLastName");
        System.out.println("Nachname von Trainer t1:");
        System.out.println(t1.getLastName());
        System.out.println("Testen von setLastName");
        System.out.println("Nachname von t1 zu Fröhlich ändern:");
        t1.setLastName("Fröhlich");
        System.out.println(t1);

        // Test add a Pokemon to Trainer t0 and Test getPokemons
        System.out.println();
        System.out.println("Testen von addPokemon und getPokemons");
        t0.addPokemon(p0);
        System.out.println("Pokemon p0:");
        System.out.println(p0);
        System.out.println("Pokemons of " + t0 + ": ");
        System.out.println(t0.getPokemons());

        // Test setPokemons and getPokemons
        System.out.println();
        System.out.println("Testen von SetPokemons über eine Liste");
        List<Pokemon> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        System.out.println("p1 und p2 in einer Liste als Pokemons von t1 setzen:");
        t1.setPokemons(ps);
        System.out.println("Pokemons of " + t1 + ": ");
        System.out.println(t1.getPokemons());

        // Test getPokemon(index)
        System.out.println();
        System.out.println("Testen von getPokemon(index)");
        System.out.println("Das 2. Pokemon von Trainer t1 (Index 1)");
        System.out.println(t1.getPokemon(1));

        // Test getPokemonOfType(type)
        System.out.println();
        System.out.println("Testen von getPokemonOfType");
        System.out.println("Pokemon von Trainer t1 vom Typ Poison:");
        System.out.println(t1.getPokemonsOfType(Type.POISON));

        // A 2.2
        // Test Swap when there should be no problem
        System.out.println();
        System.out.println("Testen von Swap ohne Probleme");
        System.out.println(BEFORE_SWAP);
        System.out.println(p0);
        System.out.println(p1);
        Swap s1 = new Swap();
        s1.execute(p0, p1);
        System.out.println(AFTER_SWAP);
        System.out.println(p0);
        System.out.println(p1);

        // Test Swap for Pokemon with the same trainer
        System.out.println();
        System.out.println("Testen von Swap mit gleichem Trainer");
        System.out.println(BEFORE_SWAP);
        System.out.println(p0);
        System.out.println(p2);
        Swap s2 = new Swap();
        s2.execute(p0, p2);
        System.out.println(AFTER_SWAP);
        System.out.println(p0);
        System.out.println(p2);

        // Test Swap with p1 not allowed to swap
        System.out.println();
        System.out.println("Testen von Swap mit p1 ohne Tauscherlaubnis");
        System.out.println(BEFORE_SWAP);
        System.out.println(p1);
        System.out.println(p2);
        p1.setSwapAllow(false);
        Swap s3 = new Swap();
        s3.execute(p1, p2);
        System.out.println(AFTER_SWAP);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println();

        // Test Competition with both Pokemon having the same trainer
        System.out.println();
        System.out.println("Testen von Competition mit gleichem Trainer!");
        System.out.println("Vor Competition: ");
        System.out.println(p1);
        System.out.println(p2);
        Competition comp1 = new Competition();
        comp1.execute(p1, p2);
        System.out.println("Nach Competition: ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println();
    }
}
