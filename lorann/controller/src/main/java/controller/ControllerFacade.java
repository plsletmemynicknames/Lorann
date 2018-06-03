 package controller;

import java.sql.SQLException;
import java.util.List;

import ecs.Engine;
import model.ILevel;
import model.IModel;
import view.IView;

/**
 * <h1>The Class ControllerFacade provides a facade of the Controller component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ControllerFacade implements IController {

    /** The view. */
    private final IView  view;

    /** The model. */
    private final IModel model;
    
    /** The engine. */
    private final Engine engine;
    
    /** The level. */
    private ILevel level;

    /**
     * Instantiates a new controller facade.
     *
     * @param view
     *            the view
     * @param model
     *            the model
     */
    public ControllerFacade(final IView view, final IModel model) {
        this.view = view;
        this.model = model;
        this.engine = new Engine();
    }

    /**
     * Start.
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Override
    public void start() {
//        this.getView().displayMessage(this.getModel().getExampleById(1).toString());
//
//        this.getView().displayMessage(this.getModel().getExampleByName("Example 2").toString());
//
//        final List<Example> examples = this.getModel().getAllExamples();
//        final StringBuilder message = new StringBuilder();
//        for (final Example example : examples) {
//            message.append(example);
//            message.append('\n');
//        }
//        this.getView().displayMessage(message.toString());
    }

    /**
     * Gets the view.
     *
     * @return the view
     */
    @Override
    public IView getView() {
        return this.view;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    @Override
    public IModel getModel() {
        return this.model;
    }
    
    @Override
    public ILevel getCurrentLevel() {
    	return this.level;
    }
    
    @Override
    public void nexLevel() {
    	
    }
}
