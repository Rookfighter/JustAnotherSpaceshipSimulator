package jass.view.hud;

import java.util.List;

public interface ISpaceHUD extends IHudElement{

	void addElement(final IHudElement p_element);
	void removeElement(final IHudElement p_element);
	void clearElements();
	List<IHudElement> getElements();
}
