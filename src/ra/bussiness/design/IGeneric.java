package ra.bussiness.design;

public interface IGeneric <T,E>{
    void displayAll();
    void addNew();
    void update();
    T changeOfStatus(E id ,boolean active );
     T findById(E id);
}
