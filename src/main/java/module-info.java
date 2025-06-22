/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module com.mycompany.test.tracker {
    requires java.logging;
    requires java.desktop;
    requires java.sql;
    requires org.jfree.jfreechart;
    requires org.jetbrains.annotations;
    requires org.slf4j;
    requires com.opencsv;
    requires org.apache.commons.collections4;

    opens com.mycompany.test.tracker.model to com.opencsv;

    exports com.mycompany.test.tracker;
}