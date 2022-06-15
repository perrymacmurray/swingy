import com.perrymacmurray.swingy.SwingyClick;
import com.perrymacmurray.swingy.SwingyReportedEvent;
import com.perrymacmurray.swingy.SwingyUserInput;
import com.perrymacmurray.swingy.SwingyWindow;

public class SwingyDemo
{
    public static void main (String[] args) {
        SwingyWindow window = new SwingyWindow();

        String color = "black";
        while (true) {
            SwingyReportedEvent e = window.getAnyEvent();

            if (e instanceof SwingyClick click) {
                window.setCanvasColor(click.getX(), click.getY(), color);
            }
            else if (e instanceof SwingyUserInput input) {
                color = input.getInputString();
            }
        }
    }
}
