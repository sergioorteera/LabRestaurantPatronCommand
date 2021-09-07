package co.edu.unicauca.commandrestaurant.domain;

import co.edu.unicauca.commandrestaurant.domain.service.FoodService;
import co.edu.unicauca.commandrestaurant.access.RepositoryFactory;
import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Comando concreto para crear una comida
 * 
 * @author Sergio Erazo, Camilo Perafan
 */
public class DeleteCommand extends Command{

    /**
     * Comida a eliminar
     */
    private Food food;
    /**
     * Comida previa
     */
    private Food foodPrevious;
    /**
     * Instancia del receptor
     */
    private FoodService service;

    /**
     * Constructor parametrizado
     * 
     * @param food comida a eliminar
     */
    public DeleteCommand(Food food) {
        this.food = food;
        IFoodRepository repo = RepositoryFactory.getInstance().getRepository("default");
        service = new FoodService(repo);
        this.hasUndo= true;
    }

    @Override
    public void execute() {
        Logger logger= LoggerFactory.getLogger(DeleteCommand.class); 
        logger.info("Comando de eliminar ejecutado. Se elimino la comida " + food.toString());
        service.delete(food.getId());
    }
    

    @Override
    public void undo() {
        Logger logger= LoggerFactory.getLogger(DeleteCommand.class); 
        logger.info("Comando de eliminar deshecho. Se creó la comida " + food.toString());
        service.create(foodPrevious);
    }
    
    
    public Food getFoodPrevious() {
        return foodPrevious;
    }

    public void setFoodPrevious(Food componentPrevious) {
        this.foodPrevious = componentPrevious;
    }
}
