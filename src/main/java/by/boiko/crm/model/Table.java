package by.boiko.crm.model;

import java.util.List;

public class Table {

    private String category;
    private List<TypeTrTable> listRow;



    public static class TypeTrTable {

        private String parameter;
        private String value;

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public TypeTrTable(String parameter, String value) {
            this.parameter = parameter;
            this.value = value;
        }
    }

    public Table() {

    }

    public Table(String category, List<TypeTrTable> listRow) {
        this.category = category;
        this.listRow = listRow;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<TypeTrTable> getListRow() {
        return listRow;
    }

    public void setListRow(List<TypeTrTable> listRow) {
        this.listRow = listRow;
    }


}
