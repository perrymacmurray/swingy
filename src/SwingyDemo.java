import com.perry.swingy.SwingyClick;
import com.perry.swingy.SwingyReportedEvent;
import com.perry.swingy.SwingyUserInput;
import com.perry.swingy.SwingyWindow;

public class SwingyDemo
{
    public static void main (String[] args) {
        SwingyWindow window = new SwingyWindow();
        String anything = window.getUserInput().getInputString();
        window.fillCanvas(anything);
    }
}
