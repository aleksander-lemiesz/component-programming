package Greeter;

public class Greeter {
    private MessageFormatter messageFormatter;

    public Greeter(MessageFormatter formatter) {
        messageFormatter = formatter;
    }

    public String greet(String who) {
        return messageFormatter.format(who);
    }
}
