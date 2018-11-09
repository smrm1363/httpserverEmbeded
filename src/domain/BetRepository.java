package domain;

;

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
