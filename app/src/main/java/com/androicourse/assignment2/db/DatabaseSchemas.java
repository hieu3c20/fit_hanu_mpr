package com.androicourse.assignment2.db;

public class DatabaseSchemas {
public static final class ProductTable {
        public static final String NAME = "products";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String THUMBNAIL = "thumbnail";
            public static final String NAME = "name";
            public static final String CATEGORY = "category";
            public static final String UNIT_PRICE = "unit_price";
            public static final String QUANTITY = "quantity";
        }
    }
}
