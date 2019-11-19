package RPIS71.Kazachenko.wdad.learn.xml;

public class TestXmlTask {

    public static void main(String[] args) {
        XmlTask task = new XmlTask("src/RPIS71/Kazachenko/wdad/learn/xml/poopnfork.xml");
        task.changeOfficiantName("fedor", "petrov", "patra", "kukrova");
    }
}
