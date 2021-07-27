package com.jnesis.jap.peartopear.index;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;

public class Index {

    private static final Logger LOG = LoggerFactory.getLogger(Index.class);

    private HashMap<String,IndexEntry> indexMap=new HashMap<>();

    public void addToIndex(File f){
        LOG.info("Adding file "+f.getName());
        if (!f.exists()){
            LOG.warn("File doesn't exist");
            return;
        }
        final IndexEntry indexEntry=IndexEntry.createIndexEntry(f);
        indexMap.put(f.getName(),indexEntry);
        LOG.info("IndexEntry "+indexEntry+" has been added");
    }

    public int size(){
        return indexMap.size();
    }

    static class IndexEntry {
        String path;
        long size;
        String name;

        private IndexEntry(String path, long size, String name) {
            this.path = path;
            this.size = size;
            this.name = name;
        }

        static IndexEntry createIndexEntry(File file){
            return new IndexEntry(file.getPath(),file.length(), file.getName());
        }

        @Override
        public String toString() {
            return "IndexEntry{" +
                    "path='" + path + '\'' +
                    ", size=" + size +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}