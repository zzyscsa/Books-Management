package com.scsa.controller;

import com.scsa.pojo.Books;
import com.scsa.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl") //指名具体调用哪个类的实现
    private BookService bookService;

    //查询全部书籍，并且返回到一个数据展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute(list);
        return "allBook";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    //添加书籍请求
    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        bookService.addBook(books);
        return "redirect:/book/allBook"; //执行完之后回到首页
    }

    //跳转到修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("books", books);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books book) {
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }

    //删除书籍
    @RequestMapping("/del")
    public String deleteBook(int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //指定名称查询书籍
    @RequestMapping("/queryBook")
    public String queryBookByName(Model model, String queryBookName) {
        List<Books> list = new ArrayList<Books>();
        Books books = bookService.queryBookByName(queryBookName);
        if(books==null) {
            list = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        } else {
            list.add(books);
        }
        model.addAttribute(list);
        return "allBook";
    }

}
