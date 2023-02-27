package dev.fenix.application.production.stock.model;

import java.util.Date;

public interface StockProductCount {
    String getNameProduct();
    Float getQuantity();
    int getIdProduct();
    Date dateStock();
}
