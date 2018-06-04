package controller;

import ecs.Engine;
import ecs.Entity;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class MovementSystem extends CustomSystem{
	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
		MoveComponent move;
		PositionComponent position;
		int x,y;
		
		for(final Entity e : this) {
			
			move = e.get(MoveComponent.class);
			position = e.get(PositionComponent.class);
			
			x = move.movement.getX();
			y = move.movement.getY();
			
			position.pos.x += x;
			position.pos.y += y;
		
		}
	}
}
