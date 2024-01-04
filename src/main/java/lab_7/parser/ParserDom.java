package lab_7.parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// Через Edit Configuration добавить пути "example.xml" и "student.xml" в Program arguments
public class ParserDom {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        String inFile = args[0], outFile = args[1];
        parseDom(inFile, outFile);
    }

    private static void parseDom(String input, String output) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String lastname = null;
        String title = null;
        double average = 0;
        double mark = 0;
        List<Double> marks = new LinkedList<>();
        List<String> titles = new LinkedList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        Element element = document.getDocumentElement();

        if (element.getNodeType() == Node.ELEMENT_NODE) {
            lastname = element.getAttribute("lastname");
        }
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (list.item(i).getNodeName().equals("subject")) {
                    NamedNodeMap nnm = list.item(i).getAttributes();
                    for (int j = 0; j < nnm.getLength(); j++) {
                        Attr attr = (Attr) nnm.item(j);
                        if (attr.getName().equals("mark")) {
                            mark = Double.parseDouble(attr.getValue());
                        }
                        if (attr.getName().equals("title")) {
                            title = attr.getValue();
                            System.out.println("Название: " + title + "\n" + "Оценка: " + mark);
                        }
                    }
                    if (mark >= 1 && mark <= 5) {
                        marks.add(mark);
                        titles.add(title);
                    } else {
                        throw new ArithmeticException();
                    }
                }
                if (list.item(i).getNodeName().equals("average")) {
                    average = Double.parseDouble(list.item(i).getTextContent());
                    System.out.println("Средняя оценка: " + average);
                }
            }
        }
        double avg = 0;
        for (Double m : marks) {
            avg = avg + m;
        }
        avg = avg / marks.size();

        if (avg != average) {
            System.out.println("Средняя оценка исправлена!");
            String correct_avg = Double.toString(avg);
            System.out.println("Исправленная оценка = " + correct_avg);
            average = avg;
        } else System.out.println("Оценка стоит правильно");

        document = builder.newDocument();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        Element root = document.createElement("student");
        root.setAttribute("lastname", lastname);
        document.appendChild(root);
        for (int i = 0; i < titles.size(); i++) {
            Element subject = document.createElement("subject");
            subject.setAttribute("title", titles.get(i));
            subject.setAttribute("mark", String.valueOf(marks.get(i)));
            root.appendChild(subject);
        }
        Element averageElement = document.createElement("average");
        averageElement.setTextContent(String.valueOf(average));
        root.appendChild(averageElement);

        DOMSource source = new DOMSource(document);
        StreamResult file = new StreamResult(new File(output));
        transformer.transform(source, file);
    }
}

