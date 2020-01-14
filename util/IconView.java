package util;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class IconView extends Region {

    private SVGPath svg;

    public IconView(String icon) {
        setIcon(icon);
    }

    @SuppressWarnings("unused")
    public static IconView valueOf(String value) {
        return new IconView(value);
    }

    /**
     * Parse the svg in path,
     *
     * @param url : url to svg to parse
     * @return String, path of SVG if success, null if failure
     */
    public static String parseSVG(String url) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        Document document;
        try {
            document = builder.parse(url);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }

        String xpathExpression = "//path/@d";

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        XPathExpression expression;

        try {
            expression = xpath.compile(xpathExpression);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }

        NodeList svgPaths;
        try {
            svgPaths = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }

        if (svgPaths.getLength() < 1) {
            System.out.println("No path in svg");
            return null;
        }

        return svgPaths.item(0).getNodeValue();
    }

    public void setIcon(String icon) {

        String path = "../static/fontawesome/svgs/solid/" + icon + ".svg";

        String fileUrl = String.valueOf(getClass().getResource(path));

        String svgPath = parseSVG(fileUrl);


        if (svgPath == null) {
            return;
        }

        this.svg = new SVGPath();

        svg.setContent(svgPath);

        double width = 15;
        double height = 15;

        adaptScale(width, height);

    }

    private void adaptScale(double width, double height) {

        setMinSize(width, height);
        setPrefSize(width, height);
        setMaxSize(width, height);

        this.getChildren().add(svg);

        double pw = svg.prefWidth(width);
        double ph = svg.prefHeight(height);

        double tx = getTranslateX() - (pw / 2) + (width / 2);
        double ty = getTranslateY() - (ph / 2) + (height / 2);

        setTranslateX(tx);
        setTranslateY(ty);

        double scaleWidth = width / pw;
        double scaleHeight = height / ph;

        svg.setScaleX(scaleWidth);
        svg.setScaleY(scaleHeight);
    }

}
