package environment.launch;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;

public interface Presentation extends Drawable {
	@Override
	default void draw(Graphics2D g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString(this.getClass().getName(), 45, 75);
	}

	public abstract Game getGame(JPanel panel, KeyRequest KEYS);
}
