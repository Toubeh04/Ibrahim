package com.progressoft.interns.advanced.utility;

import com.progressoft.interns.advanced.exception.UtilityException;
import com.progressoft.interns.advanced.parser.csvParser.ParserProperties;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ParsedDataUtilityImpl<E>  implements ParsedDataUtility<E> {


    @Override
    public BigDecimal getSummationOfColumn(E parsedData, String columnName) {
        checker(columnName);
        BigDecimal sum = new BigDecimal(0);
        if (parsedData instanceof ArrayList && ((ArrayList) parsedData).get(0) instanceof ParserProperties) {
            ArrayList<ParserProperties> dataList = (ArrayList<ParserProperties>) parsedData;
            for (ParserProperties data : dataList) {
                switch (columnName) {
                    case "Student Grade":
                        sum = sum.add(data.getStudentGrade());
                        break;
                    case "Employee years of Experience":
                        sum = sum.add(BigDecimal.valueOf(data.getEmployeeYearsOfExperience()));
                        break;
                    case "Number of international students":
                        sum = sum.add((data.getNumOfIntStudent()));
                        break;
                    case "Random Numbers One":
                        sum = sum.add(data.getProp1());
                        break;
                    case "Random Numbers Five":
                        sum = sum.add(data.getProp5());
                        break;
                    case "Random Numbers Six":
                        sum = sum.add(data.getProp6());
                        break;
                }
            }
        } else {
            throw new IllegalArgumentException("PDUI-GSOC Err");
        }
        return sum.setScale(5, RoundingMode.HALF_UP);

    }



    @Override
    public BigDecimal getAverageOfColumn(E parsedData, String columnName) {
        checker(columnName);
        BigDecimal numToDivBy= new BigDecimal(0);
        BigDecimal sum = new BigDecimal(0);
        if (parsedData instanceof ArrayList && ((ArrayList) parsedData).get(0) instanceof ParserProperties) {
            ArrayList<ParserProperties> dataList = (ArrayList<ParserProperties>) parsedData;
            for (ParserProperties data : dataList) {
                switch (columnName) {
                    case "Student Grade":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add(data.getStudentGrade());
                        break;
                    case "Employee years of Experience":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add(BigDecimal.valueOf(data.getEmployeeYearsOfExperience()));
                        break;
                    case "Number of international students":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add((data.getNumOfIntStudent()));
                        break;
                    case "Random Numbers One":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add(data.getProp1());
                        break;
                    case "Random Numbers Five":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add(data.getProp5());
                        break;
                    case "Random Numbers Six":
                        numToDivBy = numToDivBy.add(BigDecimal.valueOf(1));
                        sum = sum.add(data.getProp6());
                        break;
                }
            }
        } else {
            throw new IllegalArgumentException("parsedData must be an ArrayList of parsedProperties");
        }
        return sum.divide(numToDivBy,5,RoundingMode.HALF_UP);
    }

    @Override
    public ArrayList<String> getColumnData(E parsedData, String columnName) {
        checker(columnName);
        List<String> stringList = new ArrayList<>();
        if (parsedData instanceof ArrayList && ((ArrayList<?>) parsedData).get(0) instanceof ParserProperties) {
            ArrayList<ParserProperties> dataList = (ArrayList<ParserProperties>) parsedData;
            for (ParserProperties data : dataList) {
                if (columnName.equals("Student Grade")){
                    stringList.add(String.valueOf(data.getStudentGrade()));
                }
            }
        }
        return (ArrayList<String>) stringList;
    }
    public void checker(String columnName){
        if (!(columnName.equals("id")||
                columnName.equals("student")||
                columnName.equals("Student Grade")||
                columnName.equals("employee")||
                columnName.equals("Employee years of Experience")||
                columnName.equals("Number of international students")||
                columnName.equals("Random Numbers One")||
                columnName.equals("Random Numbers Two")||
                columnName.equals("Random Numbers Three")||
                columnName.equals("Random Numbers Four")||
                columnName.equals("Random Numbers Five")||
                columnName.equals("Random Numbers Six")||
                columnName.equals("Random Numbers Seven")||
                columnName.equals("Random Numbers Eight")||
                columnName.equals("Random Numbers Nine")||
                columnName.equals("Random Numbers Ten"))){
            throw new UtilityException("Column " + columnName + " not found");
        }
    }
}
