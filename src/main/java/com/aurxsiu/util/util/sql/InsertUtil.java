package com.aurxsiu.util.util.sql;

import com.aurxsiu.util.file.FileHelper;
import com.aurxsiu.util.normal.NumberUtil;

import java.util.Arrays;

public class InsertUtil {
    public static void main(String[] args) throws Exception {
        String tableName = "LINEITEM";
        String folderPath = "C:\\Users\\aurxsiu\\Desktop\\database";
        String fileName = "\\"+tableName+".txt";
        String text = FileHelper.readFile(folderPath+fileName);

        String template = "INSERT INTO "+tableName+" VALUES(";
        String[] sqls = text.split("\n");
        System.out.println("len:"+sqls.length);
        int index = 0;
        FileHelper.createFile(folderPath+"\\"+tableName+".sql");
        StringBuilder builder = new StringBuilder();
        for (String sql : sqls) {
            String[] split = sql.split("\\|");
            for (int i = 0; i < split.length; i++) {
                if(!NumberUtil.isNumber(split[i])){
                    split[i] = "\""+split[i]+"\"";
                }
            }
            String execute = template + String.join(",",split)+");\n";
            builder.append(execute);
            index++;
            System.out.print("\r进度:"+index+"\\"+sqls.length);
        }
        System.out.println("write file...");
        FileHelper.writeFile(folderPath+"\\"+tableName+".sql",builder.toString());
    }
}
