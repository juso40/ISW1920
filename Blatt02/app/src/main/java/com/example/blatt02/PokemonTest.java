package com.example.blatt02;

public class PokemonTest {
    public static void main(String[] args) {
        // https://de.wikipedia.org/wiki/Liste_der_Pok%C3%A9mon
        // Test of Constructor with each Type
        System.out.println("Testen des Konstruktors");
        Pokemon p0 = new Pokemon("Pikachu", Type.POISON, true);
        System.out.println("Pokemon p0 angelegt mit Name Pikachu und Typ Poison");
        Pokemon p1 = new Pokemon("Carapuce", Type.WATER, true);
        System.out.println("Pokemon p1 angelegt mit Name Carapuce und Typ Water");
        Pokemon p2 = new Pokemon("Raupy", Type.FIRE, true);
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

        Trainer ash = new Trainer("Ash", "Ketchum");
        ash.addPokemon(p1);
        String pokis;
        pokis = ash.getPokemonNames();
        System.out.println(pokis);


        // Getter Test From Trainer
        System.out.println();
        System.out.println("Testen von getFirstName");
        System.out.println("First Name von Trainer:");
        System.out.println(ash.getFirstName());
        System.out.println("Testen von getLastName");
        System.out.println("Last Name von Trainer:");
        System.out.println(ash.getLastName());

        // Testen von Setter Funktionen
        System.out.println();
        System.out.println("Testen von setFirstName");
        ash.setFirstName("Red");
        System.out.println("New First Name von Trainer:");
        System.out.println(ash.getFirstName());
        System.out.println("Testen von setLastName");
        ash.setLastName("---");
        System.out.println("New Last Name von Trainer:");
        System.out.println(ash.getLastName());

        // Testen von addPokemon
        Pokemon testPokemon = new Pokemon("MissingNo", Type.POISON, true);
        System.out.println("Testen von addPokemon");
        System.out.println("Vorher Pokemons:");
        System.out.println(ash.getPokemonNames());
        ash.addPokemon(testPokemon);
        System.out.println("Nachher Pokemons:");
        System.out.println(ash.getPokemonNames());

        // Testen von Trainer des Pokemons ändern?!
        Pokemon testPokemon2 = new Pokemon("Namenslos", Type.FIRE, true);
        System.out.println("Testen von trainer getter im Pokemon: ");
        System.out.println(testPokemon2.getMyTrainer());
        System.out.println("Nach zuweisung zum Trainer:");
        ash.addPokemon(testPokemon2);
        System.out.println(testPokemon2.getMyTrainer());

        // Testen von Swap
        Pokemon swapPoki1 = new Pokemon("Foo", Type.FIRE, false);
        Pokemon swapPoki2 = new Pokemon("Bar", Type.FIRE, true);
        Pokemon swapPoki3 = new Pokemon("Bat", Type.FIRE, true);
        Pokemon swapPoki4 = new Pokemon("Baz", Type.WATER, true);
        Trainer swapTrainer1 = new Trainer("Foo", "Bar");
        Trainer swapTrainer2 = new Trainer("Batt", "Bazz");
        swapTrainer1.addPokemon(swapPoki1);
        swapTrainer2.addPokemon(swapPoki2);
        swapTrainer2.addPokemon(swapPoki3);


        Swap swap1;
        Swap swap2;
        Swap swap3;
        swap1 = new Swap();
        // test swap between swapAllow true/false
        System.out.println();
        System.out.println("Before: ");
        System.out.println("PokemonA swapAllow: " + swapPoki1);
        System.out.println("PokemonB swapAllow: " + swapPoki2);
        System.out.println("Testing swap between Pokemons with swapAllow false and true:");
        System.out.println("After: ");
        swap1.execute(swapPoki1, swapPoki2);
        System.out.println("PokemonA swapAllow: " + swapPoki1);
        System.out.println("PokemonB swapAllow: " + swapPoki2);
        System.out.println();

        // test of swapping between same trainer
        System.out.println();
        System.out.println("Before: ");
        System.out.println("PokemonA:" + swapPoki2);
        System.out.println("PokemonB:" + swapPoki3);
        System.out.println("Testing swapping between same trainer");
        System.out.println("After:");
        swap2 = new Swap();
        swap2.execute(swapPoki2, swapPoki3);
        System.out.println("PokemonA: - " + swapPoki2);
        System.out.println("PokemonB: - " + swapPoki3);

        // test Swap between Trainer and no Trainer
        System.out.println();
        System.out.println("Before:");
        System.out.println("PokemonA: " + swapPoki3);
        System.out.println("PokemonB: " + swapPoki4);
        System.out.println("Testing swapping between trainer and no trainer:");
        System.out.println("After:");
        swap3 = new Swap();
        swap3.execute(swapPoki3, swapPoki4);
        System.out.println("PokemonA: " + swapPoki3);
        System.out.println("PokemonB: " + swapPoki4);

        // test between 2 trainers and both swapAllow true
        System.out.println();
        System.out.println("Testing swapping with swapAllow true/true and different trainers:");
        System.out.println("Before:");
        System.out.println(swapTrainer1.getPokemons());
        System.out.println(swapTrainer2.getPokemons());
        swapPoki1.setSwapAllow(true);
        System.out.println("Pokemon A set swapAllow True ->");
        System.out.println(swapTrainer1.getPokemons());
        System.out.println();

        System.out.println("After swapping:");
        Swap swap4 = new Swap();
        swap4.execute(swapPoki1, swapPoki2);
        System.out.println(swapTrainer1.getPokemons());
        System.out.println(swapTrainer2.getPokemons());
        System.out.println();
        System.out.println("getSwaps of a pokemon:");
        System.out.println(swapPoki2.getSwaps());
        System.out.println();
        System.out.println("Testing some getters of the swaps of a pokemon:");
        System.out.println(swapPoki2.getSwaps().get(0).getTrainerA());
        System.out.println(swapPoki2.getSwaps().get(0).getPokemonB());
        System.out.println(swapPoki2.getSwaps().get(0).getId());





    }
}
