package RPIS71.Kazachenko.wdad.learn.xml;

public class TestXmlTask {

    public static void main(String[] args) throws Exception{
        XmlTask task = new XmlTask("src/RPIS71/Kazachenko/wdad/learn/xml/organizm.xml");
        System.out.println(task.salaryAverage());
        System.out.println(task.salaryAverage("HR"));
        task.setSalary("Sokolov", "Slava", 40000);
    }
}
