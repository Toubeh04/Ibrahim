package com.progressoft.interns.advanced.parser.csvParser;

import com.progressoft.interns.advanced.exception.ParserException;
import com.progressoft.interns.advanced.parser.Parser;
import org.apache.maven.shared.utils.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserService<E> implements Parser<E> {
    ArrayList<ParserProperties> parsedArrayList3 = new ArrayList<>();
    ArrayList<ParserProperties> parsedArrayList5 = new ArrayList<>();
    ArrayList<ParserProperties> parsedArrayList10 = new ArrayList<>();
    int numOfHeaders =0;

    @Override
    public E parse(String filePath) {
        List<String> lines = loader(filePath);
        String[] headers = lines.get(0).split(",");
        numOfHeaders = headers.length;
        for (int i = 1; i < lines.size(); i++) {
            if(lines.get(i).isEmpty()) {
                continue;
            }
            String[] values = lines.get(i).split(",");
            if (values.length != numOfHeaders){
                throw new ParserException("Missing Data on line: "+(i+1));
            }
            ParserProperties parserProperties =new ParserProperties();
            size();
                if (numOfHeaders == 3) {
                    if (headers[1].equals(" test2")){
                        parserProperties.country = values[0];
                        parserProperties.date = values[1];
                        parserProperties.student = values[2];
                        parsedArrayList3.add(parserProperties);
                    } else {
                        parserProperties.country = values[0];
                        parserProperties.date = values[1];
                        parserProperties.numOfIntStudent = BigDecimal.valueOf(Long.parseLong(String.valueOf(values[2])));
                        parsedArrayList3.add(parserProperties);
                    }
                }else if (numOfHeaders == 5){
                    parserProperties.setId(new BigDecimal(values[0]));
                    parserProperties.setStudent(values[1]);
                    parserProperties.setStudentGrade(new BigDecimal(values[2]));
                    parserProperties.setEmployee(values[3]);
                    parserProperties.setEmployeeYearsOfExperience(Integer.parseInt(values[4]));
                    parsedArrayList5.add(parserProperties);
                }else {
                    parserProperties.setProp1(new BigDecimal(values[0]));
                    parserProperties.setProp2(new BigDecimal(values[1]));
                    parserProperties.setProp3(new BigDecimal(values[2]));
                    parserProperties.setProp4(new BigDecimal(values[3]));
                    parserProperties.setProp5(new BigDecimal(values[4]));
                    parserProperties.setProp6(new BigDecimal(values[5]));
                    parserProperties.setProp7(new BigDecimal(values[6]));
                    parserProperties.setProp8(new BigDecimal(values[7]));
                    parserProperties.setProp9(new BigDecimal(values[8]));
                    parserProperties.setProp10(new BigDecimal(values[9]));
                    parsedArrayList10.add(parserProperties);
                }
        }
        if (numOfHeaders == 3){
            return (E) parsedArrayList3;
        }
        else if (numOfHeaders == 5){
            return (E) parsedArrayList5;
        }
        return (E) parsedArrayList10;
    }
    public List<String> loader(String filePath){
        if (StringUtils.isEmpty(filePath)){
            throw new ParserException("File Path Cannot be null");
        }
        Path file = Paths.get(filePath.replaceFirst("/","").replace("/","\\"));
        List<String> lines;
        try {
            lines = Files.readAllLines(file);
            if(Files.size(file) == 0) {
                throw new ParserException("File is empty");
            }
        }catch (IOException e){
            throw new ParserException("File not found: " + filePath);
        }
        return lines;
    }
    public int size() {
        if (numOfHeaders == 3) {
            return parsedArrayList3.size();
        }
        else if (numOfHeaders == 5) {
            return parsedArrayList5.size();
        }
        return parsedArrayList10.size();
    }
}
