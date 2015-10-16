/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Folder;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author Yoss
 */
public class Loader {
    public final Set<File> root = new HashSet<>();    
       
    public Loader() {
    }
    
    public void addRoot(File...fs){
        for(File f: fs) root.add(f);      
    }
    
    public Stream<File> getContent(){
        return root.stream().flatMap( f->Stream.of(f.listFiles()) );
    }
   
}