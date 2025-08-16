package com.vasl.samp.dal.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ApiConfiguration {
    private Type type;
    private Category category;
    private Technology technology;


    public enum Type {
        HTTP,
        TCP,
        STREAMING;

        private List<Category> categories = Collections.emptyList();

        static {
            HTTP.categories = Arrays.asList(Category.REST, Category.SOAP);
            STREAMING.categories = Arrays.asList(Category.JMS, Category.DATABASE);
        }

        public List<Category> getCategories() {
            return this.categories;
        }
    }

    public enum Category {
        REST("rest"),
        SOAP("soap"),
        JMS("jms"),
        DATABASE("database");

        private String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Category getCategory(String value) {
            for (var category : Category.values()) {
                if (category.getValue().equals(value)) return category;
            }
            return null;
        }

        private List<Technology> technologies = Collections.emptyList();

        static {
            JMS.technologies = Arrays.asList(Technology.KAFKA);
            DATABASE.technologies = Arrays.asList(Technology.MONGODB);
        }

        public List<Technology> getTechnologies() {
            return technologies;
        }
    }

    public enum Technology {
        KAFKA("kafka-connector"),
        MONGODB("mongo-connector"),
        RABBITMQ("rabbitmq-connector");

        private final String connector;

        Technology(String connectorId) {
            this.connector = connectorId;
        }

        public String getConnector() {
            return this.connector;
        }

        public static Technology getTechnology(String connector) {
            for (var technology : Technology.values()) {
                if (technology.getConnector().equals(connector)) return technology;
            }
            return null;
        }
    }
}
