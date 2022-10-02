package com.spem.application.serviceimpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spem.application.pojo.PreferredDays;
import com.spem.application.pojo.PreferredTime;
import com.spem.application.pojo.ProjectOption;
import com.spem.application.pojo.Reader;
import com.spem.application.pojo.StudentNumber;
import com.spem.application.pojo.Technology;
import com.spem.application.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {

    public String fileType = "text/csv";
    public String fileTypeXlxs = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private static List<Reader> teamDetails = new ArrayList<>();

    @Override
    public List<Reader> readExcel(InputStream inputStream) {
//		TODO: Add validations here
        try {
            teamDetails.clear();
            Workbook wb = new XSSFWorkbook(inputStream);
            Sheet s = wb.getSheet("Sheet1");
            Iterator<Row> row = s.iterator();
            int size = s.getLastRowNum();


            int rNumber = 0;
            while (row.hasNext()) {
                Row cRow = row.next();
                if (rNumber == 0) {
                    rNumber = rNumber + 1;
                    continue;
                }

                Iterator<Cell> cellValue = cRow.iterator();
                Reader reader = new Reader();


                int cellNumber = 0;

                while (cellValue.hasNext()) {
                    Cell value = cellValue.next();
                    DataFormatter formatter = new DataFormatter();
                    if (value == null || value.getCellType() == CellType.BLANK) {
                        cellNumber++;
                        continue;
                    }
                    switch (cellNumber) {
                        case 0:

                            String value1 = formatter.formatCellValue(value);
                            reader.setId(value1);
                            break;
                        case 1:

                            String startDate = formatter.formatCellValue(value);
                            reader.setStartTime(startDate);
                            break;
                        case 2:
                            String compDate = formatter.formatCellValue(value);
                            reader.setCompletionTime(compDate);
                            break;
                        case 3:
                            String email = formatter.formatCellValue(value);
                            reader.setEmail(email);
                            break;
                        case 4:
                            String name = formatter.formatCellValue(value);
                            reader.setName(name);
                            break;
                        case 5:
                            String firstName = formatter.formatCellValue(value);
                            reader.setFirstName(firstName);
                            break;
                        case 6:
                            String studentId = formatter.formatCellValue(value);
                            reader.setStudentId(studentId);
                            break;
                        case 7:
                            String workShop = formatter.formatCellValue(value);
                            reader.setWorkshopClass(workShop);
                            break;
                        case 8:
                            String perStud = formatter.formatCellValue(value);
                            List<String> listStudentNumber = new ArrayList<>();
                            String[] parts = perStud.split(",");
                            for (String splitValue : parts) {
                                listStudentNumber.add(splitValue);
                            }
                            reader.setPreferStudents(listStudentNumber);
                            break;
                        case 9:
                            String projOpt = formatter.formatCellValue(value);
                            List<ProjectOption> listProjectOption = new ArrayList<>();
                            String[] projectParts = projOpt.split(";");
                            for (String splitValue : projectParts) {
                                ProjectOption projectOption = new ProjectOption();
                                projectOption.setProjectOption(splitValue);

                                listProjectOption.add(projectOption);
                            }
                            reader.setProjectOption(listProjectOption);
                            break;
                        case 10:
                            String tech = formatter.formatCellValue(value);
                            List<Technology> listTechnology = new ArrayList<>();
                            String[] techParts = tech.split(";");
                            for (String splitValue : techParts) {
                                Technology technology = new Technology();
                                technology.setTechnology(splitValue);

                                listTechnology.add(technology);
                            }
                            reader.setTechnologies(listTechnology);
                            break;
                        case 11:
                            String timeZone = formatter.formatCellValue(value);
                            reader.setTimezone(timeZone);
                            break;
                        case 12:
                            String weekDay = formatter.formatCellValue(value);
                            List<PreferredDays> listPreferredDays = new ArrayList<>();
                            String[] weekParts = weekDay.split(";");
                            for (String splitValue : weekParts) {
                                PreferredDays preferredDays = new PreferredDays();
                                preferredDays.setDays(splitValue);

                                listPreferredDays.add(preferredDays);
                            }
                            reader.setPreferredWeekDays(listPreferredDays);
                            break;
                        case 13:
                            String time = formatter.formatCellValue(value);
                            List<PreferredTime> listPreferredTime = new ArrayList<>();
                            String[] timeParts = time.split(";");
                            for (String splitValue : timeParts) {
                                String[] timeDayParts = splitValue.split(", ");
                                PreferredTime preferredTime = new PreferredTime();
                                preferredTime.setSlot(timeDayParts[0]);
                                preferredTime.setTime(timeDayParts[1]);
                                listPreferredTime.add(preferredTime);
                            }

                            reader.setPreferredTimes(listPreferredTime);
                            break;

                        default:
                            break;

                    }
                    cellNumber++;
                }
                teamDetails.add(reader);
            }
            wb.close();
            return teamDetails;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamDetails;
    }

    @Override
    public boolean isCSV(MultipartFile file) {
        if (!fileType.equals(file.getContentType())) {
            return false;
        }
        return true;
    }


    @Override
    public boolean isExcel(MultipartFile file) {
        if (!fileTypeXlxs.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    @Override
    public List<Reader> readCSV(InputStream inputStream) {
//		List<Reader> teamDetails = new ArrayList<Reader>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim());) {
            teamDetails.clear();
            Iterable<CSVRecord> records = parser.getRecords();

            int count = 0;
            for (CSVRecord value : records) {

                if (count != 0) {
                    Reader readerData = new Reader();
                    readerData.setId(value.get(0));
                    readerData.setStartTime(value.get(1));
                    readerData.setCompletionTime(value.get(2));
                    readerData.setEmail(value.get(3));
                    readerData.setName(value.get(4));
                    readerData.setFirstName(value.get(5));
                    readerData.setStudentId(value.get(6));
                    readerData.setWorkshopClass(value.get(7));

                    String[] parts = value.get(8).split(";");
                    List<String> listStudentNumber = new ArrayList<>(Arrays.asList(parts));
                    readerData.setPreferStudents(listStudentNumber);


                    List<ProjectOption> listProjectOption = new ArrayList<>();
                    String[] projectParts = value.get(9).split(";");
                    for (String splitValue : projectParts) {
                        ProjectOption projectOption = new ProjectOption();
                        projectOption.setProjectOption(splitValue);

                        listProjectOption.add(projectOption);
                    }
                    readerData.setProjectOption(listProjectOption);


                    //	readerData.setTechnologies(value.get(10));
                    List<Technology> listTechnology = new ArrayList<>();
                    String[] techParts = value.get(10).split(";");
                    for (String splitValue : techParts) {
                        Technology technology = new Technology();
                        technology.setTechnology(splitValue);

                        listTechnology.add(technology);
                    }
                    readerData.setTechnologies(listTechnology);

                    readerData.setTimezone(value.get(11));
                    //	readerData.setPreferredWeekDays(value.get(12));
                    List<PreferredDays> listPreferredDays = new ArrayList<>();
                    String[] weekParts = value.get(12).split(";");
                    for (String splitValue : weekParts) {
                        PreferredDays preferredDays = new PreferredDays();
                        preferredDays.setDays(splitValue);

                        listPreferredDays.add(preferredDays);
                    }
                    readerData.setPreferredWeekDays(listPreferredDays);

                    //	readerData.setPreferredTimes(value.get(13));
                    List<PreferredTime> listPreferredTime = new ArrayList<>();
                    String[] timeParts = value.get(13).split(";");
                    for (String splitValue : timeParts) {
                        String[] timeDayParts = splitValue.split(",");
                        PreferredTime preferredTime = new PreferredTime();
                        preferredTime.setSlot(timeDayParts[0]);
                        preferredTime.setTime(timeDayParts[1]);
                        listPreferredTime.add(preferredTime);
                    }

                    readerData.setPreferredTimes(listPreferredTime);

                    teamDetails.add(readerData);
                }
                count++;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return teamDetails;
    }

	public List<Reader> getExistingStudents() {
        return teamDetails;
	}
}
