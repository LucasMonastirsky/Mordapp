package frise.project.mordapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frise.project.mordapp.retrofit.HELPER;

public class RowContainer {

   private List<Row> rows;

   public RowContainer(Row[] rows) {
      this.rows = new ArrayList<Row>(Arrays.asList(rows));
   }
   public RowContainer(List<Row> rows) { this.rows = rows; }

   public List<Row> getRows(){return rows;}

   public List<Item> getItems() {
      List<Item> list = new ArrayList<>();
      int last_pos = 99999;
      int previous_index=0;
      for(Row row : rows) {
         if(row.getPos()==0)
            break;
         if(row.getPos()==last_pos)
            list.get(previous_index).addRow(row);
         else {
            Item t = new Item(row);
            list.add(t);
            previous_index = list.indexOf(t);
            last_pos = t.getPos(); } }
      return list;
   }

   @Override
   public String toString() {
      String s = "";
      for(Row row : rows)
      {
         s += row.toString();
      }
      return s;
   }
}
