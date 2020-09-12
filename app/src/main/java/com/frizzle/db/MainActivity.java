package com.frizzle.db;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frizzle.db.db.BaseDao;
import com.frizzle.db.db.BaseDaoFactory;
import com.frizzle.db.db.OrderDao;
import com.frizzle.db.db.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
                userDao.insert(new User(1,"frizzle1","111"));
                userDao.insert(new User(2,"frizzle2","111"));
                userDao.insert(new User(3,"frizzle3","111"));
                userDao.insert(new User(4,"frizzle4","111"));
                userDao.insert(new User(5,"frizzle5","111"));
                userDao.insert(new User(6,"frizzle6","111"));
            }
        });

        // 如何自动创建数据库
        // 如何自动创建数据表
        // 如何让用户在使用的时候非常方便
        // 将user对象里面的类名 属性 转换成 创建数据库表的sql语句
        // create table user(id integer,name text,password text);

        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 基类  user 商品表 订单表（关联查询）
                UserDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
                User where = new User();
//                where.setName("frizzle");
                where.setPassword("111");
                List<User> list = baseDao.query(where);
                Log.e("frizzle database: "," list size is "+list.size());
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).toString());
                }
            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //update tb_user where name='frizzle' set password='1111'
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
                User user = new User();
                user.setId(2);
                user.setName("frizzle111111");
                user.setPassword("111");

                User where = new User();
                where.setId(2);
                baseDao.update(user,where);
                Toast.makeText(MainActivity.this,"执行成功!",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OrderDao orderDao = BaseDaoFactory.getInstance().getBaseDao(OrderDao.class,User.class);
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class,User.class);
                User where = new User();
                where.setName("frizzle111111");
                baseDao.delete(where);
            }
        });

    }
}
