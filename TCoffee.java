/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import java.io.File;
import java.io.FileReader;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author Albert
 */
public class TCoffee {

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) throws IOException {
        
    HBaseConfiguration hconfig = new HBaseConfiguration(new Configuration());
    System.out.println( "Connecting..." );
    HBaseAdmin hbase_admin = new HBaseAdmin( hconfig );
    if (!hbase_admin.tableExists("TCoffee")) {
      HTableDescriptor htable = new HTableDescriptor("TCoffee"); 
      htable.addFamily( new HColumnDescriptor("key"));
      htable.addFamily( new HColumnDescriptor("value"));
      System.out.println( "Creating Table..." );
      hbase_admin.createTable( htable );
            
    } else {
      hbase_admin.disableTable("TCoffee");
      hbase_admin.deleteTable("TCoffee");
      HTableDescriptor htable = new HTableDescriptor("TCoffee"); 
      htable.addFamily( new HColumnDescriptor("key"));
      htable.addFamily( new HColumnDescriptor("value"));
      System.out.println( "Delete and creating table..." );
      hbase_admin.createTable( htable );
    }
    HTable hTable = new HTable(hconfig, "TCoffee");
    BufferedReader reader = null;
    int count = 0;
    File file = new File("full_lib.txt");
    reader = new BufferedReader(new FileReader(file));
    String line = null;
    String[] seq = null;
    String[] split = null;
    boolean cond = false;
    while ((line = reader.readLine()) != null) {
	    if (line.indexOf('#') >= 0) {
        	line = line.replace("#", "");
        	seq = line.split("\\s+");
		cond = true;
	    }else if(cond && line.indexOf('!') >= 0){
		    break;
      	    }else if(cond){
        	    split = line.split("\\s+");
		    Put put = new Put(Bytes.toBytes("row"+count));
		    put.addColumn(Bytes.toBytes("key"), Bytes.toBytes("seq1"), Bytes.toBytes(seq[0]));
	            put.addColumn(Bytes.toBytes("key"), Bytes.toBytes("res1"), Bytes.toBytes(split[1]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("seq2"), Bytes.toBytes(seq[1]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("res2"), Bytes.toBytes(split[2]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("pes"), Bytes.toBytes(split[3]));
	            hTable.put(put);
	            count++;
	            put = new Put(Bytes.toBytes("row"+count));
	            put.addColumn(Bytes.toBytes("key"), Bytes.toBytes("seq1"), Bytes.toBytes(seq[1]));
	            put.addColumn(Bytes.toBytes("key"), Bytes.toBytes("res1"), Bytes.toBytes(split[2]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("seq2"), Bytes.toBytes(seq[0]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("res2"), Bytes.toBytes(split[1]));
	            put.addColumn(Bytes.toBytes("value"), Bytes.toBytes("pes"), Bytes.toBytes(split[3]));
	            hTable.put(put);
	            count++;
      	    }
    }
    hTable.close();
    System.out.println("Done!");
  }
}
