package com.hvisions.common.utils;

import com.hvisions.common.dto.*;
import com.hvisions.common.annotation.*;
import io.swagger.annotations.*;
import java.lang.reflect.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;
import java.text.*;
import com.hvisions.common.enums.*;
import com.hvisions.common.exception.*;
import com.hvisions.common.interfaces.*;
import org.springframework.beans.*;
import com.hvisions.common.consts.*;
import org.apache.poi.ss.usermodel.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import org.slf4j.*;

public class ExcelUtil
{
    private static final Logger log;
    private static final int PROPERTY_NAME_ROW_NUM = 0;
    private static final int PROPERTY_ROW_NUM = 1;
    private static final int VALUE_ROW_START_NUM = 2;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT;
    private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT;
    private static final String XLS_FILE_NAME_REGEX = "^.+\\.(?i)(xls)$";
    private static final String XLSX_FILE_NAME_REGEX = "^.+\\.(?i)(xlsx)$";
    
    public static ResponseEntity<byte[]> generateImportFile(final Object example, final String tableName) throws IOException, IllegalAccessException {
        final List list = new ArrayList();
        list.add(example);
        return generateImportFile(list, tableName, example.getClass());
    }
    
    public static ResponseEntity<byte[]> generateImportFile(final Class clas, final String tableName) throws IOException, IllegalAccessException {
        final List list = new ArrayList();
        return generateImportFile(list, tableName, clas);
    }
    
    public static ResponseEntity<byte[]> generateImportFile(final List list, final String tableName, final Class clas) throws IllegalAccessException, IOException {
        final List<ExtendColumnInfo> columnInfos = new ArrayList<ExtendColumnInfo>();
        return generateImportFile(list, tableName, clas, columnInfos);
    }
    
    public static ResponseEntity<byte[]> generateImportFile(final Class clas, final String tableName, final List<ExtendColumnInfo> columnInfos) throws IllegalAccessException, IOException {
        return generateImportFile(new ArrayList(), tableName, clas, columnInfos);
    }
    
    public static ResponseEntity<byte[]> generateImportFile(final List list, final String tableName, final Class clas, final List<ExtendColumnInfo> columnInfos) throws IllegalAccessException, IOException {
        final HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        addSheetToWorkBook(list, tableName, clas, columnInfos, hssfWorkbook);
        return generateHttpExcelFile(hssfWorkbook, tableName);
    }
    
    public static void addSheetToWorkBook(final List list, final String tableName, final Class clas, final List<ExtendColumnInfo> columnInfos, final HSSFWorkbook hssfWorkbook) throws IllegalAccessException {
        final HSSFSheet hssfSheet = hssfWorkbook.createSheet(tableName);
        final HSSFRow hssfRowCn = hssfSheet.createRow(0);
        final HSSFRow hssfRow = hssfSheet.createRow(1);
        final Field[] fields = ReflectUtil.getAllFields(clas);
        final HSSFRow[] valueRows = new HSSFRow[list.size()];
        int columnSqu = 0;
        for (int i = 0; i < fields.length; ++i) {
            final ExcelAnnotation excelAnnotation = fields[i].getAnnotation(ExcelAnnotation.class);
            if (excelAnnotation == null || !excelAnnotation.ignore()) {
                final ApiModelProperty apiModelProperties = fields[i].getAnnotation(ApiModelProperty.class);
                if (apiModelProperties != null) {
                    if (apiModelProperties.hidden()) {
                        continue;
                    }
                    final HSSFCell hssfCell = hssfRowCn.createCell(columnSqu);
                    hssfCell.setCellValue(apiModelProperties.value());
                }
                HSSFCell hssfCell = hssfRow.createCell(columnSqu);
                hssfCell.setCellValue(fields[i].getName());
                for (int j = 0; j < list.size(); ++j) {
                    final Object value = fields[i].get(list.get(j));
                    if (value != null) {
                        if (valueRows[j] == null) {
                            valueRows[j] = hssfSheet.createRow(j + 2);
                        }
                        hssfCell = valueRows[j].createCell(columnSqu);
                        if (fields[i].getType() == Date.class) {
                            hssfCell.setCellValue(ExcelUtil.SIMPLE_DATE_TIME_FORMAT.format((Date)value));
                        }
                        else {
                            hssfCell.setCellValue(value.toString());
                        }
                    }
                }
                ++columnSqu;
            }
        }
        if (columnInfos != null && columnInfos.size() > 0) {
            for (int i = 0; i < columnInfos.size(); ++i) {
                final ExtendColumnInfo info = columnInfos.get(i);
                HSSFCell hssfCell = hssfRowCn.createCell(columnSqu);
                hssfCell.setCellValue(info.getChName());
                hssfCell = hssfRow.createCell(columnSqu);
                hssfCell.setCellValue(info.getColumnName());
                if (list.size() > 0 && list.get(0) instanceof IExtendObject) {
                    for (int k = 0; k < list.size(); ++k) {
                        final IExtendObject iExtendObject = (IExtendObject)list.get(k);
                        hssfCell = valueRows[k].createCell(columnSqu);
                        addCell(hssfCell, info, iExtendObject);
                    }
                }
                ++columnSqu;
            }
        }
    }
    
    private static void addCell(final HSSFCell hssfCell, final ExtendColumnInfo info, final IExtendObject iExtendObject) {
        final Map<String, Object> map = (Map<String, Object>)iExtendObject.getExtend();
        if (map != null) {
            final Object extendProperty = map.get(info.getColumnName());
            if (extendProperty != null) {
                if (info.getColumnType().toUpperCase().equals(ExtendColumnTypeEnum.DATE.toString())) {
                    hssfCell.setCellValue(ExcelUtil.SIMPLE_DATE_FORMAT.format(iExtendObject.getExtend().get(info.getColumnName())));
                }
                if (info.getColumnType().toUpperCase().equals(ExtendColumnTypeEnum.DATETIME.toString())) {
                    hssfCell.setCellValue(ExcelUtil.SIMPLE_DATE_TIME_FORMAT.format(iExtendObject.getExtend().get(info.getColumnName())));
                }
                else {
                    hssfCell.setCellValue(iExtendObject.getExtend().get(info.getColumnName()).toString());
                }
            }
        }
    }
    
    public static ResponseEntity<byte[]> generateHttpExcelFile(final HSSFWorkbook hssfWorkbook, final String tableName) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        hssfWorkbook.write((OutputStream)baos);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + tableName);
        return (ResponseEntity<byte[]>)new ResponseEntity(baos.toByteArray(), (MultiValueMap)headers, HttpStatus.OK);
    }
    
    public static <T> ImportResult importEntity(final MultipartFile file, final Class clas, final EntitySaver<T> entitySaver) throws IOException, IllegalAccessException, ParseException {
        final List<ExtendColumnInfo> extendColumnInfos = new ArrayList<ExtendColumnInfo>();
        return importEntity(file, clas, entitySaver, extendColumnInfos);
    }
    
    public static <T> ImportResult importEntity(final MultipartFile file, final Class clas, final EntitySaver<T> entitySaver, final List<ExtendColumnInfo> extendColumnInfos) throws IOException, IllegalAccessException, ParseException {
        return importEntity(file, 0, clas, entitySaver, extendColumnInfos);
    }
    
    public static <T> ImportResult importEntity(final MultipartFile file, final int sheetNum, final Class clas, final EntitySaver<T> entitySaver, final List<ExtendColumnInfo> extendColumnInfos) throws IOException, IllegalAccessException, ParseException {
        Field field = null;
        final ImportResult importResult = new ImportResult();
        final Workbook wb = getWorkbook(file);
        final Sheet sheet = wb.getSheetAt(sheetNum);
        final DataFormatter formatter = new DataFormatter();
        if (sheet == null) {
            throw new BaseKnownException((BaseErrorCode)BaseErrorEnum.IMPORT_SHEET_IS_NULL, new Object[0]);
        }
        final List<String> properties = getProrpertys(sheet);
        final Field[] allFields;
        final Field[] fields = allFields = ReflectUtil.getAllFields(clas);
        for (final Field f : allFields) {
            f.setAccessible(true);
        }
        for (int r = 2; r <= sheet.getLastRowNum(); ++r) {
            final Row row = sheet.getRow(r);
            final T entity = (T)BeanUtils.instantiateClass(clas);
            IExtendObject iExtendObject = null;
            if (entity instanceof IExtendObject) {
                iExtendObject = (IExtendObject)entity;
                iExtendObject.setExtend((Map)new HashMap(IntegerConst.MAP_SIZE));
            }
            for (int i = 0; i < properties.size(); ++i) {
                for (final Field f2 : fields) {
                    if (f2.getName().equals(properties.get(i))) {
                        field = f2;
                        break;
                    }
                }
                if (field != null) {
                    final Cell cell = row.getCell(i);
                    if (cell != null) {
                        final String cellString = formatter.formatCellValue(cell);
                        field.set(entity, StringObjectMapper.convertStringToObject(field, cellString));
                    }
                    field = null;
                }
                if (iExtendObject != null) {
                    final Cell cell = row.getCell(i);
                    if (cell != null) {
                        final String cellString = formatter.formatCellValue(cell);
                        ExtendColumnInfo cellPropertyExtendInfo = null;
                        for (final ExtendColumnInfo extendColumnInfo : extendColumnInfos) {
                            if (extendColumnInfo.getColumnName().equals(properties.get(i))) {
                                cellPropertyExtendInfo = extendColumnInfo;
                                break;
                            }
                        }
                        if (cellPropertyExtendInfo != null) {
                            final Object value = StringObjectMapper.getExtendObject(cellString, cellPropertyExtendInfo);
                            iExtendObject.getExtend().put(properties.get(i), value);
                        }
                    }
                }
            }
            try {
                importResult.setTotalCount(importResult.getTotalCount() + 1);
                entitySaver.saveOrUpdate(entity);
                importResult.getSuccessLines().add(r + 1);
            }
            catch (Exception ex) {
                importResult.getErrorLines().put(r + 1, ex.getMessage());
            }
        }
        return importResult;
    }
    
    private static List<String> getProrpertys(final Sheet sheet) {
        final List<String> properties = new ArrayList<String>();
        final Row row = sheet.getRow(1);
        for (int i = 0; i < row.getLastCellNum(); ++i) {
            properties.add(row.getCell(i).getStringCellValue());
        }
        return properties;
    }
    
    private static Workbook getWorkbook(final MultipartFile file) throws IOException {
        final String fileName = file.getOriginalFilename();
        final InputStream is = file.getInputStream();
        Workbook wb;
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            wb = (Workbook)new HSSFWorkbook(is);
        }
        else {
            if (!fileName.matches("^.+\\.(?i)(xlsx)$")) {
                throw new BaseKnownException((BaseErrorCode)BaseErrorEnum.IMPORT_FILE_NO_SUPPORT, new Object[0]);
            }
            wb = (Workbook)new XSSFWorkbook(is);
        }
        return wb;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ExcelUtil.class);
        SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
