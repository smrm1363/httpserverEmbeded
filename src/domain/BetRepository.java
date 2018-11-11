package domain;

/**
 * This is a repository of Bet. This is Singleton
 */
public class BetRepository extends AbstractRepository<Bet> {
    private static BetRepository repository;
    private BetRepository() {

    }

    public static BetRepository getInstance()
    {
        if(repository==null)
            repository = new BetRepository();
        return repository;
    }






}
