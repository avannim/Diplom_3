package steps;

import io.qameta.allure.Step;
import java.awt.Rectangle;

public class CheckSteps {


    @Step("Проверяю находится ли элемент внутри родителя")
    public static boolean isChildInsideParent(org.openqa.selenium.Rectangle child, org.openqa.selenium.Rectangle parent) {
        Rectangle parentBounds = new Rectangle(parent.x, parent.y, parent.width, parent.height);
        Rectangle childBounds = new Rectangle(child.x, child.y, child.width, child.height);

        // Дочерний полностью внутри родителя
        return parentBounds.intersects(childBounds);
   }

}
