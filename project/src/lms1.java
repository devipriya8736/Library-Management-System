

import lms_package.*;

public class lms1 {
    public static void main(String[] args) {
        code obj1 = new code();
        Userpage obj4=new Userpage(obj1);
        Adminpage obj2=new Adminpage(obj1);
        Mainpage obj3=new Mainpage(obj2, obj4,obj1);
        obj1.connect();
        obj3.gui();
    }
}
