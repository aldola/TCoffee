/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t.coffee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Albert
 */
public class TCoffee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            File file = new File(args[0]);
            BufferedReader reader = null;
            HashMap hm = new HashMap();
            String comb1;
            String comb2;
            String rest;
            try{
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                String[] seq = null;
                String[] split = null;
                
                while ((line = reader.readLine()) != null) {
                    if( line.indexOf('#')>=0){
                       line = line.replace("#", "" );
                       seq = line.split(line);
                    }
                    else if( line.indexOf('!')>=0){
                        break;
                    }
                    else{
                        split = line.split(line);
                        comb1 = seq[0]+" "+split[0];
                        comb2 = seq[1]+" "+split[1];
                        
                        if(!hm.containsKey(comb1)){
                            hm.put(comb1,comb2+" "+split[2]);
                        }
                        else{
                            rest = hm.get(comb1).toString();
                            hm.put(comb1,rest+","+comb2+" "+split[2]);
                        }
                        if(!hm.containsKey(comb2)){
                            hm.put(comb2,comb1+" "+split[1]);
                        }
                        else{
                            rest = hm.get(comb2).toString();
                            hm.put(comb2,rest+","+comb1+" "+split[1]);
                        }
                    }
                }
                
                htable.put(put);
                htable.flushCommits();
                htable.close();
                System.out.println("done");
            }catch(Exception e){
                System.out.println(e);
            }
            // Work with your 'file' object here
        }
    }
    
}
