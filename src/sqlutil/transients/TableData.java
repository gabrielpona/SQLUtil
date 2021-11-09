
package sqlutil.transients;

import java.util.ArrayList;
import java.util.List;


public class TableData {
    
    private String name;
        
    private List<ColumnData> columns;

    public TableData() {        
        this.columns = new ArrayList<>();
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnData> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnData> columns) {
        this.columns = columns;
    }
            
}
