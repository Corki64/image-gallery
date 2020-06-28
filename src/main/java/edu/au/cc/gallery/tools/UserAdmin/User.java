package edu.au.cc.gallery.tools.UserAdmin;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;

public class User {

   public String adminPage(Request requestIn, Response responseIn) throws SQLException {
      Map<String, Object> model = new HashMap<>();
      ArrayList<String> userName = DB.getUserNames();
      model.put("users", userName);
      return new HandlebarsTemplateEngine().render(new ModelAndView(model, "admin.hbs"));
   }

   public String addUserPage(Request req, Response res) {
      Map<String, Object> model = new HashMap<>();
      return new HandlebarsTemplateEngine().render(new ModelAndView(model, "addUser.hbs"));
   }

   public String deleteUserPage(Request req, Response res) throws Exception {
      Map<String, Object> model = new HashMap<>();
      model.put("user", req.params(":user"));
      return new HandlebarsTemplateEngine().render(new ModelAndView(model, "deleteUser.hbs"));
   }

   public String modifyUserPage(Request req, Response res) throws Exception {
      Map<String, Object> model = new HashMap<>();
      model.put("user", req.params(":user"));
      return new HandlebarsTemplateEngine().render(new ModelAndView(model, "modifyUser.hbs"));
   }

   public String addUser(Request req, Response res) throws SQLException {
      String prefName = req.queryParams("prefName");
      String passWord = req.queryParams("passWord");
      String fullName = req.queryParams("fullName");
      DB.addUser(prefName, passWord, fullName);
      return toHome();
   }

   public String deleteUser(Request req, Response res) throws Exception {
      DB.deleteUser(req.params(":user"));
      return toHome();
   }

   public String modifyUser(Request req, Response res) throws Exception {
      DB.updateUser(req.params(":user"), req.queryParams("password"), req.queryParams("fullName"));
      return toHome();
   }

   public void addRoutes() {
      get("/admin", (req, res) -> adminPage(req, res));
      get("/admin/addUser", (req, res) -> addUserPage(req, res));
      get("/admin/modifyUser/:user", (req, res) -> modifyUserPage(req, res));
      post("/admin/addUser/add", (req, res) -> addUser(req, res));
      get("/admin/deleteUser/:user", (req, res) -> deleteUserPage(req, res));
      post("/admin/modifyUser/:user/modify", (req, res) -> modifyUser(req, res));
      post("/admin/deleteUser/:user/delete", (req, res) -> deleteUser(req, res));
   }

   private String toHome() throws SQLException {
      Map<String, Object> model = new HashMap<>();
      ArrayList<String> userName = DB.getUserNames();
      model.put("users", userName);
      return new HandlebarsTemplateEngine().render(new ModelAndView(model, "admin.hbs"));
   }
}
