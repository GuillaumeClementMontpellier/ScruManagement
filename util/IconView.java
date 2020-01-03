package util;

import javafx.scene.layout.Region;
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

    public IconView() {
    }

    public IconView(String icon) {
        setIcon(icon);
    }

    public static IconView valueOf(String value) throws IOException {
        return new IconView(value);
    }

    public void setIcon(String icon) {

        String path = "../static/fontawesome/svgs/solid/" + icon + ".svg";

        String url = String.valueOf(getClass().getResource(path));

        SVGParser SVGParser = new SVGParser(url);

        if (SVGParser.failed()) return;

        String value = SVGParser.getPath();

        this.svg = new SVGPath();

        svg.setContent(value);

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

    private class SVGParser {

        private String url;
        private String value;

        public SVGParser(String url) {
            this.url = url;
        }

        public String getPath() {
            return value;
        }

        public boolean failed() {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                return true;
            }

            Document document = null;
            try {
                document = builder.parse(url);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
                return true;
            }

            String xpathExpression = "//path/@d";

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            XPathExpression expression = null;

            try {
                expression = xpath.compile(xpathExpression);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
                return true;
            }

            NodeList svgPaths = null;
            try {
                svgPaths = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
                return true;
            }

            if (svgPaths.getLength() < 1) {
                System.out.println("No path in svg");
                return true;
            }

            value = svgPaths.item(0).getNodeValue();
            return false;
        }
    }
}