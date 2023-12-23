package com.example.demo.servicesImpl;
import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookTypeDao;
import com.example.demo.entity.BookType;
import com.example.demo.repository.BookTypeRepository;
import com.example.demo.services.BookService;
import com.example.demo.spark.SparkSubmitRunner;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public Msg getAllBooks(){
        List<Book> books = bookDao.getBooks();
        List<Map<String, Object>> result = new ArrayList<>();
        for(Book book: books){
            Map<String, Object> map = new HashMap<>();
            map.put("bid", book.getBid());
            map.put("title", book.getTitle());
            map.put("price", book.getPrice());
            map.put("image", book.getImage());
            map.put("type", book.getType());
            result.add(map);
        }
        return new Msg(1, "get all books success", result);
    }

    @Override
    public Msg deleteBook(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(-1, "book not exist");
        bookDao.deleteBook(id);
        book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(1, "delete book success");
        else
            return new Msg(-1, "delete book failed");
    }

    @Override
    public Msg addBook(Map<String, String> book){
        Book book1 = bookDao.getBookByTitle(book.get("title"));
        if(book1 != null)
            return new Msg(-1, "book already exist");
        bookDao.addBook(book);
        book1 = bookDao.getBookByTitle(book.get("title"));
        if(book1 != null)
            return new Msg(1, "add book success");
        else
            return new Msg(-1, "add book failed");
    }

    @Override
    public Msg search(String keyword){
        Book res = bookDao.search(keyword);
        List<Book> book = new ArrayList<>();
        book.add(res);
        if(res == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "search book success", book);
    }

    @Override
    public Msg updateBook(Map<String, String> book, Long id){
        Book book1 = bookDao.getBookById(id);
        if(book1 == null)
            return new Msg(-1, "book not exist");
        bookDao.updateBook(book, id);
        book1 = bookDao.getBookById(id);
        return new Msg(1, "update book success");
    }

    @Override
    public Msg getBook(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "get book success", book);
    }

    @Override
    public Msg getBookTypeNames(){
        List<BookType> bookTypes = bookTypeDao.getAllBookTypes();
        List<String> result = new ArrayList<>();
        for(BookType bookType: bookTypes){
            result.add(bookType.getTypeName());
        }
        return new Msg(1, "get book type success", result);
    }

    @Override
    public Msg getBookByType(String typeName){
        // 直接找到两层之内所有类型
        List<BookType> bookTypes = bookTypeDao.getRelateBookTypes(typeName);
        List<Book> books = new ArrayList<>();
        for(BookType bookType: bookTypes){
            List<Long> bookIDs = bookType.getBookIDs();
            for(Long bookID: bookIDs){
                Book book = bookDao.getBookById(bookID);
                books.add(book);
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for(Book book: books){
            Map<String, Object> map = new HashMap<>();
            map.put("bid", book.getBid());
            map.put("title", book.getTitle());
            map.put("price", book.getPrice());
            map.put("image", book.getImage());
            map.put("type", book.getType());
            result.add(map);
        }
        return new Msg(1, "get book by type success", result);
    }

    @Override
    public Book getBookByTitle(String title){
        return bookDao.getBookByTitle(title);
    }

    @Override
    public boolean addBookDescriptionTxt(String path, String description){
        try {
            // 将描述文本追加到指定文件
            Files.write(Paths.get(path), description.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            // 如果成功写入，则返回 true
            return true;
        } catch (IOException e) {
            // 处理异常，这里可以根据实际情况进行处理，比如记录日志
            e.printStackTrace();
            return false;
        }
    }

    // 辅助方法：删除指定目录中的所有文件
    @Override
    public boolean deleteAllFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Msg createBookDescriptionTxt(){
        // 删除books目录中的所有文件
        String booksDirectoryPath = "D:\\bookstore\\demo\\se3353_25_spark_python\\books";
        if (!deleteAllFilesInDirectory(booksDirectoryPath)) {
            return new Msg(-1, "Failed to delete existing files in the books directory");
        }

        // 获取所有书籍并创建描述文件
        List<Book> books = bookDao.getBooks();
        for(Book book: books){
            String type = book.getType();
            String description = book.getDescription();
            String path = "D:\\bookstore\\demo\\se3353_25_spark_python\\books\\" + type + ".txt";
            if(!addBookDescriptionTxt(path, description))
                return new Msg(-1, "create book description txt failed");
        }
        return new Msg(1, "create book description txt success");
    }

    @Override
    public Msg getWordCount(){
        // 调用 SparkSubmitRunner 类中的 sparkRunner 方法
        SparkSubmitRunner.sparkRunner(null);
//        Map<String, Integer> result = new HashMap<>();
        String filePath = "D:\\bookstore\\demo\\se3353_25_spark_python\\output\\part-00000";
//        try {
//            // 读取 Spark 生成的结果文件
//            List<String> lines = Files.readAllLines(Paths.get(path));
//            for(String line: lines){
//                String[] words = line.split(" ");
//                result.put(words[0], Integer.parseInt(words[1]));
//            }
//            return new Msg(1, "get word count success", result);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new Msg(-1, "get word count failed");
//        }
//        String filePath = "D:\\PythonProjects\\se3353_25_spark_python\\output2\\part-00001";
        Map<String, Integer> resultMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设文件的每一行格式为 ('code', 7) 这样的形式
                // 使用简单的字符串操作来提取tag和对应的计数值
                int startIndex = line.indexOf('(') + 1;
                int commaIndex = line.indexOf(',');
                int endIndex = line.indexOf(')');

                if (startIndex != -1 && commaIndex != -1 && endIndex != -1) {
                    String tag = line.substring(startIndex, commaIndex).trim().replace("'", "");
                    int count = Integer.parseInt(line.substring(commaIndex + 1, endIndex).trim());

                    resultMap.put(tag, count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg(-1, "get word count failed");
        }

        return new Msg(1, "get word count success", resultMap);
    }

    @Override
    public Msg getWordCountByKeyword(String keyword){
        // 调用 SparkSubmitRunner 类中的 sparkRunner 方法
        SparkSubmitRunner.sparkRunner(keyword);
        String filePath = "D:\\bookstore\\demo\\se3353_25_spark_python\\output\\part-00000";
        Map<String, Integer> resultMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 假设文件的每一行格式为 ('code', 7) 这样的形式
                // 使用简单的字符串操作来提取tag和对应的计数值
                int startIndex = line.indexOf('(') + 1;
                int commaIndex = line.indexOf(',');
                int endIndex = line.indexOf(')');

                if (startIndex != -1 && commaIndex != -1 && endIndex != -1) {
                    String tag = line.substring(startIndex, commaIndex).trim().replace("'", "");
                    int count = Integer.parseInt(line.substring(commaIndex + 1, endIndex).trim());

                    if(tag.contains(keyword))
                        resultMap.put(tag, count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg(-1, "get word count failed");
        }

        return new Msg(1, "get word count success", resultMap);
    }
}
