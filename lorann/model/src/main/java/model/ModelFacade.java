package model;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import model.dao.ExampleDAO;

/**
 * <h1>The Class ModelFacade provides a facade of the Model component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public final class ModelFacade implements IModel {

    /**
     * Instantiates a new model facade.
     */
    public ModelFacade() {
        super();
    }


	@Override
	public ILevel getLevelByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFromFile(File file) {
		// TODO Auto-generated method stub
		
	}
    
}
