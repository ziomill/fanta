package it.millsoft.fantarepubic.utils;

public class PlayerStats
{
    private String giocatore;
    private String squadra;
    private int presenze;
    private int goals;
    private int assists;
    private int ammonizioni;
    private int espulsioni;
    private double fantaMedia;

    public PlayerStats(String giocatore,  String squadra, int presenze, int goal, int assists, int ammonizioni, int espulsioni, double fantaMedia) {
        this.giocatore = giocatore;
        this.squadra = squadra;
        this.presenze = presenze;
        this.goals = goal;
        this.assists = assists;
        this.ammonizioni = ammonizioni;
        this.espulsioni = espulsioni;
        this.fantaMedia = fantaMedia;
    }

    public PlayerStats(String giocatore)
    {
        this.giocatore = giocatore;
    }

    public String getCognome() {
        return giocatore;
    }


    public String getSquadra() {
        return squadra;
    }

    public int getPresenze() {
        return presenze;
    }

    public int getGoal() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getAmmonizioni() {
        return ammonizioni;
    }

    public int getEspulsioni() {
        return espulsioni;
    }

    public double getFantaMedia() {
        return fantaMedia;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "cognome='" + giocatore + '\'' +
                ", squadra='" + squadra + '\'' +
                ", presenze=" + presenze +
                ", goal=" + goals +
                ", assists=" + assists +
                ", ammonizioni=" + ammonizioni +
                ", espulsioni=" + espulsioni +
                ", fantaMedia=" + fantaMedia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerStats that = (PlayerStats) o;

        return giocatore.equals(that.giocatore);
    }

    @Override
    public int hashCode() {
        return giocatore.hashCode();
    }
}
