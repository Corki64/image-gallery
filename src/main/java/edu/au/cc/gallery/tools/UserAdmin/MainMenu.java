package edu.au.cc.gallery.tools.UserAdmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

   public static void menu() throws IOException {
      System.out.println("\n\n--==**Menu**==--");
      System.out.println("1)  List Users");
      System.out.println("2)  Add User");
      System.out.println("3)  Edit User");
      System.out.println("4)  Delete User");
      System.out.println("5)  Quit");
      System.out.print("Enter command :> ");
      command();
   }

   public static void command() throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int input = Integer.parseInt(br.readLine());

      if (input > 0 && input < 7) {
         switcher(input);
      } else {
         System.out.println("Not a valid menu option.");
         menu();
         command();
      }
   }

   public static void switcher(int choiceIn) throws IOException {
      switch (choiceIn) {
         case 1:
            try {
               DB.listUsers();
            } catch (Exception e) {
               e.printStackTrace();
            }
            menu();
            command();
            break;
         case 2:
            try {
               DB.addUser();
            } catch (Exception e) {
               e.printStackTrace();
            }
            menu();
            command();
            break;

         case 3:
            try {
               DB.editUser();
            } catch (Exception e) {
               e.printStackTrace();
            }
            menu();
            command();
            break;
         case 4:
            try {
               DB.deleteUser();
            } catch (Exception e) {
               e.printStackTrace();
            }
            menu();
            command();
            break;
         case 5:
            System.exit(0);
      }

   }
}
