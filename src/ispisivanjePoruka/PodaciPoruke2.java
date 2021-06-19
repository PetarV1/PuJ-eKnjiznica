package ispisivanjePoruka;

//ovaj interface je napravljen posebno zato sto KnjigeController ima 2 forme, stoga trebaju 4 metode.

public interface PodaciPoruke2 {
    public abstract void ispisiPorukuAutor();
    public abstract void ispisiPorukuKnjiga();
    public abstract void nepopunjeniPodaciAutor();
    public abstract void nepopunjeniPodaciKnjiga();
}
