class PokemonTest{
    public static void main(String args[]){
        Pokemon Pikachu;
        Pokemon Schildgröde;
        Pokemon Maus;
        Pokemon Fischi;
        Pikachu = new Pokemon("Pikachu", Type.FIRE);
        Schildgröde = new Pokemon("Schildgröde", Type.WATER);
        Maus = new Pokemon("Maus", Type.POISON);
        Fischi = new Pokemon("Fischi", Type.WATER);

        System.out.printf("Pokemon Schildgröde: %s\n", Schildgröde.toString());
        System.out.println(Pikachu.getName());
        System.out.println(Fischi.getNumber());
        System.out.println(Maus.toString());
        Pikachu.setName("UwU");
        System.out.println(Pikachu);
    }
}