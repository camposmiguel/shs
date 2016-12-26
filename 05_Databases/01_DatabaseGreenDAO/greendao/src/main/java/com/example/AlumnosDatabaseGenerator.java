package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class AlumnosDatabaseGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.miguelcr.a01_databasegreendao.model");
        schema.setDefaultJavaPackageDao("com.miguelcr.a01_databasegreendao.dao");

        shsSchemaDatabasev1(schema);

        try {
            new DaoGenerator()
                    .generateAll(schema, "../01_DatabaseGreenDAO/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void shsSchemaDatabasev1(Schema schema) {
        Entity alumnoDB = schema.addEntity("AlumnoDB");
        alumnoDB.addIdProperty().primaryKey().autoincrement();
        alumnoDB.addStringProperty("nombre").notNull();
        alumnoDB.addIntProperty("edad");
        alumnoDB.addStringProperty("apellidos");
        alumnoDB.addFloatProperty("notaMedia");
        alumnoDB.addLongProperty("claseId").notNull();

        Entity claseDB = schema.addEntity("ClaseDB");
        claseDB.addIdProperty().primaryKey().autoincrement();
        claseDB.addStringProperty("nombre").notNull();

        //Property idClase = alumnoDB.addLongProperty("claseId").notNull().getProperty();
        //ToMany alumnosDeUnaClase = claseDB.addToMany(alumnoDB, idClase);
        //alumnosDeUnaClase.setName("alumnos");

    }

}
