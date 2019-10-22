package RPIS71.Kazachenko.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.time.LocalDate;

public class XmlTask {

    private final String filePath;

    XmlTask(String filePath) {
        this.filePath = filePath;
    }

    int salaryAverage() {
        int summary = 0;
        int count = 0;
        Document doc = parseDocument();
        assert doc != null;
        NodeList salaryes = doc.getElementsByTagName("salary");
        Element salaryTag = null;

        for (int i = 0; i < salaryes.getLength(); i++) {
            salaryTag = (Element) salaryes.item(i);
            summary += Integer.parseInt(salaryTag.getTextContent());
            count++;
        }

        return (summary / count);

    }

    int salaryAverage(String departmentName) throws Exception{
        int summary = 0;
        int count = 0;
        int corr = -1;
        Document doc = parseDocument();
        assert doc != null;
        NodeList departments = doc.getElementsByTagName("department");
        Element departmentTag = null;

        for (int i = 0; i < departments.getLength(); i++) {
            departmentTag = (Element) departments.item(i);
            if (departmentName.equals(departmentTag.getAttribute("name"))) {
                corr = i;
                break;
            }
        }
        if (corr == -1) throw new Exception("Заданный департамент отсутсвует");

        NodeList salaryes = departmentTag.getElementsByTagName("salary");
        Element salaryTag = null;
        for (int i = 0; i < salaryes.getLength(); i++) {
            salaryTag = (Element) salaryes.item(i);
            summary += Integer.parseInt(salaryTag.getTextContent());
            count++;
        }

        return (summary / count);
    }

    void setSalary(String firstName, String secondName, int newSalary) throws Exception{
        int corr = -1;
        Document doc = parseDocument();
        assert doc != null;
        NodeList employes = doc.getElementsByTagName("employee");
        Element employeeTag = null;
        String thisFirstname, thisSecondName;

        for (int i = 0; i < employes.getLength(); i++) {
            employeeTag = (Element) employes.item(i);
            thisFirstname = employeeTag.getAttribute("firstname");
            thisSecondName = employeeTag.getAttribute("secondname");
            if ((firstName.equals(thisFirstname))
                    && secondName.equals(thisSecondName)) {
                corr = i;
                break;
            }
        }

        if (corr == -1)
            throw new Exception("Человек не найден");

        NodeList salaryes = doc.getElementsByTagName("salary");
        Element salaryTag = null;

        for (int i = 0; i < salaryes.getLength(); i++) {
            salaryTag = (Element) salaryes.item(i);
            if (i == corr) salaryTag.setTextContent(String.valueOf(newSalary));
        }
        changeDocument(doc);
    }

    void fireEmployee(String firstName, String secondName) {
        Document doc = parseDocument();
        assert doc != null;
        NodeList employees = doc.getElementsByTagName("employee");
        Element employeeTag = null;
        String thisFirstName, thisSecondName;

        for (int i = 0; i < employees.getLength(); i++) {
            employeeTag = (Element) employees.item(i);
            thisFirstName = employeeTag.getAttribute("firstname");
            thisSecondName = employeeTag.getAttribute("secondname");
            if (thisFirstName.equals(employeeTag.getAttribute("firstname"))
                    && thisSecondName.equals(employeeTag.getAttribute("secondName")))
                employeeTag.getParentNode().removeChild(employeeTag);
        }
        changeDocument(doc);
    }

    private Document parseDocument() {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            return domFactory.newDocumentBuilder().parse(new InputSource(filePath));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void changeDocument(Document doc) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(filePath));
            System.out.println("XML file updated successfully");
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
