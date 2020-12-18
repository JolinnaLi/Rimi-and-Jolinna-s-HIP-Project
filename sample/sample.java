<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.chart.CategoryAxis?>
<?import javafx.scene.chart.LineChart?>
<?import javafx.scene.chart.NumberAxis?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.Menu?>
<?import javafx.scene.control.MenuBar?>
<?import javafx.scene.control.MenuItem?>
<?import javafx.scene.control.Slider?>
<?import javafx.scene.control.SplitPane?>
<?import javafx.scene.control.Tooltip?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.text.Font?>

<AnchorPane fx:id="mainAnchorPane" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="600.0" prefWidth="900.0" style="-fx-background-color: #8F8F8F; -fx-border-color: #8F8F8F;" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="sample.Controller">
    <children>
        <Button fx:id="runBtn" layoutX="140.0" layoutY="477.0" minHeight="-Infinity" minWidth="-Infinity" mnemonicParsing="false" onMouseClicked="#onRunClicked" prefHeight="50.0" prefWidth="150.0" style="-fx-background-color: #68a2f4;" text="RUN" textFill="WHITE" AnchorPane.leftAnchor="140.0" AnchorPane.rightAnchor="140.0" AnchorPane.topAnchor="477.0" />
        <Label fx:id="JolinnaLabel" layoutX="1" layoutY="1" text="This is me saying hello" textFill="RED"/>
    </children>
</AnchorPane>
